package com.example.huber_riedler_kovacevic_202223_snake.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class HelloController {
    public GridPane playfield;
    public Label points;
    public ToggleButton musicbutton;
    public Slider musicslider;


    public void initialize(){
        points.setText("1");
    }

}