import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.xmlrpc.*;

public class PingClient {
    XmlRpcClient client;
    
    public PingClient() {
        this("http://localhost:7667/");
    }
    
    public PingClient(String server) {
        try {
            client = new XmlRpcClient(server);
        } catch (MalformedURLException exception) {
            System.out.println("Bad URL: " + server);
        }
    }
    
    public Hashtable ping(String siteName, String siteUrl) 
        throws IOException, XmlRpcException {
            
        Vector<String> params = new Vector<String>();
        params.add(siteName);
        params.add(siteUrl);
        Object result = client.execute("weblogUpdates.ping", params);
        if (result instanceof XmlRpcException) {
        	throw (XmlRpcException) result;
        }
        return (Hashtable) result;
    }
       
    public static void main(String[] arguments) {
        if (arguments.length < 2) {
            System.out.println("Usage: java PingClient sitename url");
            System.exit(-1);
        }
        try {
            PingClient pinger;
            pinger = new PingClient();
            Hashtable response = pinger.ping(arguments[0], arguments[1]);
            System.out.println(response.get("message"));
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
