import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class jdbc {
    public static int score = 0;

    public jdbc(int score) throws ClassNotFoundException, SQLException {
        System.out.println("2222222");
        jdbc.score = score;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(dtf);
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "system";
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully");
        Statement statement = con.createStatement();
        System.out.println("insert");
        String query = "INSERT INTO leaderboard (Score , data) values(" + score + " , '" + time + "')";
        System.out.println("insert");
        statement.executeUpdate(query);
    }
}
