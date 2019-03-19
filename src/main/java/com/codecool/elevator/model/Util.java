package com.codecool.elevator.model;

import java.util.Random;

public class Util {
    public static int getIntInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Min cannot be higher than max!");
        }

        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }
}
