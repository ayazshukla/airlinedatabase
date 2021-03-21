import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class AddFakeData {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/Ayaz/Documents/CompSummative/airlinedatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the airline table
     *
     * @param name
     * @param capacity
     * @param flightno
     */
    public void insert(String FlightNo,String Destination,String Capacity,String DepartTime,String ArriveTime) {//AYAZ
        String sql = "INSERT INTO aircrafts( FlightNo,Destination,Capacity,DepartTime,ArriveTime) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, FlightNo);
            pstmt.setString(2, Destination);
            pstmt.setString(3,Capacity);
            pstmt.setString(4, DepartTime);
            pstmt.setString(5, ArriveTime);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {//AYAZ
        AddFakeData app = new AddFakeData();

        String FlightNo[]={"AC01","AC02","AC03","AC04","AC05"};
        String Destination[]={"Dubai","Tokyo","Berlin","Moscow","Lahore"};
        String Capacity[]={"526","400","400","526","400"};
        String DepartTime[] = {"3:30","12:45","17:50","1:20","21:30"};
        String ArriveTime[] = {"9:45","2:30","3:05","11:45","17:55"};

        for(int i=0;i<5;i++){
            app.insert(FlightNo[i],Destination[i],Capacity[i],DepartTime[i],ArriveTime[i]);
        }
    }
}