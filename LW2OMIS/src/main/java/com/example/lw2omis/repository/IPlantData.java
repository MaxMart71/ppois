package com.example.lw2omis.repository;

import com.example.lw2omis.entity.plant.Plant;

import java.util.List;

public interface IPlantData {
    List<Plant> get_all();

    Plant get_plant(String name);

    void add_type(Plant plant);

    void update_plant(Plant plant);

    void delete_plant(Plant plant);
    List<String> get_plant_types();
}
