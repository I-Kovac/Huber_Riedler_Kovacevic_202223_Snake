package com.example.huber_riedler_kovacevic_202223_snake.model;


public class Playfield {
    public static int UP = 0;
    public static int RIGHT = 1;
    public static int DOWN = 2;
    public static int LEFT = 3;
    public static int SNAKE = 10;
    public static int FOOD = 11;
    public static int OBSTACLE = 12;


    protected int direction;
    Snake snake;


    public Playfield() {
        direction = Playfield.RIGHT;
        snake = new Snake();
        snake.currentPosition = new int[]{0, 0};
    }


    public void changeDirection(String toChangeTo) {
        if (toChangeTo.equals("W")) {
            direction = UP;
        } else if (toChangeTo.equals("D")) {
            direction = RIGHT;
        } else if (toChangeTo.equals("S")) {
            direction = DOWN;
        } else if (toChangeTo.equals("A")) {
            direction = LEFT;
        }
    }

    public class MoveSnake implements Runnable {
        public void run() {
            try {if (true){
                Thread.sleep(500);

            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
