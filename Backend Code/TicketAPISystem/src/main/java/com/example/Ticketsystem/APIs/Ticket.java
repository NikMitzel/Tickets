package com.example.Ticketsystem.APIs;

import java.util.Date;

public class Ticket {
    private int id;
    private Date date;
    private String code;
    private String userEmail;

    public Ticket(int id, Date date, String code, String userEmail) {
        this.id = id;
        this.date = date;
        this.code = code;
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
