import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc_demo";
        String user = "root";
        String pass = "Durga@2006";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        String sql = "INSERT INTO students (name, email) VALUES (?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);

            int rows = ps.executeUpdate();
            System.out.println(rows + " record inserted");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}