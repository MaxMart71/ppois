package com.example.lw2omis.repository;

import com.example.lw2omis.db.DBSession;
import com.example.lw2omis.entity.plant.Plant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlantRepository implements IPlantData{

    private DBSession session;

    public PlantRepository(){
        this.session = new DBSession();
    };
    public List<Plant> get_all(){return null;};

    public Plant get_plant(String name){return null;};

    public void add_type(Plant plant){};

    public void update_plant(Plant plant){};

    public void delete_plant(Plant plant){};
    public List<String> get_plant_types(){return null;};

}
