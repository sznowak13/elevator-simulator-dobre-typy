package com.codecool.elevator.model;

import com.codecool.elevator.view.DisplayConfig;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Person extends MovingEntity implements PropertyChangeListener {
    private int currentFloorLevel;
    private int destinationFloorLevel;
    private boolean closeToElevator;
    private boolean inElevator;
    private boolean hasCalled;
    private boolean spawned;
    private boolean arrived;

    public Person() {
        super(-DisplayConfig.PERSON_WIDTH, -DisplayConfig.PERSON_HEIGHT, 0);
        this.spawned = false;
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void spawn() {
        this.currentFloorLevel = Util.getIntInRange(0, Config.FLOORS_AMOUNT-1);

        do {
            this.destinationFloorLevel = Util.getIntInRange(0, Config.FLOORS_AMOUNT - 1);
        } while (currentFloorLevel == destinationFloorLevel);

        this.setPosX(DisplayConfig.SCREEN_WIDTH);
        this.setPosY(DisplayConfig.SCREEN_HEIGHT - (DisplayConfig.FLOOR_HEIGHT * currentFloorLevel));
        this.setDirection(-1);

        Building.getFloorList().get(currentFloorLevel).addPerson(this);
        Building.getFloorList().get(currentFloorLevel).addPropertyChangeListener(this);
        this.hasCalled  = false;
        this.spawned = true;
        this.closeToElevator = false;
        this.inElevator = false;
        this.arrived = false;
    }

    public int getCurrentFloorLevel() {
        return this.currentFloorLevel;
    }


    public void callAnElevator() {
        Call newCall = new Call(this.currentFloorLevel, this.destinationFloorLevel);
        Building.getFloorList().get(currentFloorLevel).addPropertyChangeListener(this);
        ElevatorManager.getInstance().addExternalCall(newCall);
        closeToElevator = true;

        if (checkIfCalledAnElevator(newCall)) this.hasCalled = true;
    }

    public boolean checkIfCalledAnElevator(Call call) {
        return ElevatorManager.getInstance().getExternalCalls().contains(call);
    }

    public void getInElevator(Elevator elevator) {
        if (closeToElevator) {
            elevator.addPerson(this);
            Building.getFloorList().get(this.currentFloorLevel).removePerson(this);
            this.setPosX(elevator.getPosX() + DisplayConfig.ELEVATOR_WIDTH);
            this.setPosY(elevator.getPosY() + DisplayConfig.FLOOR_HEIGHT);
            elevator.getInternalOrders().add(this.destinationFloorLevel);
            this.inElevator = true;
        }
    }

    public void getOutTheElevator(Elevator elevator) {
        if (inElevator) {
            if (elevator.getCurrentFloorLevel() == destinationFloorLevel) {
                elevator.removePerson(this);
                //this.setPosX(DisplayConfig.SCREEN_WIDTH - DisplayConfig.PERSON_WIDTH);
                this.setPosY(DisplayConfig.SCREEN_HEIGHT - (DisplayConfig.FLOOR_HEIGHT * destinationFloorLevel));
                this.arrived = true;
            }
        }
    }

    public boolean isArrived() {
        return arrived;
    }

    public Elevator getSuitableElevator(List<Elevator> elevators) {
        // returns Elevator if found suitable one, else null.
        for (Elevator elevator: elevators) {

        }
        return null;
    }

    public boolean checkIfElevatorSuits(Elevator elevator) {
        return elevator.getDirection() == getDirection();
    }

    public boolean isInElevator() {
        return this.inElevator;
    }

    @Override
    public void move() {
        if (spawned && !inElevator) {
            if (this.getPosX() > (DisplayConfig.ELEVATOR_WIDTH * Config.ELEVATORS_AMOUNT) + DisplayConfig.PERSON_WIDTH) {
               this.setPosX(this.getPosX() + (this.getDirection()*Config.PEOPLE_SPEED));

            }else {
                if (!hasCalled){
                    callAnElevator();
                }
        }
        } else if (arrived) {
            if (this.getPosX() < DisplayConfig.SCREEN_WIDTH) {
                this.setPosX(this.getPosX() + (1*Config.PEOPLE_SPEED));
            }
        }



    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        inElevator = true;
    }
}
