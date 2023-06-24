import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private static UserDAO userDAO = new UserDAO();
    private Connection dbVerbindung;

    private UserDAO() {
        dbVerbindung = DBManager.getInstance().getConnection();
    }

    public static UserDAO getInstance() {
        return userDAO;
    }

    //add user //tested
    public void DBCreateUser(User user) {
        long milliseconds = user.getBirthdate().getTime();
        java.sql.Date sqlDate = new java.sql.Date(milliseconds);
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("INSERT INTO user (name, birthdate, email, password) VALUES (?,?,?,?)");
                preparedStatement.setString(1,user.getName());
                preparedStatement.setDate(2,sqlDate);
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //update user //tested
    public void DBUpdateUser(User user){
        long milliseconds = user.getBirthdate().getTime();
        java.sql.Date sqlDate = new java.sql.Date(milliseconds);
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("UPDATE user SET name = ?, birthdate = ?, password = ? WHERE email = ?;");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //checkLogin //tested
    public boolean DBCheckLoginPassword(String email, String password){
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("SELECT password FROM user WHERE email = ?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                if (resultSet.getString(1).equals(password)){
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //getUser //tested
    public User DBGetUser(String email){
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("SELECT * FROM user WHERE email = ?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new User(resultSet.getString(1),resultSet.getDate(2),resultSet.getString(3),resultSet.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
