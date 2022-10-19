package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.HelloApplication;
import com.example.huber_riedler_kovacevic_202223_snake.model.Game;
import com.example.huber_riedler_kovacevic_202223_snake.model.Menu;
import com.example.huber_riedler_kovacevic_202223_snake.model.Playfield;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Besitzt combobox zum auswählen des Schwierigkeitsgrades
 * Combobox zum auswählen der zuspielenden Musik
 * Menu welches erstellt wird beim Starten des Programms
 * Volume Slider zum einstellen der Lautstärke
 * ToggleButton zum ein und ausschalten der Musik
 * Highscore Label um den Highscore der akutellen Session anzuzeigen
 *
 */
public class HelloController {

    @FXML
    private ComboBox difficulty;
    @FXML
    private ComboBox music;
    @FXML
    private Menu menu;
    @FXML
    private Slider volume;
    @FXML
    private ToggleButton onoff;
    @FXML
    protected static Label labelHighscore;
    @FXML
    private VBox vBox;

    /**
     * Neues Menü erstellen; Werte in combobox setzen;
     * Musik abspielen; volumeslider aktivieren; Highscore label erstellen und setzen;
     */
    public void initialize() {

        menu = new Menu();

        music = menu.addListofFiles(music);

        difficulty = menu.addDifficulties(difficulty);

        menu.playMusic();

        menu.volumeslider(volume);

        labelHighscore=new Label();
        labelHighscore.setText("Highscore: "+ Game.getHighscore());
        labelHighscore.setStyle("-fx-font-size: 15");

        vBox.getChildren().add(labelHighscore);
    }

    /**
     * Musik pausieren und spiel starten
     * @throws IOException
     */
    public void playButtonClick() throws IOException {
        menu.stopMusic();
        openGame();
    }

    /**
     * Playfield fxml öffnen mit den gewählten werten der Comboboxen vom Menü im Controller.
     * @throws IOException
     */
    public void openGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("playfield.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),810,628);
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
