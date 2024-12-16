package com.example.lw2omis.controller;

import com.example.lw2omis.entity.TypeOfPlant;
import com.example.lw2omis.entity.plant.Plant;
import com.example.lw2omis.repository.PlantRepository;
import com.example.lw2omis.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class PlantController extends Controller implements IPlantController{
    private PlantRepository repo;
    public PlantController(PlantRepository plantRepository){
        this.repo = plantRepository;
    }

    public LocalDate currentDate;


    @Override
    public List<Plant> getAll() {
        return null;
    }

    @Override
    public Plant getPlant(Integer id) {
        return null;
    }

    @Override
    public Plant getPlant(String name) {
        return null;
    }

    @Override
    public void addPlant(List<String> smth) {

    }

    @Override
    public void updatePlant(List<String> smth) {

    }

    @Override
    public void deletePlant(Integer id) {

    }

    @Override
    public List<TypeOfPlant> getPlantTypes() {
        return null;
    }

    @Override
    public boolean watering_dates_comparison() {
        return false;
    }
    @Override
    public void get_repo(String name){}

}
