package com.codecool.elevator.model;

import com.codecool.elevator.view.DisplayConfig;

public class Person extends MovingEntity {
    private int currentFloorLevel;
    private int destinationFloorLevel;
    private boolean hasCalled;
    private boolean spawned;

    public Person() {
        super(-DisplayConfig.PERSON_WIDTH, -DisplayConfig.PERSON_HEIGHT, 0);
        this.spawned = false;
    }

    public boolean isSpawned() {
        return spawned;
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
        this.hasCalled  = false;
        this.spawned = true;
    }


    public void callAnElevator() {
        ElevatorManager.getInstance().addExternalCall(new Call(this.currentFloorLevel, this.destinationFloorLevel));
        this.setDirection(0);
        this.hasCalled = true;
    }

    public void getInElevator() {

    }

    public void checkIfSuitableElevator() {

    }

    @Override
    public void move() {
        if (this.getPosX() > (DisplayConfig.ELEVATOR_WIDTH * Config.ELEVATORS_AMOUNT) + DisplayConfig.PERSON_WIDTH) {
            this.setPosX(this.getPosX() + this.getDirection());
        }else {
            if (!hasCalled){
                callAnElevator();
                System.out.println(hasCalled);
            }
        }



    }
}
