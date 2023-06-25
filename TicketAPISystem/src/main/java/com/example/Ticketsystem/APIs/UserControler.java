package com.example.Ticketsystem.APIs;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/user")
public class UserControler {
    private static final Gson gson = new Gson();
    private final UserService userService;

    @Autowired
    public UserControler(UserService userService) {
        this.userService = userService;
    }


    //working
    @PostMapping("/checkLogin")
    public ResponseEntity<Boolean> checkLoginPassword(@RequestBody Login json){

        boolean b = userService.DBCheckLoginPassword(json.getEmail(), json.getPassword());

        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    //working
    @PostMapping("/register")
    public void register(@RequestBody Register json){
        userService.createUser(new User(json.getName(),json.getBirthdate(), json.getEmail(), json.getPassword()));
    }

    //working
    @GetMapping("/getName/{email}")
    public User getName(@PathVariable("email") String email){
        return userService.DBGetUser(email);
    }

    //working
    @PostMapping(path = "/update")
    public void updateUser(@RequestBody UpdateUser json){
        userService.updateUser(new User(json.getName(),json.getBirthdate(), json.getEmail(), json.getPassword()));
    }
}