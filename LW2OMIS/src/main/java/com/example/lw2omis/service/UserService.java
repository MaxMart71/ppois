package com.example.lw2omis.service;

import com.example.lw2omis.entity.user.User;
import com.example.lw2omis.repository.UserRepository;
import org.springframework.stereotype.Service;


public class UserService implements IUserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(User user){
        return false;
    };

    public boolean register(User user){return false;};
}
