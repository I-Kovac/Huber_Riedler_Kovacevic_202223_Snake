package com.example.huber_riedler_kovacevic_202223_snake.model;

import java.util.ArrayList;

public class Snake {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    private int length;
    private int direction;
    private Position currentPosition;
    private ArrayList<Position> lastPositions = new ArrayList<>();

    public Snake() {
    length = 2;
    direction = RIGHT;
    currentPosition = new Position(0, 0);
    }

    public int getLength() {
        return length;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public ArrayList<Position> getLastPositions() {
        return lastPositions;
    }

    public void eat(){
        length++;
        move();
    }

    public int getDirection() {
        return direction;
    }

    public synchronized void changeDirection(String directionString){
        switch (directionString) {
            case "W":
                if (direction!=DOWN && direction!=UP){
                direction = UP;
                }
                break;
            case "D":
                if (direction != LEFT && direction!=RIGHT) {
                    direction = RIGHT;
                }
                break;
            case "S":
                if (direction!=UP && direction!=DOWN) {
                    direction = DOWN;
                }
                break;
            case "A":
                if (direction!=RIGHT && direction!=LEFT) {
                    direction = LEFT;
                }
                break;
        }
    }

    public void move(){
        if (direction == UP){
            lastPositions.add(new Position(currentPosition.getCol(), currentPosition.getRow()));
            currentPosition.setRow(currentPosition.getRow()-1);
        } else if(direction == RIGHT){
            lastPositions.add(new Position(currentPosition.getCol(), currentPosition.getRow()));
            currentPosition.setCol(currentPosition.getCol()+1);
        }else if(direction == DOWN){
            lastPositions.add(new Position(currentPosition.getCol(), currentPosition.getRow()));
            currentPosition.setRow(currentPosition.getRow()+1);
        }else if(direction == LEFT){
            lastPositions.add(new Position(currentPosition.getCol(), currentPosition.getRow()));
            currentPosition.setCol(currentPosition.getCol()-1);
        }
        if (lastPositions.size() == length){
            lastPositions.remove(0);
        }

    }
}
