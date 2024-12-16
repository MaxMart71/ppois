package com.example.lw2omis.repository;

import com.example.lw2omis.entity.land.Land;
import com.example.lw2omis.entity.user.User;

import java.util.List;

public interface IUserData {
    List<User> get_all();

    Land get_user(Integer id);

    void create_user(User user);

    void update_user(User user);

    void delete_user(Integer id);
    void get_access_level(Integer id);
    void get_access_level(User user);
}
