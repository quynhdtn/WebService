package liir.nlp.webservices;

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
public class LundImp {
    static LundLemmatizer ll;
    static LundTagger lt;
    static LundParser lp ;
    static LundSRL srl;

    private static  LundImp instance;

    public static synchronized  LundImp getInstance()
    {
        if(instance == null)
        {
            instance = new  LundImp( );

        }
        return instance;
    }

    private  LundImp(){

        try {

            Properties prop = new Properties();
            String propFileName = "lund.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String lemma_model = prop.getProperty("lemma_model");
            String pos_model = prop.getProperty("pos_model");
            String parser_model = prop.getProperty( "parser_model");
            String srl_model = prop.getProperty("srl_model");
            System.out.println(lemma_model);
            if (lemma_model!=null)
                ll = new LundLemmatizer(lemma_model);
            if (pos_model!=null)
                lt = new LundTagger(pos_model);
            if (parser_model!=null)
                lp = new LundParser(parser_model);
            if (srl_model!=null)
                srl = new LundSRL(srl_model);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
