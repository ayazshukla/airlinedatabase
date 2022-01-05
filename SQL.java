import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
class SQL
{
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

    public void insert(String PNR,String FlightNo,String FirstName,String LastName,String SeatNo) {//UTKARSH
        String sql = "INSERT INTO passenger(PNR,FlightNo,FirstName,LastName,SeatNo) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, PNR);
            pstmt.setString(2, FlightNo);
            pstmt.setString(3, FirstName);
            pstmt.setString(4, LastName);
            pstmt.setString(5, SeatNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String CheckSeatAvailabilty(String FlightNo) {//AYAZ
        int seat=0;
        String sql1= "SELECT SeatNo FROM passenger WHERE FlightNo='"+FlightNo+"'";
        try (Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql1)){

            while (rs.next()) {
                int seatcheck=Integer.parseInt(rs.getString("SeatNo"));
                if(Integer.parseInt(rs.getString("SeatNo"))>seat) {
                    seat=seatcheck;
                }

            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return String.valueOf(++seat);

    }

    public String assignPNR() {//UTKARSH
        String PNRNumbers = null;
        String PNRBooked = null;
        String rowCounter = "SELECT COUNT(*) FROM passenger";
        //String sql2 = "SELECT 'PNR' FROM passengers";
        String sql2 = "SELECT * FROM passenger ORDER BY 'PNR' DESC LIMIT (SELECT COUNT(*) FROM passenger)";  
        try (Connection conn = this.connect();
        Statement stmt  = conn.createStatement();

        ResultSet rs    = stmt.executeQuery(sql2)){
            while (rs.next()) {
                // gets the last entered PNR
                PNRNumbers = rs.getString("PNR");

            }
            int counter = 1;
            while (counter < 100) {
                String passengerDigits = PNRNumbers.substring(3);
                int passengerPNR = Integer.parseInt(passengerDigits);
                PNRBooked = ("PN" + "0" + (passengerPNR + 1));
                System.out.println("Your PNR is " + PNRBooked);
                counter++;
                break;
            }
            while (counter >= 100) {
                String passengerDigits = PNRNumbers.substring(2);
                int passengerPNR = Integer.parseInt(passengerDigits);
                PNRBooked = ("PN" + (passengerPNR + 1));
                System.out.println("Your PNR is " + PNRBooked);
                counter++;
                break;
            }  
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
        return PNRBooked;     
    }

    public void PrintBoardingPass(String s){//TOGETHER
        String sql = "SELECT passenger.FirstName,passenger.LastName,passenger.SeatNo,aircrafts.Destination,aircrafts.DepartTime,aircrafts.ArriveTime FROM aircrafts,passenger WHERE passenger.PNR='"+s+"' AND aircrafts.FlightNo=passenger.FlightNo ";
        try (Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println("Name:"+  rs.getString("FirstName") + " "+ rs.getString("LastName") + "\t" + 
                    "SeatNo:" +  +rs.getInt("SeatNo") + "\n" + 
                    "To:" +  rs.getString("Destination") + "\n" +
                    "Departure:" + rs.getString("DepartTime") + "\t" +
                    "Arrival:" + rs.getString("ArriveTime") + "\n");

                System.out.println("Check-in closes 60 minutes before departure. Gates close 15 minutes before take off");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void BookTicket(String PNR,String FlightNo,String FirstName,String LastName,String SeatNo){//UTKARSH
        String sql = "INSERT INTO passenger(PNR,FlightNo,FirstName,LastName,SeatNo) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, PNR);
            pstmt.setString(2, FlightNo);
            pstmt.setString(3,FirstName);
            pstmt.setString(4, LastName);
            pstmt.setString(5, SeatNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CancelBooking(String s){//AYAZ
        String sql = "DELETE FROM passenger WHERE PNR = ?";
        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CheckAvailableDestinations(){//UTKARSH
        String sql= "SELECT FlightNo,Destination FROM aircrafts";
        try (Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(rs.getString("FlightNo") + " " + "To:" + rs.getString("Destination")); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {//AYAZ
        SQL obj=new SQL();//object to call class functions
        String choice="0";

        while(!choice.equals("5")){
            Scanner input=new Scanner(System.in);
            System.out.println(" ");
            System.out.println("PLEASE CHOOSE ONE OF THE FOLLOWING");
            System.out.println("1.Print Boarding Pass");
            System.out.println("2.Book a ticket");
            System.out.println("3.Cancel a Booking");
            System.out.println("4.Check Destinations");
            System.out.println("5.EXIT"+ "\n");

            choice=input.nextLine();
            switch(choice){
                case "1":
                System.out.println("Enter your PNR");
                obj.PrintBoardingPass(input.nextLine());
                break;

                case "2":
                String FlightNo=" ";;
                System.out.println("Please enter First Name:  ");
                String FirstName;
                FirstName = input.nextLine();
                System.out.println("Please enter Last Name:  ");
                String LastName;
                LastName = input.nextLine();
                System.out.println("Where are you flying to : ");
                String Destination = " ";
                Destination = input.nextLine();
                switch(Destination){
                    case "Dubai":
                    FlightNo = "AC01";
                    break;
                    case "Tokyo":
                    FlightNo = "AC02";
                    break;
                    case "Berlin":
                    FlightNo = "AC03";
                    break;
                    case "Moscow":
                    FlightNo = "AC04";
                    break;
                    case "Lahore":
                    FlightNo = "AC05";
                    break;
                }
                String passengerPNR = obj.assignPNR();
                String passengerSeat = obj.CheckSeatAvailabilty(FlightNo);
                obj.insert(passengerPNR,FlightNo,FirstName,LastName,passengerSeat);
                System.out.println("Your flight has been booked");
                break;

                case "3":
                System.out.println("Enter your existing PNR to cancel the booking");
                obj.CancelBooking(input.nextLine());
                System.out.println("Your Booking has been cancelled");
                break;

                case "4":
                obj.CheckAvailableDestinations();
                break;

                case "5":
                System.out.println("TERMINATED");
                break;
            }
        }
    }

} 
