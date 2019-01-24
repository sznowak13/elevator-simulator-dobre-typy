package com.codecool.elevator.model;

import com.codecool.elevator.view.DisplayConfig;

public class Person extends MovingEntity {
    private int currentFloorLevel;
    private int destinationFloorLevel;
    private boolean closeToElevator;
    private boolean inElevator;
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
        this.closeToElevator = false;
        this.inElevator = false;
    }


    public void callAnElevator() {
        Call newCall = new Call(this.currentFloorLevel, this.destinationFloorLevel);
        ElevatorManager.getInstance().addExternalCall(newCall);

        if (checkIfCalledAnElevator(newCall)) this.hasCalled = true;
    }

    public boolean checkIfCalledAnElevator(Call call) {
        return ElevatorManager.getInstance().getExternalCalls().contains(call);
    }

    public void getInElevator() {

    }

    public void checkIfSuitableElevator() {

    }

    @Override
    public void move() {
        if (spawned && !inElevator) {
            if (this.getPosX() > (DisplayConfig.ELEVATOR_WIDTH * Config.ELEVATORS_AMOUNT) + DisplayConfig.PERSON_WIDTH) {
               this.setPosX(this.getPosX() + this.getDirection());
            }else {
                this.setDirection(0);
                if (!hasCalled){
                    callAnElevator();
                }
        }

        }



    }
}
