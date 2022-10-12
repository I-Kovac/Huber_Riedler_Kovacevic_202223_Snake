package com.example.huber_riedler_kovacevic_202223_snake.controller;

import com.example.huber_riedler_kovacevic_202223_snake.model.Snake;

import java.util.Scanner;

public class InputScanThread implements Runnable{
    private Scanner sc;


    public InputScanThread(Snake snake){
        sc = new Scanner(System.in);

    }

    @Override
    public void run() {


        while (true){
            String direction = sc.next();

        }

    }
}
