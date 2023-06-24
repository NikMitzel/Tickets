package com.example.Ticketsystem.APIs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path = "api/user")
public class UserControler {
    private final UserService userService;

    @Autowired
    public UserControler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "checkLogin/{email}_{password}")
    public boolean checkLoginPassword(@PathVariable("email") String email, @PathVariable("password") String password){
        return userService.DBCheckLoginPassword(email,password);
    }

    @PostMapping(path = "get/{email}")
    public void getUser(@PathVariable("email") String email){
        userService.DBGetUser(email);
    }

    @PostMapping(path = "create/{name}_{birthdate}_{email}_{password}")
    public void createUser(@PathVariable("name") String name,@PathVariable("birthdate") String birthdate,@PathVariable("email") String email, @PathVariable("password") String password){
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        userService.createUser(new User(name,date1,email,password));
    }

    @PostMapping(path = "update/{name}_{birthdate}_{email}_{password}")
    public void updateUser(@PathVariable("name") String name,@PathVariable("birthdate") String birthdate,@PathVariable("email") String email, @PathVariable("password") String password){
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        userService.updateUser(new User(name,date1,email,password));
    }
}