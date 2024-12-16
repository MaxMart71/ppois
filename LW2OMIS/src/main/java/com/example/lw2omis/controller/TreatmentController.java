package com.example.lw2omis.controller;

import com.example.lw2omis.entity.treatment.Treatment;
import com.example.lw2omis.repository.TreatmentRepository;
import com.example.lw2omis.repository.UserRepository;

import java.util.List;

public class TreatmentController implements ITreatmentController{

    private TreatmentRepository repo;
    public TreatmentController(TreatmentRepository treatmentRepository){
        this.repo = treatmentRepository;
    }
    @Override
    public List<Treatment> getAll() {
        return null;
    }

    @Override
    public Treatment get_treatment(Integer id) {
        return null;
    }
}
