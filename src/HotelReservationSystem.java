import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class HotelReservationSystem {

    private static String url = "jdbc:mysql://127.0.0.1:3306/Hotel";

    private static String username = "root";

    private static String password = "123456";

    public static void main(String args[]) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("WELCOME TO HOTEL RESERVATION SYSTEM!!!");
                System.out.println("--------------------------------------");

                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. Exit");
                System.out.println();
                System.out.print("Choose an option: ");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        reserveRoom(conn, stmt, sc);
                        break;

                    case 2:
                        viewReservations(conn, stmt, sc);
                        break;

                    case 3:
                        getRoomNumber(conn, stmt, sc);
                        break;

                    case 4:
                        updateReservations(conn, stmt, sc);
                        break;

                    case 5:
                        deleteReservations(conn, stmt, sc);
                        break;

                    case 0:
                        exit();
                        stmt.close();
                        conn.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Please enter a valid choice!");
                        System.out.println();
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reserveRoom(Connection conn, Statement stmt, Scanner sc) throws SQLException{
        System.out.print("Enter guest name: ");
        String name = sc.next();
        System.out.print("Enter room number: ");
        int roomNum = sc.nextInt();
        System.out.print("Enter contact number: ");
        String contact = sc.next();

        String sql = "INSERT INTO Reservations (guest_name, room_number, contact_number) VALUES ('" + name + "', " + roomNum + ", '" + contact + "');";
        int rs = stmt.executeUpdate(sql);

        if (rs > 0) {
            System.out.println("Reservation is successful!");
            System.out.println();
        } else {
            System.out.println("Reservation failed.");
            System.out.println();
        }
    }

    public static void viewReservations(Connection conn, Statement stmt, Scanner sc) throws SQLException {
        String sql = "SELECT * FROM Reservations;";
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Current Reservations:");
        System.out.println("+------------------------+------------------------+------------------------+------------------------+------------------------+");
        System.out.println("|     Reservation ID     |       Guest Name       |       Room Number      |     Contact Number     |     Reservation Date   |");
        System.out.println("+------------------------+------------------------+------------------------+------------------------+------------------------+");

        while (rs.next()) {
            int id = rs.getInt("reservation_id");
            String name = rs.getString("guest_name");
            int roomNum = rs.getInt("room_number");
            String contact = rs.getString("contact_number");
            String tstamp = String.valueOf(rs.getTimestamp("reservation_date"));

            System.out.printf("| %-22d | %-22s | %-22d | %-22s | %-22s |\n",
                    id, name, roomNum, contact, tstamp);
        }
        System.out.println("+------------------------+------------------------+------------------------+------------------------+------------------------+");
        System.out.println();
    }

    public static void getRoomNumber(Connection conn, Statement stmt, Scanner sc) throws SQLException{
        System.out.print("Enter reservation ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter guest name: ");
        String name = sc.nextLine();

        String sql = "SELECT room_number FROM Reservations WHERE reservation_id = " + id + " AND guest_name = '" + name + "';";
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            int roomNum = rs.getInt("room_number");
            System.out.println("Room number for Reservation ID " + id + " and Guest " + name + " is: " + roomNum);
            System.out.println();
        } else {
            System.out.println("Reservation not found for the given ID and guest name.");
            System.out.println();
        }
    }

    public static void updateReservations(Connection conn, Statement stmt, Scanner sc) throws SQLException{
        System.out.print("Enter reservation ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new guest name: ");
        String name = sc.nextLine();
        System.out.print("Enter new room number: ");
        int roomNum = sc.nextInt();
        System.out.print("Enter new contact number: ");
        String contact = sc.next();

        String sql = "UPDATE Reservations " +
                "SET guest_name = '" + name + "', room_number = " + roomNum + ", contact_number = '" + contact +
                "' WHERE reservation_id = " + id + ';';
        int rowsAffected = stmt.executeUpdate(sql);

        if (rowsAffected > 0) {
            System.out.println("Reservation updated successfully!");
            System.out.println();
        } else {
            System.out.println("Reservation update failed.");
            System.out.println();
        }
    }

    public static void deleteReservations(Connection conn, Statement stmt, Scanner sc) throws SQLException{
        System.out.print("Enter reservation ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM Reservations WHERE reservation_id = " + id;
        int rowsAffected = stmt.executeUpdate(sql);

        if (rowsAffected > 0) {
            System.out.println("Reservation deleted successfully!");
            System.out.println();
        } else {
            System.out.println("Reservation delete failed.");
            System.out.println();
        }
    }

    public static void exit() throws InterruptedException{
        System.out.print("Exiting System");
        for (int i=0; i<5; i++) {
            System.out.print(". ");
            Thread.sleep(500);
        }
        System.out.println();
        System.out.println("Thank You for using Hotel Reservation System!!!");
    }
}