import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class CreateTable {
    public static void createNewTable() {//AYAZ
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/Ayaz/Documents/CompSummative/airlinedatabase.db";
        // SQL statement for creating a new table
        
        String sql = "CREATE TABLE IF NOT EXISTS aircrafts (\n"
                + "    FlightNo text PRIMARY KEY NOT NULL,\n"
                + "    Destination text NOT NULL,\n"
                + "    Capacity text NOT NULL,\n"
                + "    DepartTime text NOT NULL,\n"
                + "    ArriveTime text NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    
    public static void main(String[] args) {
        createNewTable();
     }
}