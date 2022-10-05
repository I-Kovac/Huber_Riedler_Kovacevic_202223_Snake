package com.example.huber_riedler_kovacevic_202223_snake.model;


import java.util.Arrays;

public class Playfield {
    public static int UP = 0;
    public static int RIGHT = 1;
    public static int DOWN = 2;
    public static int LEFT = 3;
    Snake snake;




    public Playfield() {
        snake = new Snake();
        snake.currentPosition = new int[]{0, 0};
        snake.direction=DOWN;
    }


    public void changeDirection(String toChangeTo) {
        if (toChangeTo.equals("W")) {
            snake.direction = UP;
        } else if (toChangeTo.equals("D")) {
            snake.direction = RIGHT;
        } else if (toChangeTo.equals("S")) {
            snake.direction = DOWN;
        } else if (toChangeTo.equals("A")) {
            snake.direction = LEFT;
        }
    }


}
 class  MoveSnake implements Runnable {
    Snake snakeToMove;
    public MoveSnake(Snake snakeToUse) {
        snakeToMove=snakeToUse;
    }

    public void run() {
        try {
            if (true) {
                Thread.sleep(900);
                snakeToMove.move();
                System.out.println("Thread:"+ Arrays.toString(snakeToMove.currentPosition) +" "+ Arrays.toString(snakeToMove.lastPositions.get(0)));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}