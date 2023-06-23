import java.util.Date;

public class Ticket {
    private int id;
    private Date date;
    private String code;

    public Ticket(int id, Date date, String code) {
        this.id = id;
        this.date = date;
        this.code = code;
    }
}
