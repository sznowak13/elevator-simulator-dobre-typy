package com.codecool.elevator.model;

import com.codecool.elevator.view.DisplayConfig;

public class Person extends MovingEntity {
    private int currentFloorLevel;
    private int destinationFloorLevel;

    public Person() {
        super(-DisplayConfig.PERSON_WIDTH, -DisplayConfig.PERSON_HEIGHT, 0);
    }

    public void spawn() {
        System.out.println("Person spawning.");
        this.currentFloorLevel = Util.getIntInRange(0, Config.FLOORS_AMOUNT-1);

        do {
            this.destinationFloorLevel = Util.getIntInRange(0, Config.FLOORS_AMOUNT - 1);
        } while (currentFloorLevel == destinationFloorLevel);

        this.setPosX(DisplayConfig.SCREEN_WIDTH);
        this.setPosY(DisplayConfig.SCREEN_HEIGHT - (DisplayConfig.FLOOR_HEIGHT * currentFloorLevel));
        this.setDirection(-1);
    }

    public void moveToOppositeSide() {
        this.setPosX(this.getPosX() - DisplayConfig.PERSON_WIDTH);
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
