import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TicketDAO {
    private static TicketDAO personDAO = new TicketDAO();

    public static TicketDAO getInstance() {
        return personDAO;
    }

    private Connection dbVerbindung;

    private TicketDAO() {
        dbVerbindung = DBManager.getInstance().getConnection();
    }



}
