import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.xmlrpc.*;

public class AdvogatoClient {
    XmlRpcClient client;
    
    public AdvogatoClient() {
        this("http://www.advogato.org:80/XMLRPC");
    }
    
    public AdvogatoClient(String server) {
        try {
            client = new XmlRpcClient(server);
        } catch (MalformedURLException exception) {
            System.out.println("Bad URL: " + server);
        }
    }
    
    public Integer getEntryCount(String username) 
        throws IOException, XmlRpcException {
            
        Vector<String> params = new Vector<String>();
        params.add(username);
        Integer result = (Integer) client.execute("diary.len", params);
        return result;
    }
    
    public String getEntry(String username, Integer index) 
        throws IOException, XmlRpcException {
        	
    	Vector<Object> params = new Vector<Object>();
        params.add(username);
        params.add(index);
        String result = (String) client.execute("diary.get", params);
        return result;
    }
    
    public static void main(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java AdvogatoClient username");
            System.exit(-1);
        }
        try {
            AdvogatoClient advo;
            advo = new AdvogatoClient();
            int entryCount = advo.getEntryCount(arguments[0]);
            for (int i = 0; i < entryCount; i++) {
            	String entry = advo.getEntry(arguments[0], i);
            	System.out.println(entry);
            }
            System.out.println("");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
