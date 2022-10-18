package com.example.huber_riedler_kovacevic_202223_snake.model;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Game {
    public Playfield playfield;
    MediaPlayer mediaPlayer;

    public Game(Playfield playfield) {
        this.playfield = playfield;
            }


    public void playMusic(String value,double volume){
        Media media = new Media(new File("music\\" + value).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volume);
    }
}
