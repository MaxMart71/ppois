package com.example.lw2omis.service;

import com.example.lw2omis.entity.user.User;

public interface IUserService {
    boolean login(User user);

    boolean register(User user);
}
