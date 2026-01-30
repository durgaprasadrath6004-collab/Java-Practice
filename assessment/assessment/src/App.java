import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/durga";
        String user = "root";
        String pass = "Durga@2006";

        System.out.println("Welcome to Movie Watchlist App");
        System.out.println("1. Add question");
        System.out.println("2. Update question");
        System.out.println("3. delete question");
        System.out.println("4. read all question");
        System.out.println("5. Exit");

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    
         System.out.println("Enter the question :");
        String Question = sc.nextLine();
        System.out.println("Enter the option A :");
        String A = sc.nextLine();
        System.out.println("Enter the option B :");
        String B = sc.nextLine();
        System.out.println("Enter the option C :");
        String C = sc.nextLine();
        System.out.println("Enter the option D :");
        String D = sc.nextLine();
        System.out.println("Enter the correct answer :");
        String Answer = sc.nextLine();
        String sql = "INSERT INTO ONLINE_QUIZ (Question,A,B,C,D,Answer) VALUES (?,?,?,?,?,?)";
            try {
                Connection con = DriverManager.getConnection(url, user, pass);
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, Question);
                ps.setString(2, A);
                ps.setString(3, B);
                ps.setString(4, C);
                ps.setString(5, D);
                ps.setString(6, Answer);
                int rows = ps.executeUpdate();
                System.out.println(rows + " record inserted");
                
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
                    
                    break;
                case 2:
                    System.out.print("Enter question id to update: ");
                    int SlNo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new question: ");
                    String newQuestion = sc.nextLine();
                    System.out.print("Enter new option A: ");
                    String newA = sc.nextLine();
                    System.out.print("Enter new option B: ");
                    String newB = sc.nextLine();
                    System.out.print("Enter new option C: ");
                    String newC = sc.nextLine();
                    System.out.print("Enter new option D: ");
                    String newD = sc.nextLine();
                    System.out.print("Enter new correct answer: ");
                    String newAnswer = sc.nextLine();
                    String updateSql = "UPDATE ONLINE_QUIZ SET Question=?, A=?, B=?, C=?, D=?, Answer=? WHERE SlNo=?";
                    
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(updateSql);
                        ps.setString(1, newQuestion);
                        ps.setString(2, newA);
                        ps.setString(3, newB);
                        ps.setString(4, newC);
                        ps.setString(5, newD);
                        ps.setString(6, newAnswer);
                        ps.setInt(7, SlNo);
                        int rows = ps.executeUpdate();
                        System.out.println(rows + " record updated");
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Enter Question id to delete: ");
                    int id = sc.nextInt();
                    String deleteSql = "DELETE FROM ONLINE_QUIZ WHERE SlNo = ?";
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
                    String selectSql = "SELECT SlNo, Question, A, B, C, D, Answer FROM ONLINE_QUIZ ORDER BY SlNo ASC";
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(selectSql);
                        java.sql.ResultSet rs = ps.executeQuery();
                        System.out.println("ID\tQuestion\tA\tB\tC\tD\tAnswer");
                        while (rs.next()) {
                            int slNoResult = rs.getInt("SlNo");
                            String questionText = rs.getString("Question");
                            String optionA = rs.getString("A");
                            String optionB = rs.getString("B");
                            String optionC = rs.getString("C");
                            String optionD = rs.getString("D");
                            String answer = rs.getString("Answer");
                            System.out.println(slNoResult + "\t" + questionText + "\t" + optionA + "\t" + optionB + "\t" + optionC + "\t" + optionD + "\t" + answer);
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