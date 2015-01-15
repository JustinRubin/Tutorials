import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.xmlrpc.*;
import org.apache.xmlrpc.client.*;

public class SiteClient {
    public static void main(String arguments[]) {
        SiteClient client = new SiteClient();
        try {
            HashMap<String, String> response = client.getRandomSite();
            // Report the results
            if (response.size() > 0) {
                System.out.println("URL: " + response.get("url")
                    + "\nTitle: " + response.get("title")
                    + "\nDescription: " + response.get("description"));
            }
        } catch (IOException ioe) {
            System.out.println("IO Exception: " + ioe.getMessage());
        } catch (XmlRpcException xre) {
            System.out.println("XML-RPC Exception: " + xre.getMessage());
        }
    }

    public HashMap getRandomSite()
      throws IOException, XmlRpcException {

            // Create the client
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            URL server = new URL("http://localhost:4413/");
            config.setServerURL(server);
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);
            // Create the parameters for the request
            ArrayList params = new ArrayList();
            // Send the request and get the response
            HashMap result = (HashMap) client.execute("dmoz.getRandomSite",
                params);
            return result;
    }
}
