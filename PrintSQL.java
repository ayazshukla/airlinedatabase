import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PrintSQL {
    private Connection connect() {
        // SQLite connection string
        String url =  "jdbc:sqlite:C:/Users/Ayaz/OneDrive/Documents/963832-Summative/airlinedatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

 
    public void selectAll(){// AYAZ
        String sql = "SELECT * FROM aircrafts";

        try (Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("FlightNo") +  "\t" + 
                    rs.getString("Destination") + "\t" +
                    rs.getInt("Capacity") + "\t" +
                    rs.getString("DepartTime") + "\t" +
                    rs.getString("ArriveTime"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        PrintSQL app = new PrintSQL();
        app.selectAll();
     }

}