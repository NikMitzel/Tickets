
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String name;
    private Date birthdate;
    private String email;
    private String password;

    // Konstruktor
    public User(String name, Date birthdate, String email, String password) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
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
}

