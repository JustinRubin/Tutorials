import java.io.IOException;
import org.apache.xmlrpc.WebServer;
import org.apache.xmlrpc.XmlRpc;

public class PingServer {
    public static void main(String[] arguments) {
         try {
             startServer(7667);
         } catch (IOException ioe) {
             System.out.println("Server error: " +
                 ioe.getMessage());
         }
    }

    public static void startServer(int port) throws IOException {
        // Start the server
        System.out.println("Starting ping server ...");
        WebServer server = new WebServer(port);

        // Register the handler
        PingHandler handler = new PingHandler();
        server.addHandler("weblogUpdates", handler);
        server.start();
        System.out.println("Accepting requests ...");
    }
}