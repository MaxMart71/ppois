package com.example.lw2omis.repository;

import com.example.lw2omis.entity.land.Land;

import java.util.List;

public interface ILandData {
   List<Land> get_all();

    Land get_by_type(String type);

    void add_type(Land land);

    void update_type(Land land);

    void delete_type(Land land);
}
