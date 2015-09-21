package liir.nlp.webservices;
import liir.nlp.sources.stanford.SentenceSplitter;
import liir.nlp.sources.stanford.Tokenizer;
import scala.collection.script.End;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by quynhdo on 20/09/15.
 */
@WebService()
public class StanfordInterfaceServices {

  static SentenceSplitter ss;
  static liir.nlp.sources.stanford.Tokenizer tk;

  public StanfordInterfaceServices(){
    ss =new SentenceSplitter();
    tk=new Tokenizer();
  }
  @WebMethod
  public String sentenceSplitter(String text) {
    String result = ss.processToText(text).toXMLString();
    System.out.println(result);
    return result;
  }

  public static void main(String[] argv) {
    Object implementor = new StanfordInterfaceServices ();
    String address = "http://localhost:9000/snlp";
    Endpoint ep = Endpoint.publish(address, implementor);



  }
}
