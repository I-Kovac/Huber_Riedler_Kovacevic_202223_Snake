package com.example.huber_riedler_kovacevic_202223_snake.model;

import javafx.scene.control.ComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SnakeGame {
    protected Playfield playfield;
    protected Snake snake;
    MediaPlayer mediaPlayer;

    public SnakeGame(Playfield playfield, Snake snake) {
        this.playfield = playfield;
        this.snake = snake;
    }


    public void playMusic(String value){
        Media media = new Media(new File("music\\" + value).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
