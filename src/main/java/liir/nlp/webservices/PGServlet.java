package liir.nlp.webservices;

import liir.nlp.pg.preprocessing.TextPreprocessing;
import liir.nlp.pg.virtual.DomainReader;
import liir.nlp.pg.virtual.Mapper;
import liir.nlp.core.representation.Text;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PGServlet extends HttpServlet {
    TextPreprocessing prep ;
    DomainReader dr =new DomainReader();
    Mapper mp = new Mapper();
    public PGServlet() {
        super();
        prep = new TextPreprocessing();
        try {

            dr.readPddlPredicates("/Users/quynhdo/Documents/WORKING/MUSE/pddl/muse-domain.pddl");
            dr.readPddlTypes("/Users/quynhdo/Documents/WORKING/MUSE/pddl/muse-domain.pddl", "types");
            dr.readPddlTypes("/Users/quynhdo/Documents/WORKING/MUSE/pddl/muse-domain.pddl", "constants");

            dr.readPddlActions("/Users/quynhdo/Documents/WORKING/MUSE/pddl/muse-domain.pddl");
           dr.readDictionary("/Users/quynhdo/Desktop/pg.dictionary.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String text= request.getParameter("text");
        process(text);
   //     request.setAttribute("output_value", rs);
    //    request.getRequestDispatcher("/index.jsp").forward(request, response);



    }

    String process(String text){

        try {
            Text txt = TextPreprocessing.process(text);
            mp.map(txt,dr );

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return "";
    }


}