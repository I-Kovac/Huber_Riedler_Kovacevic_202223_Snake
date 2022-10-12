package com.example.huber_riedler_kovacevic_202223_snake.model;

public class Playfield {
    public static final int COLS = 10;
    public static final int ROWS = 10;
    public static final int EMPTY = 0;
    public static final int SNAKE = 1;
    public static final int FOOD = 2;

    private int playfield[][];

    public Playfield(int cols, int rows) {
        playfield = new int[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                playfield[i][j] = EMPTY;
            }
        }
    }

    public void setPosition(int col, int row, int state) {
        playfield[col][row] = state;
    }

    public int[][] getPlayfield() {
        return playfield;
    }

    public boolean checkForFood(int direction, Position position) {
        boolean ret = false;

        try {
            if (direction == Snake.UP) {
                if (playfield[position.getCol()][position.getRow() + 1] == FOOD) {
                    ret = true;
                }
            } else if (direction == Snake.RIGHT) {
                if (playfield[position.getCol() + 1][position.getRow()] == FOOD) {
                    ret = true;
                }
            } else if (direction == Snake.DOWN) {
                if (playfield[position.getCol()][position.getRow() - 1] == FOOD) {
                    ret = true;
                }
            } else if (direction == Snake.LEFT) {
                if (playfield[position.getCol() - 1][position.getRow()] == FOOD) {
                    ret = true;
                }
            }
        } catch (Exception e){}
        return ret;
    }
}
