import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;


public class _ConsoleVersion {
    static User user = null;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------Ticket System----------");
        System.out.println("choose action: \n 1-Login \n 2-Register \n 3-update personal data \n admin - for the admin login");
        System.out.print("> ");
        switch (scanner.nextLine()){
            case "1":
                System.out.print("EMail: ");
                String email = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                if(UserDAO.getInstance().DBCheckLoginPassword(email,password)){
                    user = UserDAO.getInstance().DBGetUser(email);
                    afterlogin();
                }else{
                    System.out.println("incorrect Password or unknown Email");
                }
                break;
            case "2":
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Birthdate yyyy-MM-dd: ");
                String birthdate = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = dateFormat.parse(birthdate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("EMail: ");
                String email2 = scanner.nextLine();
                System.out.print("Password: ");
                String password2 = scanner.nextLine();
                user = new User(name,date,email2,password2);
                UserDAO.getInstance().DBCreateUser(user);
                System.out.println("Login");
                System.out.print("EMail: ");
                String email3 = scanner.nextLine();
                System.out.print("Password: ");
                String password3 = scanner.nextLine();
                if(UserDAO.getInstance().DBCheckLoginPassword(email3,password3)){
                    afterlogin();
                }else{
                    System.out.println("incorrect Password or unknown Email");
                }
                break;
            case "3":
                System.out.print("EMail: ");
                String email4 = scanner.nextLine();
                System.out.print("Password: ");
                String password4 = scanner.nextLine();
                System.out.println("new information");
                if(UserDAO.getInstance().DBCheckLoginPassword(email4,password4)){
                    user = UserDAO.getInstance().DBGetUser(email4);
                    System.out.print("Name: ");
                    String name2 = scanner.nextLine();
                    System.out.print("Birthdate yyyy-MM-dd: ");
                    String birthdate2 = scanner.nextLine();
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date date2 = null;
                    try {
                        date2 = dateFormat2.parse(birthdate2);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print("Password: ");
                    String password5 = scanner.nextLine();
                    UserDAO.getInstance().DBUpdateUser(new User(name2,date2,email4,password5));
                }else{
                    System.out.println("incorrect Password or unknown Email");
                }
                break;
            case "admin":
                System.out.println("Admin Login");
                if (Objects.equals(scanner.nextLine(), "qwert")) {
                    adminPage();
                }
                break;
        }
        scanner.close();
    }

    private static void afterlogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("choose action: \n 1-Book Ticket \n 2-See your Tickets");
        System.out.print("> ");
        switch (scanner.nextLine()){
            case "1":
                System.out.println("---Choose a Date for the Ticket---");
                String pattern = "yyyy-MM-dd";
                System.out.print(pattern);
                System.out.print("> ");
                String dateString = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                Date date = null;
                try {
                    date = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                ArrayList<Ticket> tickets = TicketDAO.getInstance().DBGetOpenTickets(date);
                if (tickets.size() != 0){
                    for (Ticket ticket : tickets) {
                        System.out.println("id: "+ticket.getId() + "  at: " + ticket.getDate().toString());
                    }
                    System.out.println("-----Choose a Ticket ID-----");
                    System.out.print("> ");
                    String ticketid = scanner.nextLine();
                    TicketDAO.getInstance().DBBookTicket(user.getEmail(), Integer.valueOf(ticketid));
                } else {
                    System.out.println("there are no tickets for this date");
                }
                break;
            case "2":
                ArrayList<Ticket> myTickets = TicketDAO.getInstance().DBGetMyTickets(user);
                if (myTickets.size() != 0){
                    for (Ticket ticket : myTickets) {
                        System.out.println(ticket.getId() + "Ticket at:"+ ticket.getDate().toString() + "  ticket code:"+ticket.getCode());
                    }
                } else {
                    System.out.println("you have not booked any tickets");
                }
                System.out.println("cancel ticket?");
                System.out.print("id >");
                TicketDAO.getInstance().DBCancelTicket(Integer.parseInt(scanner.nextLine()));
                break;
        }
        scanner.close();
    }

    private static void adminPage(){
        String patternDate = "yyyy-MM-dd";
        String patternDateTime = "yyyy-MM-dd HH:mm";
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------Admin Page----------");
        System.out.println("choose action: \n 1-See Booked Tickets \n 2-Add one Ticket \n 3-Add a frequency of tickets \n 4-del old tickets");
        System.out.print("> ");
        switch (scanner.nextLine()){
            case "1":
                System.out.println("---Choose a Date for the Tickets---");
                System.out.print(patternDate);
                System.out.print("> ");
                String dateString = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
                Date date = null;
                try {
                    date = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                ArrayList<Ticket> tickets = TicketDAO.getInstance().DBGetBookedTickets(date);
                if (tickets.size() > 0){
                    for (Ticket ticket : tickets) {
                        System.out.println("id: "+ticket.getId() + "   at: " + ticket.getDate().toString()+ "   code: " + ticket.getCode() + "   E-Mail: "+ticket.getUserEmail());
                    }
                } else {
                    System.out.println("there are no tickets booked for this date");
                }
                break;
            case "2":
                System.out.print("DateTime for the Ticket ");
                System.out.println(patternDateTime);
                System.out.print("> ");
                String dateString2 = scanner.nextLine();
                SimpleDateFormat dateFormat2 = new SimpleDateFormat(patternDateTime);
                Date date2 = null;
                try {
                    date2 = dateFormat2.parse(dateString2);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                TicketDAO.getInstance().DBCreateSigelTicket(date2);
                break;
            case "3":
                System.out.print("Start DateTime ");
                System.out.println(patternDateTime);
                System.out.print("> ");
                String dateStringStart = scanner.nextLine();
                SimpleDateFormat dateFormatStart = new SimpleDateFormat(patternDateTime);
                Date dateStart = null;
                try {
                    dateStart = dateFormatStart.parse(dateStringStart);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("End DateTime ");
                System.out.println(patternDateTime);
                System.out.print("> ");
                String dateStringEnd = scanner.nextLine();
                SimpleDateFormat dateFormatEnd = new SimpleDateFormat(patternDateTime);
                Date dateEnd = null;
                try {
                    dateEnd = dateFormatEnd.parse(dateStringEnd);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("every(inMin): ");
                System.out.print("> ");
                String frequency = scanner.nextLine();
                TicketDAO.getInstance().DBCreateTickets(dateStart,dateEnd,Integer.parseInt(frequency));
                break;
            case "4":
                TicketDAO.getInstance().deletePastTickets();
                break;
        }
        scanner.close();
    }
}
