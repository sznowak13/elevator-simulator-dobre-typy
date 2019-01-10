package com.codecool.elevator.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class Person implements Observer {
    private Floor currentFloor;
    private Floor destFloor;
    private Direction desiredDirection;

    private static Queue<Person> peoplePool;

    public static Queue<Person> getPeoplePool() {
        return peoplePool;
    }

    public static void setPeoplePool(Queue<Person> peoplePool) {
        Person.peoplePool = peoplePool;
    }

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

    public int getCurrentFloorLevel() {
        return currentFloor.getLevel();
    }

    public int getDestFloorLevel() {
        return destFloor.getLevel();
    }

    public Direction getDesiredDirection() {
        return desiredDirection;
    }

    public void spawn(Floor floor) {
        setCurrentFloor(floor);
        do {
            setDestFloor(Floor.getRandomFloor());
        } while (this.destFloor == this.currentFloor);

        setDesiredDirection((destFloor.getLevel() - currentFloor.getLevel() < 0) ? Direction.UP : Direction.DOWN);

        floor.addPerson(this);
    }

    public void callAnElevator() {
        Elevator.addToExternalQueue(this);
    }

    public void getInElevator(Elevator elevator) {
        if (elevator.getPeopleList().size() < Consts.MAX_ELEVATOR_CAP) {
            this.currentFloor.removePersonFromQueue(this);
            elevator.addPerson(this);
            System.out.println(elevator.getPeopleList());
            elevator.removePersonFromExternalQueue(this);
        }
    }

    public void getOutTheElevator(Elevator elevator) {
        elevator.getPeopleList().remove(this);
        if (!peoplePool.contains(this)) Person.getPeoplePool().add(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
