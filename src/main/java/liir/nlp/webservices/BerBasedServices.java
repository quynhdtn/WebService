package liir.nlp.webservices;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import liir.nlp.core.representation.io.XMLReader;
import liir.nlp.core.representation.Text;
import org.xml.sax.SAXException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by quynhdo on 21/09/15.
 */
@Path("/bnlp")
@XmlRootElement
public class BerBasedServices {


    static BerImp bi=BerImp.getInstance();



    public BerBasedServices(){



    }




    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/parse")
    @Produces("application/xml")
    public Response getParse(@QueryParam("text") String xmltext){

        try {
            Text   text = XMLReader.readCorpus(xmltext).get(0);
            bi.pa.processToText(text);
            text.setAutomaticIndexing();
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
    @Path("/sens")
    @Produces("application/xml")
    public Response getSentences(@QueryParam("text") String text){

        Text txt = bi.bs.processToText(text);

        Response.ResponseBuilder rb = new ResponseBuilderImpl();

        rb.entity(txt.toXMLString());

        return rb.build();

    }


    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/tokens")
    @Produces("application/xml")
    public Response getTokens(@QueryParam("text") String xmltxt){

        try {
            Text text = XMLReader.readCorpus(xmltxt).get(0);
             bi.tk.processToText(text);



            text.setAutomaticIndexing();
            Response.ResponseBuilder rb = new ResponseBuilderImpl();

            rb.entity(text.toXMLString());


            return rb.build();
        } catch (Exception e1) {
        }

        return null;
    }



    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/coref")
    @Produces("application/xml")
    public Response getCoref(@QueryParam("text") String xmltext){

        try {
            Text   text = XMLReader.readCorpus(xmltext).get(0);
            bi.bc.setUsingGold(false);

            bi.bc.processToText(text);
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
    @Path("/corefG")
    @Produces("application/xml")
    public Response getCorefG(@QueryParam("text") String xmltext){

        try {
            Text   text = XMLReader.readCorpus(xmltext).get(0);
            bi.bc.setUsingGold(true);
            bi.bc.processToText(text);
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
