import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class QuoteSaver {
    private String ticker;

    public QuoteSaver(String inTicker) {
        ticker = inTicker;
    }

    private String retrieveQuote() {
        StringBuffer buf = new StringBuffer();
        try {
            URL page = new URL("http://quote.yahoo.com/d/quotes.csv?s=" +
               ticker + "&f=sl1d1t1c1ohgv&e=.csv");
            String line;
            URLConnection conn = page.openConnection();
            conn.connect();
            InputStreamReader in= new InputStreamReader(
                conn.getInputStream());
            BufferedReader data = new BufferedReader(in);
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }
        } catch (MalformedURLException mue) {
            System.out.println("Bad URL: " + mue.getMessage());
        } catch (IOException ioe) {
            System.out.println("IO Error:" + ioe.getMessage());
        }
        return buf.toString();
    }

    private void storeQuote(String data) {
        StringTokenizer tokens = new StringTokenizer(data, ",");
        String[] fields = new String[9];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = stripQuotes(tokens.nextToken());
        }
        String datasource = "jdbc:derby:quotedata;create=true";
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(datasource);
            PreparedStatement prep2 = conn.prepareStatement(
                "INSERT INTO " +
                "Stocks(ticker, price, quoteDate, change, open, " +
                "high, low, volume) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            prep2.setString(1, fields[0]);
            prep2.setString(2, fields[1]);
            prep2.setString(3, fields[2]);           
            prep2.setString(4, fields[4]);
            prep2.setString(5, fields[5]);
            prep2.setString(6, fields[6]);
            prep2.setString(7, fields[7]);
            prep2.setString(8, fields[8]);
            prep2.executeUpdate();
            conn.close();
        } catch (SQLException sqe) {
            System.out.println("SQL Error: " + sqe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }

    private String stripQuotes(String input) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '\"') {
                output.append(input.charAt(i));
            }
        }
        return output.toString();
    }

    public static void main(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java QuoteSaver tickerSymbol");
            System.exit(0);
        }
        QuoteSaver qd = new QuoteSaver(arguments[0]);
        String data = qd.retrieveQuote();
        qd.storeQuote(data);
    }      
}
