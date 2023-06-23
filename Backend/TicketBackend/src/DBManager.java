import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static DBManager dbManager = new DBManager();
    private Connection connection = null;
    private String dbUrl = null;

    private DBManager() {
        initConnection("localhost", 3306, "Ticketsystem", "root", "root8");
        try {
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS user (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "birthdate DATE NOT NULL," +
                    "email VARCHAR(50) NOT NULL," +
                    "password VARCHAR(50) NOT NULL" +
                    ");").executeUpdate();
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS tickets (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "date_time DATETIME NOT NULL," +
                    "code VARCHAR(50) NOT NULL," +
                    "user_id INT," +
                    "FOREIGN KEY (user_id) REFERENCES user(id)" +
                    "ON DELETE CASCADE" +
                    ");").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBManager getInstance() {
        return dbManager;
    }

    public void removeConnection() {
        if (dbManager != null && this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void initConnection(String host, int port, String schema, String user, String pwd) {
        this.dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + schema;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // mit MySQL verbinden
            connection = DriverManager.getConnection(dbUrl, user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
