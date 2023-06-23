import java.sql.Timestamp;
import java.util.Date;

public class _TestMain {
    public static void main(String[] args) {
        Date utilDate = new Date();

        // Convert java.util.Date to java.sql.Timestamp
        long milliseconds = utilDate.getTime();
        Timestamp sqlTimestamp = new Timestamp(milliseconds);

        // Display the formatted SQL datetime
        System.out.println("Formatted SQL datetime: " + sqlTimestamp);
    }
}
