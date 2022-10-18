package com.example.huber_riedler_kovacevic_202223_snake.model;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Playfield {
    public static final Paint SNAKECOLOR= Color.BLACK;
    public static final Paint BACKGROUNDCOLOR= Color.GREEN;
    public static final Paint FOODCOLOR= Color.RED;
    public static final int COLS = 45;
    public static final int ROWS = 35;
    public static final int EMPTY = 0;
    public static final int SNAKE = 1;
    public static final int FOOD = 2;
    public Snake snake;
    public Position foodPosition;
    public boolean foodSpawned;
    Rectangle[][] rectangles;
//
    private int playfield[][];

    public Playfield(int cols, int rows, Snake snake) {
        this.snake= snake;
        playfield = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                playfield[i][j] = EMPTY;
            }
        }
    }

    public GridPane buildPlayfield() {


        rectangles = new Rectangle[Playfield.COLS][Playfield.ROWS];

        GridPane gridPane = new GridPane();
        for (int i = 0; i < Playfield.COLS; i++) {
            for (int j = 0; j < Playfield.ROWS; j++) {
                rectangles[i][j] = new Rectangle(18,18, Color.GREEN);
                gridPane.add(rectangles[i][j], i, j);
            }
        }
        return gridPane;
    }

    public void updatePlayfield() {

        for (int i = 0; i < Playfield.COLS; i++) {
            for (int j = 0; j < Playfield.ROWS; j++) {
                if (playfield[i][j] == Playfield.EMPTY) {
                    rectangles[i][j].setFill(BACKGROUNDCOLOR);
                } else if (playfield[i][j] == Playfield.FOOD) {
                    rectangles[i][j].setFill(FOODCOLOR);
                } else if (playfield[i][j] == Playfield.SNAKE) {
                    rectangles[i][j].setFill(SNAKECOLOR);
                }
            }
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int[][] getPlayfield() {
        return playfield;
    }

    public boolean checkForSnake(){
        boolean ret = false;

        try {
            if (snake.getDirection() == Snake.UP) {
                if (playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow() - 1] == SNAKE) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.RIGHT) {
                if (playfield[snake.getCurrentPosition().getCol() + 1][snake.getCurrentPosition().getRow()] == SNAKE) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.DOWN) {
                if (playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow() + 1] == SNAKE) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.LEFT) {
                if (playfield[snake.getCurrentPosition().getCol() - 1][snake.getCurrentPosition().getRow()] == SNAKE) {
                    ret = true;
                }
            }
        } catch (Exception e){}
        return ret;
    }

    public boolean checkForFood() {
        boolean ret = false;

        try {
            if (snake.getDirection() == Snake.UP) {
                if (playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow() - 1] == FOOD) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.RIGHT) {
                if (playfield[snake.getCurrentPosition().getCol() + 1][snake.getCurrentPosition().getRow()] == FOOD) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.DOWN) {
                if (playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow() + 1] == FOOD) {
                    ret = true;
                }
            } else if (snake.getDirection() == Snake.LEFT) {
                if (playfield[snake.getCurrentPosition().getCol() - 1][snake.getCurrentPosition().getRow()] == FOOD) {
                    ret = true;
                }
            }
        } catch (Exception e){}
        return ret;
    }

    public void setFoodposition() {
        foodPosition= new Position(getRandomNumber(0, Playfield.COLS - 1), getRandomNumber(0, Playfield.ROWS - 1));
    }

    public void setSnakeState(int i) {
        playfield[snake.getLastPositions().get(i).getCol()][snake.getLastPositions().get(i).getRow()] = SNAKE;
    }

    public void setFoodState() {
        playfield[foodPosition.getCol()][foodPosition.getRow()] = FOOD;
    }

    public void setEmptyState() {
        playfield[snake.getLastPositions().get(0).getCol()][snake.getLastPositions().get(0).getRow()]=EMPTY;
    }

    public void setSnakefirstState() {
        playfield[snake.getCurrentPosition().getCol()][snake.getCurrentPosition().getRow()]=SNAKE;
    }
}
