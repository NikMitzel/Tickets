
import java.util.ArrayList;
import java.util.Date;

public class User {
    private int id;
    private String name;
    private Date birthdate;
    private String email;
    private String password;
    private ArrayList<Integer> bookedTickets = new ArrayList<>();

    // Konstruktor
    public User(int id, String name, Date birthdate, String email, String password) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
    }

    public void addTicket(int ticketId){
        bookedTickets.add(ticketId);

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Integer> getBookedTickets() {
        return bookedTickets;
    }
}

