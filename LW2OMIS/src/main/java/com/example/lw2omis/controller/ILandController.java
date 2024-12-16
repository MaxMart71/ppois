package com.example.lw2omis.controller;

import com.example.lw2omis.entity.land.Land;

import java.util.List;

public interface ILandController {
    List<Land> getAll();
    Land getByType(String type);
    void addType(List<String> smth);
    void updateLand(Integer square);
    void deleteLand(Integer id);
    void get_repo(String name);
}
