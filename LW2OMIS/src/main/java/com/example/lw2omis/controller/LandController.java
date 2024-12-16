package com.example.lw2omis.controller;

import com.example.lw2omis.entity.land.Land;
import com.example.lw2omis.repository.LandRepository;
import com.example.lw2omis.repository.UserRepository;

import java.util.List;

public class LandController implements ILandController{

    private LandRepository repo;
    public LandController(LandRepository landRepository){
        this.repo = landRepository;
    }
    @Override
    public List<Land> getAll() {
        return null;
    }

    @Override
    public Land getByType(String type) {
        return null;
    }

    @Override
    public void addType(List<String> smth) {

    }

    @Override
    public void updateLand(Integer square) {

    }

    @Override
    public void deleteLand(Integer id) {

    }

    @Override
    public void get_repo(String name) {

    }
}
