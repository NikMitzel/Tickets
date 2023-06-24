package com.example.Ticketsystem.APIs;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    public List<Ticket> getOpenTicketsAt(Date date) {
        return TicketDAO.getInstance().DBGetOpenTickets(date);
    }

    public List<Ticket> getMyTickets(User user){
        return TicketDAO.getInstance().DBGetMyTickets(user);
    }

    public List<Ticket>  getBookedTicket(Date date){
        return TicketDAO.getInstance().DBGetBookedTickets(date);
    }

    public void bookTicket(String email, int TicketId){
        TicketDAO.getInstance().DBBookTicket(email, TicketId);
    }

    public void canelTicket(int id){
        TicketDAO.getInstance().DBCancelTicket(id);
    }

    public void createTicket(Date date){
        TicketDAO.getInstance().DBCreateSigelTicket(date);
    }

    public void createTickets(Date start, Date end, int frequencyMin){
        TicketDAO.getInstance().DBCreateTickets(start,end,frequencyMin);
    }
}
