package com.example.lw2omis.view;

import com.example.lw2omis.controller.Controller;

public class OldPlantView implements IView{
    private final Controller controller;
    private String title;

    public OldPlantView(Controller controller, String title) {
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

    public void changeBackground(Integer color){}
}
