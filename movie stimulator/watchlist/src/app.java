import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class app {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/Movie_watchlist_stimulator";
        String user = "root";
        String pass = "Durga@2006";

        System.out.println("Welcome to Movie Watchlist App");
        System.out.println("1. Add Movie");
        System.out.println("2. Update Movie");
        System.out.println("3. delete Movie");
        System.out.println("4. read all Movies");
        System.out.println("5. Exit");

        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    
                    System.out.print("Enter movie name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter gounrae: ");
                    String gounrae = sc.nextLine();
                    System.out.print("Enter rating: ");
                    int rating= sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter status(watched/to watched): ");
                    String status = sc.nextLine();
                    String sql = "INSERT INTO watchlist (movie_name, gounrae, rating, status_) VALUES (?, ?, ?, ?)";
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, name);
                        ps.setString(2, gounrae);
                        ps.setInt(3, rating);
                        ps.setString(4, status);
                        int rows = ps.executeUpdate();
                        System.out.println(rows + " record inserted");
                        
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        sc.close();
        }
                    
                    break;
                case 2:
                    System.out.print("Enter movie id to update: ");
                    int sl_no = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new movie name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new gounrae: ");
                    String newgounrae = sc.nextLine();
                    System.out.print("Enter new rating: ");
                    int newrating= sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter new status (watched/to watched): ");
                    String newStatus = sc.nextLine();
                    String updateSql = "UPDATE watchlist SET movie_name = ?, gounrae = ?, rating = ?, status_ = ? WHERE sl_no = ?";
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(updateSql);
                        ps.setString(1, newName);
                        ps.setString(2, newgounrae);
                        ps.setInt(3, newrating);
                        ps.setString(4, newStatus);
                        ps.setInt(5, sl_no);
                        int rows = ps.executeUpdate();
                        System.out.println(rows + " record updated");
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Enter movie id to delete: ");
                    int id = sc.nextInt();
                    String deleteSql = "DELETE FROM watchlist WHERE sl_no = ?";
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(deleteSql);
                        ps.setInt(1, id);
                        int rows = ps.executeUpdate();
                        System.out.println(rows + " record deleted");
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    String selectSql = "SELECT sl_no, movie_name, gounrae, rating, status_ FROM watchlist ORDER BY sl_no ASC";
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(selectSql);
                        java.sql.ResultSet rs = ps.executeQuery();
                        System.out.println("ID\tName\tGenre\tRating\tStatus");
                        while (rs.next()) {
                            int movieId = rs.getInt("sl_no");
                            String movieName = rs.getString("movie_name");
                            String genre = rs.getString("gounrae");
                            int movieRating = rs.getInt("rating");
                            String movieStatus = rs.getString("status_");
                            System.out.println(movieId + "\t" + movieName + "\t" + genre + "\t" + movieRating + "\t" + movieStatus);
                        }
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 

                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}