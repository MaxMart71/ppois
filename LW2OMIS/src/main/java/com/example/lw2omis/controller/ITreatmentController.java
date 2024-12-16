package com.example.lw2omis.controller;

import com.example.lw2omis.entity.treatment.Treatment;

import java.util.List;

public interface ITreatmentController {
    List<Treatment> getAll();
    Treatment get_treatment(Integer id);
}
