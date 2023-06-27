package com.example.Ticketsystem.APIs;

public class Book {
    private String email;
    private int id;

    public Book(String email, int id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
