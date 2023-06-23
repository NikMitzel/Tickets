import java.sql.*;
import java.util.Date;


public class DBManager {
    //Prepare connection
    private static final Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = connection();
    }

    public static Connection connection() {
        String url = "jdbc:mysql://127.0.0.1:3306/Ticket";
        String username = "root";
        String password = "root8";

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public DBManager() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "birthdate DATE NOT NULL," +
                    "email VARCHAR(50) NOT NULL," +
                    "password VARCHAR(50) NOT NULL" +
                    ");");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tickets (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "date_time DATETIME NOT NULL," +
                    "free_spots INT NOT NULL" +
                    ");");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user_tickets (" +
                    "user_id INT," +
                    "ticket_id INT," +
                    "PRIMARY KEY (user_id, ticket_id)," +
                    "FOREIGN KEY (user_id) REFERENCES user(id)," +
                    "FOREIGN KEY (ticket_id) REFERENCES tickets(id)" +
                    ");");
        } catch (Exception e) {
            System.out.println(e + " - in CreateTable");
        }
    }

    //SQL statements:
    public void DBCreateUser(String name, Date birthdate, String EMail, String password) {
        long milliseconds = birthdate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(milliseconds);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO user (name, birthdate, email, password)" +
                    "VALUES ('" + name + "', '" + sqlDate + "', '" + EMail + "', '" + password + "')");
        } catch (Exception e) {
            System.out.println(e + " - in CreateUser");
        }
    }
    public void DBCreateTicket(Date date, int free_spots) {
        long milliseconds = date.getTime();
        Timestamp sqlTimestamp = new Timestamp(milliseconds);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO user (date_time, free_spots)" +
                    "VALUES ('" + sqlTimestamp + "', '" + free_spots + "')");
        } catch (Exception e) {
            System.out.println(e + " - in CreateUser");
        }
    }

    public void DBBookTicket() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO user (date_time, free_spots)" +
                    "VALUES ('" + sqlTimestamp + "', '" + free_spots + "')");
        } catch (Exception e) {
            System.out.println(e + " - in CreateUser");
        }
    }
}
