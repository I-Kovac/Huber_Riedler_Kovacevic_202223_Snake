package com.example.huber_riedler_kovacevic_202223_snake.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

/**
 * Repräsentiert dei Schlange
 * Konstanten:
 *  UP: Richtung nach oben
 *  RIGHT: Richtung nach rechts
 *  DOWN: Richtung nach unten
 *  LEFT: Richtung nach links#
 *  STARTLENGHT: Startlänge der Schlange
 * Eigenschaften:
 *  lenght: Länge der Schlange
 *  direction: Richtung, in die sich die Schlange bewegt
 *  currentPosition: Feld, auf dem sich der Kopf befindet
 *  lastPositions: Liste der Felder, auf der sich der Schwanz der Schlange befindet
 *  hasMoved: Richtung kann nur geändert werden, nachdem sich die Schlange bewegt hat
 */
public class Snake {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int STARTLENGHT = 2;

    private int length;
    private int direction;
    private Position currentPosition;
    private ArrayList<Position> lastPositions = new ArrayList<>();
    private boolean hasMoved;

    /**
     * Konstruktor setzt default values für lengt, direction, currentPosition und hasMoved
     */
    public Snake() {
    length = STARTLENGHT;
    direction = RIGHT;
    currentPosition = new Position(1, 1);
    hasMoved = false;
    }

    /**
     * getter von lenght
     * @return Länge
     */
    public int getLength() {
        return length;
    }

    /**
     * getter von currentPosition
     * @return Position des Kopfes der Schlange
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * getter von lastPositions
     * @return lastPositions
     */
    public ArrayList<Position> getLastPositions() {
        return lastPositions;
    }

    /**
     * erhöht die Länge der Schlange und bewegt sie
     */
    public void eat(){
        length++;
        move();
    }

    /**
     * getter von direction
     * @return Richtung
     */
    public int getDirection() {
        return direction;
    }

    /**
     * ändert die Richtung, in die sich die Schlange bewegt
     * @param directionString W/A/S/D = UP/LEFT/DOWN/RIGHT
     */
    public void changeDirection(String directionString){
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

    /**
     * Bewegut die Schlange um ein Feld in direction
     */
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
        hasMoved = true;

    }

    /**
     * getter von hasMoved
     * @return hasMoved
     */
    public boolean isHasMoved() {
        return hasMoved;
    }

    /**
     * setter von hasMoved
     * @param hasMoved hasMoved
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
