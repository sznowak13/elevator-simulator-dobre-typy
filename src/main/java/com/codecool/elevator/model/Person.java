package com.codecool.elevator.model;

public class Person {
    private Floor currentFloor;
    private Floor destFloor;
    private Direction desiredDirection;

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDestFloor(Floor destFloor) {
        this.destFloor = destFloor;
    }

    public void setDesiredDirection(Direction desiredDirection) {
        this.desiredDirection = desiredDirection;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Floor getDestFloor() {
        return destFloor;
    }

    public Direction getDesiredDirection() {
        return desiredDirection;
    }

    public void getInAnElevator(Elevator elevator) {
        currentFloor.removePersonFromQueue(this);
        elevator.takePerson(this);
    }

    public void leaveTheElevator() {

    }
}
