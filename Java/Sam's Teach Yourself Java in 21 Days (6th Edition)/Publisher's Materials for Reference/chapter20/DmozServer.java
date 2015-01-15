import java.io.*;
import org.apache.xmlrpc.*;
import org.apache.xmlrpc.server.*;
import org.apache.xmlrpc.webserver.*;

public class DmozServer {
    public static void main(String[] arguments) {
         try {
             startServer();
         } catch (IOException ioe) {
             System.out.println("Server error: " +
                 ioe.getMessage());
         } catch (XmlRpcException xre) {
             System.out.println("XML-RPC error: " +
                 xre.getMessage());
         }
    }

    public static void startServer() throws IOException, XmlRpcException {
        // Create the server
        System.out.println("Starting Dmoz server ...");
        WebServer server = new WebServer(4413);
        XmlRpcServer xmlRpcServer = server.getXmlRpcServer();
        PropertyHandlerMapping phm = new PropertyHandlerMapping();

        // Register the handler
        phm.addHandler("dmoz", DmozHandlerImpl.class);
        xmlRpcServer.setHandlerMapping(phm);

        // Start the server
        server.start();
        System.out.println("Accepting requests ...");
    }
}
