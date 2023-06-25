package com.example.Ticketsystem.APIs;

public class Register {
    private String name;
    private String birthdate;
    private String email;
    private String password;

    public Register(String name, String birthdate, String email, String password) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
