package liir.nlp.webservices;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import liir.nlp.core.representation.Text;
import liir.nlp.core.representation.io.XMLReader;
import org.xml.sax.SAXException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by quynhdo on 28/09/15.
 */

@Path("/liirnlp")
@XmlRootElement
public class LiirCorefService {

    static LiirImp li = LiirImp.getInstance();



    public LiirCorefService(){



    }




    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/coref")
    @Produces("application/xml")
    public Response getParse(@QueryParam("text") String xmltext){

        try {
            Text text = XMLReader.readCorpus(xmltext).get(0);
            li.bc.processToText(text);
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
