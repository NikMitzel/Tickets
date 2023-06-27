package com.example.Ticketsystem.APIs;

public class UpdateUser {
    private String email;
    private String name;
    private String birthdate;
    private String password;

    public UpdateUser(String name, String birthdate, String email,  String password) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getPassword() {
        return password;
    }
}
