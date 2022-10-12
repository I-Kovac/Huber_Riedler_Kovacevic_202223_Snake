package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {

    public void playButtonClick(ActionEvent actionEvent) throws IOException {
        openGame();
    }

    public void loadfxmlFile(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    public void openGame() throws IOException {
        loadfxmlFile("playfield.fxml");

    }
}
