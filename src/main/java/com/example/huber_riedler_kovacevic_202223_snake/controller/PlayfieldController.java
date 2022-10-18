
package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.model.Playfield;
import com.example.huber_riedler_kovacevic_202223_snake.model.Snake;
import com.example.huber_riedler_kovacevic_202223_snake.model.Game;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class PlayfieldController {
    public Game game;
    public Label lengthLabel;
    public Button goButton;
    public BorderPane borderPane;
    public boolean gameRunning = true;
    public boolean foodSpawned = true;
    public int difficulty = 250;


    public void initialize() throws InterruptedException {
        game = new Game(new Playfield(Playfield.COLS,Playfield.ROWS,new Snake()));
        lengthLabel.setText("");
        GridPane gridPane = game.playfield.buildPlayfield();
        borderPane.setBottom(gridPane);
    }

    public void goButtonClick() throws InterruptedException {
        play();
    }

    public void play() throws InterruptedException {
        game.playfield.setFoodposition();
        AnimationTimerClass animationTimerClass = new AnimationTimerClass();
        animationTimerClass.start();
    }

    public GridPane buildPlayfield() {
        rectangles = new Rectangle[Playfield.COLS][Playfield.ROWS];

        GridPane gridPane = new GridPane();
        for (int i = 0; i < Playfield.COLS; i++) {
            for (int j = 0; j < Playfield.ROWS; j++) {
                rectangles[i][j] = new Rectangle(18, 18, Color.GREEN);
                gridPane.add(rectangles[i][j], i, j);
            }
        }


        return gridPane;
    }

    public void updatePlayfield(int[][] playfield) {
        for (int i = 0; i < Playfield.COLS; i++) {
            for (int j = 0; j < Playfield.ROWS; j++) {
                if (playfield[i][j] == Playfield.EMPTY) {
                    rectangles[i][j].setFill(Color.GREEN);
                } else if (playfield[i][j] == Playfield.FOOD) {
                    rectangles[i][j].setFill(Color.RED);
                } else if (playfield[i][j] == Playfield.SNAKE) {
                    rectangles[i][j].setFill(Color.BLACK);
                }
            }
        }
    }

    public Position generateFood() {
        return new Position(getRandomNumber(0, Playfield.COLS - 1), getRandomNumber(0, Playfield.ROWS - 1));
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void setDifficulty(Object value) {
        if (Objects.equals(value.toString(), "Pro")) {
            difficulty = PRO;
        } else if (Objects.equals(value.toString(), "Amateur")) {
            difficulty = AMATEUR;
        } else if (Objects.equals(value.toString(), "Noob")) {
            difficulty = NOOB;
        }

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
                if (!foodSpawned) {
                    game.playfield.setFoodposition();
                    game.playfield.foodSpawned=true;
                }

                game.playfield.setFoodState();

                game.playfield.updatePlayfield();

                if (game.playfield.checkForFood()) {
                    game.playfield.snake.eat();
                    game.playfield.foodSpawned=true;
                } else {
                    if (game.playfield.snake.getLastPositions().size() > 0) {
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
            }
            lastupdate = l;
        }
    }

    public void setDifficulty(Object value) {
        difficulty= (int) value;
    }

    public void setLengthLabel(int lengthLabel) {
        this.lengthLabel.setText(String.valueOf(lengthLabel));
    }

    public void playMusic(String value,double volume,boolean play) {
        if(play) {
            game.playMusic(value, volume);
        }
    }

}
