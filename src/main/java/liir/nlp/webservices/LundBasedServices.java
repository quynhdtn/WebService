
package liir.nlp.webservices;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.sun.jersey.spi.inject.Inject;
import liir.nlp.io.XMLReader;
import liir.nlp.representation.Text;
import liir.nlp.srl.sources.lth.interfaces.LundLemmatizer;
import liir.nlp.srl.sources.lth.interfaces.LundParser;
import liir.nlp.srl.sources.lth.interfaces.LundSRL;
import liir.nlp.srl.sources.lth.interfaces.LundTagger;
import org.xml.sax.SAXException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/*
@Path("/lnlp")
public class LundBasedServices {


       static LundLemmatizer ll;
       static LundTagger lt;
       static LundParser lp ;
       static LundSRL srl;



    public LundBasedServices(){

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


    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/lemma")
    @Produces({"application/xml", "application/json"})
    public Response getLemma(@QueryParam("text") String xmltext){

        Text text= null;
        try {
            text = XMLReader.readCorpus(xmltext).get(0);
           ll.processToText(text);
            Response.ResponseBuilder rb = new ResponseBuilderImpl();

            rb.entity(text.toXMLString());

            return rb.build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;

    }



    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/pos")
    @Produces({"application/xml", "application/json"})
    public Response getPOS(@QueryParam("text") String xmltext){

        try {
            Text   text = XMLReader.readCorpus(xmltext).get(0);
            lt.processToText(text);
            Response.ResponseBuilder rb = new ResponseBuilderImpl();

            rb.entity(text.toXMLString());

            return rb.build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;

    }



}
*/


@Path("/lnlp")
public class LundBasedServices {


    static LundImp li=LundImp.getInstance();



    public LundBasedServices(){



    }


    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/lemma")
    @Produces({"application/xml", "application/json"})
    public Response getLemma(@QueryParam("text") String xmltext){

        Text text= null;
        try {
            text = XMLReader.readCorpus(xmltext).get(0);
            li.ll.processToText(text);
            Response.ResponseBuilder rb = new ResponseBuilderImpl();

            rb.entity(text.toXMLString());

            return rb.build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;

    }



    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/pos")
    @Produces({"application/xml", "application/json"})
    public Response getPOS(@QueryParam("text") String xmltext){

        try {
            Text   text = XMLReader.readCorpus(xmltext).get(0);
            li.lt.processToText(text);
            Response.ResponseBuilder rb = new ResponseBuilderImpl();

            rb.entity(text.toXMLString());

            return rb.build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;

    }


    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/parse")
    @Produces({"application/xml", "application/json"})
    public Response getParse(@QueryParam("text") String xmltext){

        try {
            Text   text = XMLReader.readCorpus(xmltext).get(0);
            li.lp.processToText(text);
            Response.ResponseBuilder rb = new ResponseBuilderImpl();

            rb.entity(text.toXMLString());

            return rb.build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;

    }


    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/srl")
    @Produces({"application/xml", "application/json"})
    public Response getSRL(@QueryParam("text") String xmltext){

        try {
            Text   text = XMLReader.readCorpus(xmltext).get(0);
            text = li.srl.processToText(text);
            Response.ResponseBuilder rb = new ResponseBuilderImpl();

            rb.entity(text.toXMLString());

            return rb.build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;

    }

}
