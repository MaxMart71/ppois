package com.example.lw2omis.controller;

import com.example.lw2omis.entity.TypeOfPlant;
import com.example.lw2omis.entity.plant.Plant;

import java.util.List;

public interface IPlantController {
    List<Plant> getAll();
    Plant getPlant(Integer id);
    Plant getPlant(String name);
    void addPlant(List<String> smth);

    void updatePlant(List<String> smth);
    void deletePlant(Integer id);
    List<TypeOfPlant> getPlantTypes();
    boolean watering_dates_comparison();
    void get_repo(String name);
}
