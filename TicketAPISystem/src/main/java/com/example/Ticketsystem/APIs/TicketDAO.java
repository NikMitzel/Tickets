package com.example.Ticketsystem.APIs;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class TicketDAO {
    private static TicketDAO personDAO = new TicketDAO();

    public static TicketDAO getInstance() {
        return personDAO;
    }

    private Connection dbVerbindung;

    private TicketDAO() {
        dbVerbindung = DBManager.getInstance().getConnection();
    }

    //get all //tested
    public ArrayList<Ticket> DBGetOpenTickets(Date date){
        long milliseconds = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(milliseconds);
        ArrayList<Ticket> r = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("SELECT * FROM tickets WHERE DATE(date) = '"+ sqlDate +"' AND user_email IS NULL ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Time mysqlTime = resultSet.getTime("time");
                java.util.Date javaTime = new java.util.Date(mysqlTime.getTime());

                Date mysqlDate = resultSet.getDate("date");
                java.util.Date javaDate = new java.util.Date(mysqlDate.getTime());

                // Gemeinsames Date-Objekt erstellen
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(javaDate);

                Calendar timeCalendar = Calendar.getInstance();
                timeCalendar.setTime(javaTime);

                calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
                calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
                calendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));

                java.util.Date combinedDate = calendar.getTime();

                r.add(new Ticket(resultSet.getInt(1), combinedDate ,resultSet.getString(4),resultSet.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
    //book //tested
    public void DBBookTicket(String email, int ticketId){
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("UPDATE tickets SET user_email = ? WHERE id = ?;");
            preparedStatement.setString(1,email);
            preparedStatement.setInt(2,ticketId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //cancel //tested
    public void DBCancelTicket(int ticketId){
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("UPDATE tickets SET user_email = null WHERE id = ?;");
            preparedStatement.setInt(1,ticketId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get books //tested
    public ArrayList<Ticket> DBGetBookedTickets(Date date){
        long milliseconds = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(milliseconds);
        ArrayList<Ticket> r = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("SELECT * FROM tickets WHERE DATE(date) = '"+ sqlDate +"' AND user_email IS NOT NULL ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Time mysqlTime = resultSet.getTime("time");
                java.util.Date javaTime = new java.util.Date(mysqlTime.getTime());

                Date mysqlDate = resultSet.getDate("date");
                java.util.Date javaDate = new java.util.Date(mysqlDate.getTime());

                // Gemeinsames Date-Objekt erstellen
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(javaDate);

                Calendar timeCalendar = Calendar.getInstance();
                timeCalendar.setTime(javaTime);

                calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
                calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
                calendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));

                java.util.Date combinedDate = calendar.getTime();
                r.add(new Ticket(resultSet.getInt(1), combinedDate,resultSet.getString(4),resultSet.getString(5)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    //add frequency system //tested
    public void DBCreateTickets(Date from, Date to, int everyMin){
        //number of tickets = (from+to)/everyInMin
        long fromMilliseconds = from.getTime();
        long toMilliseconds = to.getTime();
        long everyMilliseconds = everyMin * 60000L;
        long numberOfTickets = (toMilliseconds - fromMilliseconds) / everyMilliseconds;
        try {
            for (int i = 0; i < numberOfTickets; i++) {
                //nextTime = from + i*everyInMin
                long nextTime = fromMilliseconds + i * everyMilliseconds;
                java.sql.Date sqlDate = new java.sql.Date(nextTime);
                java.sql.Time sqlTime = new java.sql.Time(nextTime);
                PreparedStatement preparedStatement = dbVerbindung.prepareStatement("INSERT INTO `tickets` (`date`, `time`, `code`, `user_email`) VALUES (?, ?, ?, null);");
                preparedStatement.setDate(1, sqlDate);
                preparedStatement.setTime(2, sqlTime);
                preparedStatement.setString(3, UUID.randomUUID().toString().toUpperCase().replaceAll("-", "").substring(0, 6));
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //add one //tested
    public void DBCreateSigelTicket(Date when){
        long milliseconds = when.getTime();
        java.sql.Date sqlDate = new java.sql.Date(milliseconds);
        java.sql.Time sqlTime = new java.sql.Time(milliseconds);
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("INSERT INTO `tickets` (`date`, `time`, `code`, `user_email`) VALUES (?, ?, ?, null);");
            preparedStatement.setDate(1,sqlDate);
            preparedStatement.setTime(2,sqlTime);
            preparedStatement.setString(3, UUID.randomUUID().toString().toUpperCase().replaceAll("-", "").substring(0, 6));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //My Tickets //tested
    public ArrayList<Ticket> DBGetMyTickets(User user){
        ArrayList<Ticket> r = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("SELECT * FROM Tickets WHERE user_email = '"+ user.getEmail()+"'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Time mysqlTime = resultSet.getTime("time");
                java.util.Date javaTime = new java.util.Date(mysqlTime.getTime());

                Date mysqlDate = resultSet.getDate("date");
                java.util.Date javaDate = new java.util.Date(mysqlDate.getTime());

                // Gemeinsames Date-Objekt erstellen
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(javaDate);

                Calendar timeCalendar = Calendar.getInstance();
                timeCalendar.setTime(javaTime);

                calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
                calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
                calendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));

                java.util.Date combinedDate = calendar.getTime();
                r.add(new Ticket(resultSet.getInt(1), combinedDate,resultSet.getString(4),resultSet.getString(5)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    //delete Past //tested
    public static void deletePastTickets() {
        Connection dbVerbindung = DBManager.getInstance().getConnection();
        LocalDate pastDate = LocalDate.now().minusDays(7);
        try {
            PreparedStatement preparedStatement = dbVerbindung.prepareStatement("DELETE FROM tickets WHERE date < ?");
            preparedStatement.setDate(1, java.sql.Date.valueOf(pastDate));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
