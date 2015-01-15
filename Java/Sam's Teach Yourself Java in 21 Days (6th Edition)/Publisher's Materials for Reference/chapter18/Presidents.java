import java.io.*;
import java.sql.*;

public class Presidents {
    String home, system;
    
    public Presidents() {
        // set the database's directory
        home = System.getProperty("user.home", ".");
        system = home + File.separatorChar + ".database";
        System.setProperty("derby.system.home", system);
    }
    
    public void createDatabase() {       
        // create the database
        String data = "jdbc:derby:presidents;create=true";
        try {
            // load the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // create the connection
            Connection conn = DriverManager.getConnection(data);
            Statement st = conn.createStatement();
            // create the contacts table
            int result = st.executeUpdate(
                "CREATE TABLE contacts ("
                    + "dex INTEGER NOT NULL PRIMARY KEY "
                    + "GENERATED ALWAYS AS identity "
                    + "(START WITH 1, INCREMENT BY 1), "
                    + "name VARCHAR(40), "
                    + "address1 VARCHAR(40), "
                    + "address2 VARCHAR(40), "
                    + "phone VARCHAR(20), "
                    + "email VARCHAR(40))");
            // insert four records into the new table
            result = st.executeUpdate(
                "INSERT INTO contacts (name, address1, address2, "
                    + "phone, email) VALUES("
                    + "'Jimmy Carter', "
                    + "'Carter Presidential Center', "
                    + "'1 Copenhill, Atlanta, GA 30307', "
                    + "'(404) 727-7611', "
                    + "'carterweb@emory.edu')");
            result = st.executeUpdate(
                "INSERT INTO contacts (name, address1, address2, "
                    + "phone, email) VALUES("
                    + "'George Bush', "
                    + "'Box 79798', "
                    + "'Houston, TX 77279', "
                    + "'(409) 260-9552', "
                    + "'library@bush.nara.gov')");
            result = st.executeUpdate(
                "INSERT INTO contacts (name, address1, address2, "
                    + "phone, email) VALUES("
                    + "'Bill Clinton', "
                    + "'15 Old House Lane', "
                    + "'Chappaqua, NY 10514', "
                    + "'(501) 370-8000', "
                    + "'info@clintonpresidentialcenter.com')");
            result = st.executeUpdate(
                "INSERT INTO contacts (name, address1, address2, "
                    + "phone, email) VALUES("
                    + "'George W. Bush', "
                    + "'White House, 1600 Pennsylvania Ave.', "
                    + "'Washington, DC 20500', "
                    + "'(202) 456-1414', "
                    + "'president@whitehouse.gov')");
            st.close();
            System.out.println("Database created in " + system);
        } catch (Exception e) {
            System.out.println("Error -- " + e.toString());
        }
    }            
            
    public void readDatabase() {
        String data = "jdbc:derby:presidents";
        try {
            // load the driver and connect to the database
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(
                data, "", "");
            // load all records from the contacts table
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery(
                "SELECT * FROM contacts ORDER BY name");
            // loop through each record and display its fields
            while(rec.next()) {
                System.out.println(rec.getString("name") +  "\n"
                    + rec.getString("address1") + "\n"
                    + rec.getString("address2") + "\n"
                    + rec.getString("phone") + "\n"
                    + rec.getString("email") + "\n");
            }
            st.close();
        } catch (Exception e) {
            System.out.println("Error -- " + e.toString());
        }
    }
    
    public static void main(String[] arguments) {
        Presidents prez = new Presidents();
        if (arguments.length < 1) {
            System.out.println("Usage: java Presidents [create|read]");
            System.exit(-1);
        }
        if (arguments[0].equals("create")) {
            prez.createDatabase();
        }
        if (arguments[0].equals("read")) {
            prez.readDatabase();
        }
    }    
}
