package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.HelloApplication;
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
    public MediaPlayer mediaPlayer;

    public Slider volume;
    public ToggleButton onoff;

    public void initialize() {
        File folder = new File("music");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                music.getItems().add(listOfFiles[i].getName());

            }
            music.setValue(listOfFiles[0].getName());
            difficulty.setValue("Noob");
            difficulty.getItems().addAll(
                    "Noob",
                    "Amateur",
                    "Pro"
            );
        }
        String path = "music/CFC_Geometry_Dash_Back_On_Track_.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        volume.setValue(mediaPlayer.getVolume() * 100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volume.getValue() / 100);
            }
        });
    }

    public void playButtonClick() throws IOException {

        mediaPlayer.stop();
        openGame();

    }

    public void openGame() throws IOException {

        String path = music.getValue().toString();
        Media media = new Media(new File("music\\" + path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("playfield.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PlayfieldController controller = fxmlLoader.<PlayfieldController>getController();
        controller.setDifficulty(difficulty.getValue());
        Stage stage = new Stage();
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    public void music_button(MouseEvent mouseEvent) {
        if (Objects.equals(onoff.getText(), "ON")) {
            onoff.setText("OFF");
            mediaPlayer.stop();
        } else {
            onoff.setText("ON");
            mediaPlayer.play();
        }

    }
}
