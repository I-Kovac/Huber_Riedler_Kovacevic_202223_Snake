package com.example.huber_riedler_kovacevic_202223_snake.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Objects;

public class Menu {

    public MediaPlayer mediaPlayer;
    public static final int PRO = 20;
    public static final int AMATEUR = 50;
    public static final int NOOB = 80;


    public ComboBox addListofFiles(ComboBox comboBox){

        File folder = new File("music");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                comboBox.getItems().add(listOfFiles[i].getName().substring(0,listOfFiles[i].getName().indexOf(".")));
                comboBox.getItems().remove("IntroMusic");
            }
        }
        comboBox.setValue("Geometry Dash");
        return comboBox;
    }

    public void playMusic(){
        String path = "music/IntroMusic.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public ComboBox addDifficulties(ComboBox comboBox) {
        String[] strs = new String[]{"Noob", "Amateur", "Pro"};
        comboBox.getItems().addAll(strs);
        comboBox.setValue(strs[0]);
        return comboBox;
    }

    public void volumeslider(Slider volume) {

        volume.setValue(mediaPlayer.getVolume() * 100);
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volume.getValue() / 100);
            }
        });
    }

    public void stopMusic() {
        mediaPlayer.stop();
    }

    public void switchmusic(ToggleButton onoff) {
        if (Objects.equals(onoff.getText(), "ON")) {
            onoff.setText("OFF");
            mediaPlayer.stop();
        } else {
            onoff.setText("ON");
            mediaPlayer.play();
        }
    }

    public Object getdifficulty(ComboBox comboBox) {
        int difficulty=NOOB;
        if (Objects.equals(comboBox.getValue().toString(), "Pro")) {
            difficulty = PRO;
        } else if (Objects.equals(comboBox.getValue().toString(), "Amateur")) {
            difficulty = AMATEUR;
        }
        return difficulty;
    }

    public Boolean getMusicStatus(ToggleButton b) {
        boolean play=false;
        if (Objects.equals(b.getText(), "ON")) {
            play=true;
        }
        return play;
    }
}