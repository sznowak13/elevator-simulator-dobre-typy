package com.codecool.elevator.model;

import com.codecool.elevator.view.DisplayConfig;

public class Person extends MovingEntity {
    private int currentFloorLevel;
    private int destinationFloorLevel;

    public Person() {
        super(-DisplayConfig.PERSON_WIDTH, -DisplayConfig.PERSON_HEIGHT, 0);
    }

    public void spawn(int startFloor) {

    }

    public void callAnElevator() {

    }

    public void getInElevator() {

    }

    public void checkIfSuitableElevator() {

    }

    @Override
    public void move() {

    }
}
