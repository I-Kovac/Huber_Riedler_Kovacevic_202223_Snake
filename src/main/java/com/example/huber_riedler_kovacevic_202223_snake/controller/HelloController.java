package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class HelloController {

    public ComboBox difficulty;
    public ComboBox music;

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
    }

    public void playButtonClick(ActionEvent actionEvent) throws IOException {
        openGame();
    }



    public void openGame() throws IOException {

        String path = music.getValue().toString();
        Media media = new Media(new File("music\\"+path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
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
}
