package com.example.huber_riedler_kovacevic_202223_snake.model;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Game Klasse besteht aus einem Spielfeld ein Status ob es pausiert ist, einem Highscore und einem Mediaplayer
 */
public class Game {
    private Playfield play;
    private boolean paused;
    private static int highscore;
    MediaPlayer mediaPlayer;

    /**
     *Setzen der Eigenschaften
     */
    public Game(Playfield playfield) {
        this.play = playfield;
        highscore = 0;
        paused = false;
    }

    /**
     *
     * @param value der Name der Datei von welcher das Lied gespielt werden soll
     * @param volume in welcher Lautstärke die Musik abgespielt werden soll, gesetzt im Menu
     * Lied in SChleife laufen lassen
     * Lautstärke auf den im Menu gesetzten Wert geben
     */
    public void playMusic(String value, double volume) {
        Media media = new Media(new File("music\\" + value).toURI()+".mp3");
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volume);
    }

    public void stopmusic() {
            mediaPlayer.stop();
    }

    public boolean isPaused() {
        return paused;
    }
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Playfield getPlayfield() {
        return play;
    }

    public static int getHighscore() {
        return highscore;
    }
    public static void setHighscore(int highscore) {
        Game.highscore = highscore;
    }
}
