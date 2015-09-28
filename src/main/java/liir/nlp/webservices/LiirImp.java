package liir.nlp.webservices;

import liir.nlp.sources.bekerley.coref.DriverExtended;
import liir.nlp.sources.bekerley.interfaces.*;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by quynhdo on 28/09/15.
 */
public class LiirImp {

    static LiirCoref bc;


    private static  LiirImp instance;

    public static synchronized  LiirImp getInstance()
    {
        if(instance == null)
        {
            instance = new LiirImp();

        }
        return instance;
    }

    private  LiirImp(){

        try {

            Properties prop = new Properties();
            String propFileName = "ber.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }


            String coref_model=prop.getProperty("coref_model");
            String gender_path = prop.getProperty("gender_path");

            int w = Integer.parseInt(prop.getProperty("w"));
            String ilp_config  = prop.getProperty("ilp_config");


            DriverExtended be = new DriverExtended(coref_model, gender_path);


            if (coref_model!=null && gender_path!=null)
                bc= new LiirCoref( coref_model, gender_path  , w, ilp_config);


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
