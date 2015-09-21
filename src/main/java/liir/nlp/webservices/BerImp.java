package liir.nlp.webservices;

import liir.nlp.sources.bekerley.interfaces.BerCoref;
import liir.nlp.sources.bekerley.interfaces.BerParser;
import liir.nlp.sources.bekerley.interfaces.BerSentenceSplitter;
import liir.nlp.sources.bekerley.interfaces.BerTokenizer;
import liir.nlp.srl.sources.lth.interfaces.LundLemmatizer;
import liir.nlp.srl.sources.lth.interfaces.LundParser;
import liir.nlp.srl.sources.lth.interfaces.LundSRL;
import liir.nlp.srl.sources.lth.interfaces.LundTagger;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by quynhdo on 20/09/15.
 */
public class BerImp {

    static BerSentenceSplitter bs;
    static BerTokenizer tk;
    static BerParser pa;
    static BerCoref bc;


    private static  BerImp instance;

    public static synchronized  BerImp getInstance()
    {
        if(instance == null)
        {
            instance = new  BerImp( );

        }
        return instance;
    }

    private  BerImp(){

        try {

            Properties prop = new Properties();
            String propFileName = "ber.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String sensplitter_model = prop.getProperty("sensplitter_model");
            String parser_model1= prop.getProperty("grammar_model");
            String parser_model2= prop.getProperty("backoff_model");
            String coref_model=prop.getProperty("coref_model");
            String gender_path = prop.getProperty("gender_path");



            if (sensplitter_model!=null) {
                bs = new BerSentenceSplitter(sensplitter_model);
                tk = new BerTokenizer(sensplitter_model);
            }
            if (parser_model1 !=null && parser_model2 != null)
                pa = new BerParser(parser_model1,parser_model2);


            System.out.println(pa);
            if (coref_model!=null && gender_path!=null)
                bc= new BerCoref(coref_model,gender_path);


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
