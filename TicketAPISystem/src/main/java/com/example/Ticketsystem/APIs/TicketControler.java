package com.example.Ticketsystem.APIs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/ticket")
public class TicketControler{

    private final TicketService ticketService;

    @Autowired
    public TicketControler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(path = "open/{date}")
    public List<Ticket> getOpenTicketsAt(@PathVariable("date") String date) {
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return ticketService.getOpenTicketsAt(date1);
    }

    @GetMapping(path = "booked/{date}")
    public List<Ticket> getBookedTicketsAt(@PathVariable("date") String date) {
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return ticketService.getBookedTicket(date1);
    }

    @GetMapping(path = "my/{email}")
    public List<Ticket>  getMyTickets(@PathVariable("email") String email){
        return ticketService.getMyTickets(new User("", new Date(),email,""));
    }

    @PostMapping(path = "add/{startDate}_{endDate}_{frequency}")
    public void addTickets(@PathVariable("startDate") String startdate, @PathVariable("endDate") String enddate, @PathVariable("frequency") int frequency){
        Date startdate1;
        Date enddate2;
        try {
            startdate1 = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
            enddate2 = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ticketService.createTickets(startdate1, enddate2, frequency);
    }

    @PostMapping(path = "add/{date}")
    public void addTicket(@PathVariable("date") String date){
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ticketService.createTicket(date1);
    }

    @PostMapping(path = "cancel/{id}")
    public void cancelTickets(@PathVariable("id") int id){
        ticketService.canelTicket(id);
    }

    @PostMapping(path = "cancel/{id}_{email}")
    public void bookTickets(@PathVariable("id") int id, @PathVariable("email") String email){
        ticketService.bookTicket(email, id);
    }
}
