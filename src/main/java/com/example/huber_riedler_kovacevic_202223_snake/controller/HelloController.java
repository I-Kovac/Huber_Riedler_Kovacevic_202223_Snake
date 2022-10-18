package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.HelloApplication;
import com.example.huber_riedler_kovacevic_202223_snake.model.Menu;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class HelloController {

    public ComboBox difficulty;
    public ComboBox music;
    public Menu menu;
    public Slider volume;
    public ToggleButton onoff;

    public void initialize() {

        menu = new Menu();

        music = menu.addListofFiles(music);

        difficulty = menu.addDifficulties(difficulty);

        menu.playMusic();

        menu.volumeslider(volume);


    }

    public void playButtonClick() throws IOException {
        menu.stopMusic();
        openGame();
    }

    public void openGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("playfield.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PlayfieldController controller = fxmlLoader.<PlayfieldController>getController();
        controller.setDifficulty(menu.getdifficulty(difficulty));
        controller.playMusic(music.getValue().toString(),menu.mediaPlayer.getVolume(),menu.getMusicStatus(onoff));
        Stage stage = new Stage();
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    public void music_button(MouseEvent mouseEvent) {
        menu.switchmusic(onoff);
    }
}
