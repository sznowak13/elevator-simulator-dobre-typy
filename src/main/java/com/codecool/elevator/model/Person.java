package com.codecool.elevator.model;

public class Person {
    private Floor currentFloor;
    private Floor destFloor;
    private Direction desiredDirection;

    Person(Floor currFloor, Floor destFloor) {
        currentFloor = currFloor;
        this.destFloor = destFloor;
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
