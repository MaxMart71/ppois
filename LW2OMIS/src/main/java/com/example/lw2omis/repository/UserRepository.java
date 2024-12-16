package com.example.lw2omis.repository;


import com.example.lw2omis.db.DBSession;
import com.example.lw2omis.entity.land.Land;
import com.example.lw2omis.entity.plant.Plant;
import com.example.lw2omis.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserData{

    private DBSession session;

    public UserRepository(){
        this.session = new DBSession();
    };
    public List<User> get_all(){return null;};

    public Land get_user(Integer id){return null;};

    public void create_user(User user){};

    public void update_user(User user){};

    public void delete_user(Integer id){};
    public void get_access_level(Integer id){};
    public void get_access_level(User user){};

}