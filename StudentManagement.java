import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    private Connection getConnection() throws SQLException {
    String url = "jdbc:oracle:thin:@//localhost:1524/XE"; // service name format
    String user = "SYSTEM";
    String password = "tiger";

    return DriverManager.getConnection(url, user, password);
    }
    public void insertStudent(Student s) throws SQLException { 
        Connection con=getConnection();
         PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Students (rollNumber, firstName, lastName, course, rating, createdAt) VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, s.getRollNumber());
            ps.setString(2, s.getFirstName());
            ps.setString(3, s.getLastName());
            ps.setString(4, s.getCourse());
            ps.setDouble(5, s.getRating());
            ps.setTimestamp(6, java.sql.Timestamp.valueOf(s.getCreatedAt()));

            int rows = ps.executeUpdate();
            System.out.println(rows + " row(s) inserted.");
    }
    public void updateStudent(Student s) {
    String sql = "UPDATE Students SET firstName=?, lastName=?, course=?, rating=?, createdAt=? WHERE rollNumber=?";
    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, s.getFirstName());
        ps.setString(2, s.getLastName());
        ps.setString(3, s.getCourse());
        ps.setDouble(4, s.getRating());
        ps.setTimestamp(5, java.sql.Timestamp.valueOf(s.getCreatedAt()));
        ps.setInt(6, s.getRollNumber());
        int rows = ps.executeUpdate();
        System.out.println(rows + " row(s) updated.");
    } catch (Exception e) {
        e.printStackTrace();
    }
  
}
public void deleteStudent(int rollNumber) {
    String sql = "DELETE FROM Students WHERE rollNumber=?";
    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, rollNumber);
        int rows = ps.executeUpdate();
        System.out.println(rows + " row(s) deleted.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public List<Student> fetchStudents() {
    List<Student> students = new ArrayList<>();
    String sql = "SELECT rollNumber, firstName, lastName, course, rating, createdAt FROM Students";
    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Student s = new Student(
                rs.getInt("rollNumber"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("course"),
                rs.getDouble("rating")
            );
            students.add(s);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return students;
}
public Student fetchStudentByRollNumber(int rollNumber) {
    String sql = "SELECT * FROM Students WHERE rollNumber=?";
    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, rollNumber);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Student(
                rs.getInt("rollNumber"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("course"),
                rs.getDouble("rating")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}



public static void main(String[] args) {
    StudentManagement sm = new StudentManagement();
    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.println("\n--- Student Management Menu ---");
        System.out.println("1. Insert Student");
        System.out.println("2. Fetch All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        sc.nextLine(); // clear buffer

        switch (choice) {
            case 1:
                System.out.print("Enter Roll Number: ");
                int roll = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter First Name: ");
                String fname = sc.nextLine();
                System.out.print("Enter Last Name: ");
                String lname = sc.nextLine();
                System.out.print("Enter Course: ");
                String course = sc.nextLine();
                System.out.print("Enter Rating: ");
                double rating = sc.nextDouble();

                Student s;
                try {
                    s = new Student(roll, fname, lname, course, rating);
                    sm.insertStudent(s);
                } catch (InvalidDetailsException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case 2:
                sm.fetchStudents().forEach(System.out::println);
                break;
            case 3:
                // similar to insert, but call updateStudent()
                break;
            case 4:
                System.out.print("Enter Roll Number to delete: ");
                int delRoll = sc.nextInt();
                sm.deleteStudent(delRoll);
                break;
            case 5:
                System.out.println("Exiting...");
                sc.close();
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

  }
}
   

