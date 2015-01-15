import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class WebSaver {

    public WebSaver() {
    }

    void getData(String address) throws MalformedURLException {
        URL page = new URL(address);
        // Create file object
        String filename = page.getHost() + "_" + page.getFile() + "_file.html";
        File output = new File(filename);
        try {
            // Create output stream
            FileWriter fw = new FileWriter(output);
            BufferedWriter out = new BufferedWriter(fw);
            StringBuffer text = new StringBuffer();
            HttpURLConnection conn = (HttpURLConnection)
                page.openConnection();
            conn.connect();
            InputStreamReader in = new InputStreamReader(
                (InputStream) conn.getContent());
            BufferedReader buff = new BufferedReader(in);
            String line;
            do {
                line = buff.readLine();
                if (line != null) {
                	out.write(line, 0, line.length());
                }
            } while (line != null);
            out.flush();
        } catch (IOException ioe) {
            System.out.println("IO Error:" + ioe.getMessage());
        }
    }

    public static void main(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java WebSaver url");
            System.exit(1);
        }
        try {
            WebSaver app = new WebSaver();
            app.getData(arguments[0]);
        } catch (MalformedURLException mue) {
            System.out.println("Bad URL: " + arguments[0]);
        }
    }
}
