package com.example.lw2omis.controller;

import com.example.lw2omis.repository.UserRepository;

public class UserController extends Controller implements IUserController{
    private UserRepository repo;
    public UserController(UserRepository userRepository){
        this.repo = userRepository;
    }

    public boolean login(String login, String password){return false;}

    public boolean register(){return false;}

    private void get_repo(String name){}

}
