import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//localhost:1524/XE"; // service name format
        String user = "SYSTEM";
        String password = "tiger";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // ensure driver is loaded
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connection successful!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}