package com.codecool.elevator.model;

import java.util.Random;

public class RandomGenerator {

    public static int getIntInRange(int min, int max) {
        if (min >= max) {
            return 0;
        }

        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }
}
