package com.example.lw2omis.view;

import com.example.lw2omis.controller.Controller;
import com.example.lw2omis.entity.treatment.Treatment;

public class TreatmentView implements IView {
    private final Controller controller;
    private String title;

    public TreatmentView(Controller controller, String title) {
        this.controller = controller;
        this.title = title;
    }
    @Override
    public void getController() {

    }

    @Override
    public void build() {

    }

    @Override
    public void show() {

    }
}
