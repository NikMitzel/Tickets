
import java.util.Date;

public class User {
    private int id;
    private String name;
    private Date birthdate;
    private String email;
    private String password;

    // Konstruktor
    public User(int id, String name, Date birthdate, String email, String password) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;

    }

}

