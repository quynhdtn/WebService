package liir.nlp.webservices;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by quynhdo on 28/09/15.
 */
public class Driver {


    static HttpServer server;

    public Driver(){
            try {

                Properties prop = new Properties();
                String propFileName = "server.properties";

                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }
                String address = prop.getProperty("server");
                String host = prop.getProperty("host");
                server = HttpServerFactory.create("http://" + address + ":" +  host +"/");

            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public void start(){
        server.start();
        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9999/snlp");
        System.out.println("Hit return to stop...");
        try {
            System.in.read();

        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        server.stop(0);
        System.out.println("Server stopped");
    }


    public static void main(String[] args) throws IOException {

        Driver d = new Driver();
        d.start();

    }


}
