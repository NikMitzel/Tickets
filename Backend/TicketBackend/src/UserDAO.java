import java.sql.Connection;

public class UserDAO {
    private static UserDAO userDAO = new UserDAO();
    private Connection dbVerbindung;

    private UserDAO() {
        dbVerbindung = DBManager.getInstance().getConnection();
    }

    public static UserDAO getInstance() {
        return userDAO;
    }

    public void DBCreateUser(User user) {
        long milliseconds = user.getBirthdate().getTime();
        java.sql.Date sqlDate = new java.sql.Date(milliseconds);
        try {
            dbVerbindung.prepareStatement("INSERT INTO user (name, birthdate, email, password)\" +\n" +
                    "VALUES (?,?,?)");
            /// TODO: 23-Jun-23 finish 
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
