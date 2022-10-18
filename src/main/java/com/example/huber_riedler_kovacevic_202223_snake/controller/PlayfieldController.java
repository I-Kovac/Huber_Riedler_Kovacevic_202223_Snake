
package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.model.Playfield;
import com.example.huber_riedler_kovacevic_202223_snake.model.Snake;
import com.example.huber_riedler_kovacevic_202223_snake.model.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PlayfieldController {
    public Game game;
    public Label lengthLabel;
    public Button goButton;
    public boolean gameRunning = true;
    public int difficulty;
    public Label scoreLabel;
    AnimationTimerClass animationTimerClass;
    public HBox hBox;
    private boolean music = false;


    public void initialize() throws InterruptedException {
        game = new Game(new Playfield(Playfield.COLS, Playfield.ROWS, new Snake()));
        lengthLabel.setText("");
        GridPane gridPane = game.playfield.buildPlayfield();
        hBox.getChildren().add(gridPane);
    }

    public void goButtonClick() throws InterruptedException {
        goButton.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (game.playfield.snake.isHasMoved()) {
                if ((key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) && game.playfield.snake.getDirection() != Snake.DOWN) {
                    game.playfield.snake.changeDirection("W");
                } else if ((key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) && game.playfield.snake.getDirection() != Snake.LEFT) {
                    game.playfield.snake.changeDirection("D");
                } else if ((key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) && game.playfield.snake.getDirection() != Snake.UP) {
                    game.playfield.snake.changeDirection("S");
                } else if ((key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) && game.playfield.snake.getDirection() != Snake.RIGHT) {
                    game.playfield.snake.changeDirection("A");
                } else if (key.getCode() == KeyCode.ESCAPE) {
                    Stage stage = (Stage) goButton.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    if (music) {
                        game.stopmusic();
                    }
                }
                game.playfield.snake.setHasMoved(false);
            }
        });
        play();
    }

    public void play() throws InterruptedException {
        game.playfield.setFoodposition();
        animationTimerClass = new AnimationTimerClass();
        animationTimerClass.start();
    }

    class AnimationTimerClass extends AnimationTimer {

        private long lastupdate = 0;

        @Override
        public void handle(long l) {
            if (l - lastupdate >= 12_000_000 && gameRunning) {
                game.playfield.setSnakefirstState();
                for (int i = 0; i < game.playfield.snake.getLastPositions().size(); i++) {
                    game.playfield.setSnakeState(i);
                }
                if (!game.playfield.foodSpawned) {
                    game.playfield.setFoodposition();
                    game.playfield.foodSpawned = true;
                }

                game.playfield.setFoodState();

                game.playfield.updatePlayfield();

                if (game.playfield.checkForSnake()) {
                    gameRunning = false;
                }

                if (game.playfield.checkForFood() && gameRunning) {
                    game.playfield.snake.eat();
                    game.playfield.foodSpawned = false;
                } else {
                    if (game.playfield.snake.getLastPositions().size() > 0 && gameRunning) {
                        game.playfield.setEmptyState();
                    }
                    game.playfield.snake.move();
                }

                if (game.playfield.snake.getCurrentPosition().getRow() >= Playfield.ROWS || game.playfield.snake.getCurrentPosition().getRow() < 0 ||
                        game.playfield.snake.getCurrentPosition().getCol() >= Playfield.COLS || game.playfield.snake.getCurrentPosition().getCol() < 0) {
                    gameRunning = false;
                }
                setLengthLabel(game.playfield.snake.getLength());
                try {
                    Thread.sleep(difficulty);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (!gameRunning) {
                Game.highscore = game.playfield.snake.getLength();
                animationTimerClass.stop();

                    Stage stage = (Stage) goButton.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    if (music) {
                        game.stopmusic();
                    }




            }
            lastupdate = l;
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
