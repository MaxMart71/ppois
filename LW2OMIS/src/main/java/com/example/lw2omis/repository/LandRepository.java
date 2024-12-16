package com.example.lw2omis.repository;

import com.example.lw2omis.db.DBSession;
import com.example.lw2omis.entity.land.Land;
import com.example.lw2omis.entity.plant.Plant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LandRepository implements ILandData{

    private DBSession session;

    public LandRepository(){
        this.session = new DBSession();
    };
    public List<Land> get_all(){return null;};

    public Land get_by_type(String type){return null;};

    public void add_type(Land land){};

    public void update_type(Land land){};

    public void delete_type(Land land){};

}
