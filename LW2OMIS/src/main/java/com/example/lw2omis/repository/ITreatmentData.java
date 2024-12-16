package com.example.lw2omis.repository;

import com.example.lw2omis.entity.land.Land;
import com.example.lw2omis.entity.treatment.Treatment;

import java.util.List;

public interface ITreatmentData {
    List<Treatment> get_all();

    Land get_treatment(String name);
}
