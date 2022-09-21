package com.example.huber_riedler_kovacevic_202223_snake.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {

    protected int length;
    protected List<int[]> lastPositions = new ArrayList<>();


    public Snake(){
        length=1;
    }

    public void setLastPosition(int row, int col){
        lastPositions.add(new int[]{row, col});
        System.out.println(Arrays.toString(lastPositions.stream().findFirst().get()));
    }
}
