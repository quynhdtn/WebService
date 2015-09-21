package liir.nlp.webservices;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.sun.jersey.spi.inject.Inject;
import com.sun.net.httpserver.HttpServer;
import liir.nlp.interfaces.preprocessing.Tokenizer;
import liir.nlp.representation.Text;
import liir.nlp.sources.stanford.SentenceSplitter;
import liir.nlp.srl.sources.lth.interfaces.LundLemmatizer;
import liir.nlp.srl.sources.lth.interfaces.LundParser;
import liir.nlp.srl.sources.lth.interfaces.LundSRL;
import liir.nlp.srl.sources.lth.interfaces.LundTagger;
import org.xml.sax.SAXException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by quynhdo on 20/09/15.
 */
@Path("/snlp")
public class StanfordBasedServices {


    static SentenceSplitter ss;
    static liir.nlp.sources.stanford.Tokenizer tk;


    public StanfordBasedServices(){

        ss = new SentenceSplitter();
        tk=new liir.nlp.sources.stanford.Tokenizer();

    }


    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/sens")
    @Produces({"application/xml", "application/json"})
    public Response getSentences(@QueryParam("text") String xmltext){

       Text txt =ss.processXMLToText(xmltext);

        Response.ResponseBuilder rb = new ResponseBuilderImpl();

        rb.entity(txt.toXMLString());

        return rb.build();

    }


    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/tokens")
    @Produces({"application/xml", "application/json"})
    public Response getTokens(@QueryParam("text") String sentence){

        try {
            Text  txt = tk.processToText(sentence);

            Response.ResponseBuilder rb = new ResponseBuilderImpl();

            rb.entity(txt.toXMLString());


            return rb.build();
        } catch (Exception e1) {
        }

        return null;
    }


    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9999/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9999/snlp");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }




}
