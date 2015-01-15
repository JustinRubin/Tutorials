import java.util.*;

public class PingHandler {
    public Hashtable ping(String siteName, String url) {
        Hashtable<String, Object> response = new Hashtable<String, Object>();
        if ((siteName.equals("")) | (url.equals(""))) {
        	response.put("message", "Bad ping");
        	Boolean flError = new Boolean(true);
        	response.put("flerror", flError);
        } else {
        	response.put("message", "Thanks for the ping");
        	Boolean flError = new Boolean(false);
        	response.put("flerror", flError);
        	System.out.println("Ping received from " + siteName + ", " + url);
        }
        return response;
    }
}