
package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.model.Playfield;
import com.example.huber_riedler_kovacevic_202223_snake.model.Position;
import com.example.huber_riedler_kovacevic_202223_snake.model.Snake;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class PlayfieldController {


    public Label timeLabel;
    public Label lengthLabel;
    public Button goButton;
    public BorderPane borderPane;
    public Circle[][] circles;
    public boolean gameRunning = true;
    public boolean foodSpawned = true;
    private Playfield playfield;
    private Position foodPosition;
    private Snake snake;

    public void initialize() {
        timeLabel.setText("");
        lengthLabel.setText("");
        GridPane gridPane = buildPlayfield();
        borderPane.setBottom(gridPane);

    }

    public void goButtonClick(ActionEvent actionEvent) throws InterruptedException {
        //  goButton.setVisible(false);
        // goButton.setDisable(true);
        play();
    }

    public void play() throws InterruptedException {
        playfield = new Playfield(Playfield.COLS, Playfield.ROWS);
        snake = new Snake();
        foodPosition = generateFood();
        AnimationTimerClass animationTimerClass = new AnimationTimerClass();
        goButton.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:    snake.changeDirection("W"); break;
                    case DOWN:  snake.changeDirection("S"); break;
                    case LEFT:  snake.changeDirection("A"); break;
                    case RIGHT: snake.changeDirection("D"); break;
                }
            }
        });
        animationTimerClass.start();


    }

    public GridPane buildPlayfield() {
        circles = new Circle[Playfield.COLS][Playfield.ROWS];

        GridPane gridPane = new GridPane();
        for (int i = 0; i < Playfield.COLS; i++) {
            for (int j = 0; j < Playfield.ROWS; j++) {
                circles[i][j] = new Circle(10, Color.GREEN);
                gridPane.add(circles[i][j], i, j);
            }
        }


        return gridPane;
    }

    public void updatePlayfield(int[][] playfield) {
        for (int i = 0; i < Playfield.COLS; i++) {
            for (int j = 0; j < Playfield.ROWS; j++) {
                if (playfield[i][j] == Playfield.EMPTY) {
                    circles[i][j].setFill(Color.GREEN);
                } else if (playfield[i][j] == Playfield.FOOD) {
                    circles[i][j].setFill(Color.RED);
                } else if (playfield[i][j] == Playfield.SNAKE) {
                    circles[i][j].setFill(Color.LIGHTBLUE);
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

    class AnimationTimerClass extends AnimationTimer {

        private long lastupdate = 0;

        @Override
        public void handle(long l) {
            if (l - lastupdate >= 12_000_000 && gameRunning) {
                playfield.setPosition(snake.getCurrentPosition().getCol(), snake.getCurrentPosition().getRow(), Playfield.SNAKE);
                for (int i = 0; i < snake.getLastPositions().size(); i++) {
                    playfield.setPosition(snake.getLastPositions().get(i).getCol(), snake.getLastPositions().get(i).getRow(), Playfield.SNAKE);
                }
                if (!foodSpawned) {
                    foodPosition = generateFood();
                    foodSpawned = true;
                }

                playfield.setPosition(foodPosition.getCol(), foodPosition.getRow(), Playfield.FOOD);

                updatePlayfield(playfield.getPlayfield());

                if (playfield.checkForFood(snake.getDirection(), snake.getCurrentPosition())) {
                    snake.eat();
                    foodSpawned = false;
                } else {
                    if (snake.getLastPositions().size() > 0) {
                        playfield.setPosition(snake.getLastPositions().get(0).getCol(), snake.getLastPositions().get(0).getRow(), Playfield.EMPTY);
                    }
                    snake.move();
                }

                if (snake.getCurrentPosition().getRow() >= Playfield.ROWS || snake.getCurrentPosition().getRow() < 0 ||
                        snake.getCurrentPosition().getCol() >= Playfield.COLS || snake.getCurrentPosition().getCol() < 0) {
                    gameRunning = false;
                }

                    try {
                        Thread.sleep(240);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
            lastupdate = l;
        }
    }

}
