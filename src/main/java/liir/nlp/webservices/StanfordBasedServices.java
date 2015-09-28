package liir.nlp.webservices;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.sun.net.httpserver.HttpServer;
import liir.nlp.core.representation.Text;
import liir.nlp.sources.stanford.SentenceSplitter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

/**
 * Created by quynhdo on 20/09/15.
 */
@Path("/snlp")
@XmlRootElement

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
    @Produces("application/xml")
    public Response getSentences(@QueryParam("text") String xmltext){

       Text txt =ss.processXMLToText(xmltext);
        txt.setAutomaticIndexing();
        Response.ResponseBuilder rb = new ResponseBuilderImpl();

        rb.entity(txt.toXMLString());

        return rb.build();

    }


    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/tokens")
    @Produces("application/xml")
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





}
