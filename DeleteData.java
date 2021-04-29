import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author sqlitetutorial.net
 */
public class DeleteData {
 
    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/Ayaz/OneDrive/Documents/963832-Summative/airlinedatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    /**
     * Delete a warehouse specified by the id
     *
     * @param id
     */
    public void delete(String s) {//AYAZ
        String sql = "DELETE FROM passenger WHERE PNR = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, s);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DeleteData app = new DeleteData();
        // delete the rows 
        app.delete("PN035");
        
    }
 
}
