package com.example.huber_riedler_kovacevic_202223_snake.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {
    protected int direction;
    protected int length;
    protected int[] currentPosition;
    protected List<int[]> lastPositions = new ArrayList<>();


    public Snake() {
        length = 1;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public  void move() {
        lastPositions.add(currentPosition.clone());
        if (lastPositions.size() > length){
            lastPositions.remove(0);
        }

        if (direction == Playfield.UP) {
            currentPosition[0]--;
        } else if (direction == Playfield.DOWN) {
            currentPosition[0]++;
        } else if (direction == Playfield.RIGHT) {
            currentPosition[1]++;
        } else if (direction == Playfield.LEFT) {
            currentPosition[1]--;
        }

    }

    public void eatFood(){
        length++;
    }
}
