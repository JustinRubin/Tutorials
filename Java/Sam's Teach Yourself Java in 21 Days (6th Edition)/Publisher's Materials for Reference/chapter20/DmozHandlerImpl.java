import java.sql.*;
import java.util.*;

public class DmozHandlerImpl implements  DmozHandler {

    public HashMap getRandomSite() {
        Connection conn = getMySqlConnection();
        HashMap<String, String> response = new HashMap<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery(
                "SELECT * FROM cooldata ORDER BY RAND() LIMIT 1");
            if (rec.next()) {
                response.put("url", rec.getString("url"));
                response.put("title", rec.getString("title"));
                response.put("description", rec.getString("description"));
            } else {
                response.put("error", "no database record found");
            }
        } catch (SQLException sqe) {
            response.put("error", sqe.getMessage());
        }
        return response;
    }

    private Connection getMySqlConnection() {
        Connection conn = null;
        String data = "jdbc:mysql://localhost/cool";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                data, "cool", "mrfreeze");
        } catch (SQLException s) {
            System.out.println("SQL Error: " + s.toString() + " "
                + s.getErrorCode() + " " + s.getSQLState());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString()
                + e.getMessage());
        }
        return conn;
    }
}
