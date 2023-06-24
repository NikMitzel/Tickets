package com.example.Ticketsystem.APIs;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void createUser(User user){
        UserDAO.getInstance().DBCreateUser(user);
    }

    public void updateUser(User user){
        UserDAO.getInstance().DBUpdateUser(user);
    }

    public boolean DBCheckLoginPassword(String email, String password){
        return UserDAO.getInstance().DBCheckLoginPassword(email, password);
    }

    public User DBGetUser(String email){
        return UserDAO.getInstance().DBGetUser(email);
    }
}
