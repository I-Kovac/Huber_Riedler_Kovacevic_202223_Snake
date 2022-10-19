
package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.model.Playfield;
import com.example.huber_riedler_kovacevic_202223_snake.model.Snake;
import com.example.huber_riedler_kovacevic_202223_snake.model.Game;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Besitzt Game in welchem das ganze SPiel abläuft
 * lengthlabel mit welchem der Score angezeigt wird
 * gamerunning um zu checken ob das Spiel noch weiterlaufen soll
 * difficulty um zu regeln wie lange der Thread nach jedem durch lauf pausieren soll
 *
 */

public class PlayfieldController {
    private Game game;
    @FXML
    private Label lengthLabel;

    @FXML
    private Button goButton;

    private boolean gameRunning = true;
    private int difficulty;

    @FXML
    private StackPane stackpane;

    private AnimationTimerClass animationTimerClass;
    private boolean music = false;

    /**
     * Neues game objekt erstellen
     * Spielfeld aufbauen und in Stackpane hinzufügen
     * Score label über das Spielfeld im Stackpane legen
     * @throws InterruptedException
     */
    public void initialize() throws InterruptedException {
        game = new Game(new Playfield(Playfield.COLS, Playfield.ROWS, new Snake()));
        lengthLabel=new Label();
        lengthLabel.setText("");
        lengthLabel.setStyle("-fx-font-size: 35");
        lengthLabel.setTextFill(Color.YELLOW);
    }

    /**
     * Auf Key input warten und entsprechende Methoden zum Tastenklick aufrufen
     * @throws InterruptedException
     */
    public void goButtonClick() throws InterruptedException {
        GridPane gridPane = game.getPlayfield().buildPlayfield();
        stackpane.getChildren().add(gridPane);
        stackpane.getChildren().add(lengthLabel);

        goButton.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (game.getPlayfield().getSnake().isHasMoved()) {
                if ((key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) && game.getPlayfield().getSnake().getDirection() != Snake.DOWN) {
                    game.getPlayfield().getSnake().changeDirection("W");
                } else if ((key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) && game.getPlayfield().getSnake().getDirection() != Snake.LEFT) {
                    game.getPlayfield().getSnake().changeDirection("D");
                } else if ((key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) && game.getPlayfield().getSnake().getDirection() != Snake.UP) {
                    game.getPlayfield().getSnake().changeDirection("S");
                } else if ((key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) && game.getPlayfield().getSnake().getDirection() != Snake.RIGHT) {
                    game.getPlayfield().getSnake().changeDirection("A");
                } else if (key.getCode() == KeyCode.ESCAPE) {
                    Stage stage = (Stage) goButton.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    if (music) {
                        game.stopmusic();
                    }
                } else if (key.getCode() == KeyCode.P){
                    game.setPaused(!game.isPaused());
                }
                if (!game.isPaused()){
                    game.getPlayfield().getSnake().setHasMoved(false);
                }

            }
        });
        play();
    }

    /**
     * Futter setzen und das updaten des Spielfelds starten
     * @throws InterruptedException
     */
    public void play() throws InterruptedException {
        game.getPlayfield().setFoodposition();
        animationTimerClass = new AnimationTimerClass();
        animationTimerClass.start();
    }

    class AnimationTimerClass extends AnimationTimer {

        private long lastupdate = 0;


        @Override
        public void handle(long l) {
            if (l - lastupdate >= 12_000_000 && gameRunning) {
                if (!game.isPaused()) {
                    game.getPlayfield().setSnakefirstState();
                    for (int i = 0; i < game.getPlayfield().getSnake().getLastPositions().size(); i++) {
                        game.getPlayfield().setSnakeState(i);
                    }
                    if (!game.getPlayfield().isFoodSpawned()) {
                        game.getPlayfield().setFoodposition();
                        game.getPlayfield().setFoodSpawned(true);
                    }

                    game.getPlayfield().setFoodState();

                    game.getPlayfield().updatePlayfield();

                    if (game.getPlayfield().checkForSnake()) {
                        gameRunning = false;
                    }

                    if (game.getPlayfield().checkForFood() && gameRunning) {
                        game.getPlayfield().getSnake().eat();
                        game.getPlayfield().setFoodSpawned(false);
                    } else {
                        if (game.getPlayfield().getSnake().getLastPositions().size() > 0 && gameRunning) {
                            game.getPlayfield().setEmptyState();
                        }

                        game.getPlayfield().getSnake().move();

                    }

                    if (game.getPlayfield().getSnake().getCurrentPosition().getRow() == Playfield.ROWS - 1 || game.getPlayfield().getSnake().getCurrentPosition().getRow() == 0 ||
                            game.getPlayfield().getSnake().getCurrentPosition().getCol() == Playfield.COLS - 1 || game.getPlayfield().getSnake().getCurrentPosition().getCol() == 0) {
                        gameRunning = false;
                    }
                    setLengthLabel(game.getPlayfield().getSnake().getLength());
                    try {
                        Thread.sleep(difficulty);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (!gameRunning) {
                Game.setHighscore(game.getPlayfield().getSnake().getLength());
                animationTimerClass.stop();
                HelloController.labelHighscore.setText("Highscore: "+Game.getHighscore());
                    Stage stage = (Stage) goButton.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    if (music) {
                        game.stopmusic();

                }


            }
            lastupdate = l;
            goButton.requestFocus();
        }
    }

    public void setDifficulty(Object value) {
        difficulty = (int) value;
    }

    public void setLengthLabel(int lengthLabel) {
        this.lengthLabel.setText(String.valueOf(lengthLabel));
    }

    public void playMusic(String value, double volume, boolean play) {
        if (play) {
            music = true;
            game.playMusic(value, volume);
        }
    }

}
