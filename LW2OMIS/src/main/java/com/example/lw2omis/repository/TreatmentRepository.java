package com.example.lw2omis.repository;


import com.example.lw2omis.db.DBSession;
import com.example.lw2omis.entity.land.Land;
import com.example.lw2omis.entity.treatment.Treatment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreatmentRepository implements ITreatmentData {

    private DBSession session;

    public TreatmentRepository(){
        this.session = new DBSession();
    };
    public List<Treatment> get_all(){return null;};

    public Land get_treatment(String name){return null;};


}
