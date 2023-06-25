package com.example.Ticketsystem.APIs;

public class Canceler {
    private int  id;

    public Canceler(String email, int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
