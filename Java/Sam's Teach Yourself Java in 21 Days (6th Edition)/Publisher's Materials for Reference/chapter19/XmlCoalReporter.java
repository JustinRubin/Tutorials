import java.io.*;
import java.sql.*;
import nu.xom.*;
 
public class XmlCoalReporter {
    public static void main(String[] arguments) {
       // create an <energy> element to serve as the document's root
       Element energy = new Element("energy");
       
       String data = "jdbc:odbc:WorldEnergy";
       try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conn = DriverManager.getConnection(
                data, "", "");
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery(
                "SELECT * " +
                "FROM Coal " +
                "WHERE " +
                "(Country='" + arguments[0] + "') " +
                "ORDER BY Year");
            while(rec.next()) {
            	// create a <coal> element and make it a child of <energy>
                Element coal = new Element("coal");
                energy.appendChild(coal);
                // create a <fips> element with the value of this database field
                Element fips = new Element("fips");
                Text fipsText = new Text(rec.getString(1));
        		fips.appendChild(fipsText);
        		coal.appendChild(fips);
                // create a <country> element with the value of this database field
                Element country = new Element("country");
                Text countryText = new Text(rec.getString(2));
        		country.appendChild(countryText);
        		coal.appendChild(country);
                // create a <year> element with the value of this database field
                Element year = new Element("year");
                Text yearText = new Text(rec.getString(3));
        		year.appendChild(yearText);
        		coal.appendChild(year);
                // create a <production> element with the value of this database field
                Element production = new Element("production");
                Text productionText = new Text(rec.getString(4));
        		production.appendChild(productionText);
        		coal.appendChild(production);
            }
            st.close();
            // create a new document with <energy> as the root element
            Document doc = new Document(energy);
            // store the document to disk
	        String filename = "energy.xml";
            File outFile = new File(filename);
            FileOutputStream fos = new FileOutputStream(outFile);
            Serializer serializer = new Serializer(fos);
            serializer.setIndent(2);
            serializer.write(doc);
        } catch (SQLException s) {
            System.out.println("SQL Error: " + s.toString() + " "
                + s.getErrorCode() + " " + s.getSQLState());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString()
                + e.getMessage());
            e.printStackTrace();
        }
    }
}