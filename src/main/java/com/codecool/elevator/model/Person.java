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

    public Direction getDesiredDirection() {
        return desiredDirection;
    }

    @Override
    public void update(Observable o, Object floorLevel) {
        Elevator elevator = (Elevator) o;
        if (!elevator.checkIfPersonIsInside(this)) {
            if ((int) floorLevel == currentFloor.getLevel() && elevator.getDirection() == desiredDirection) {
                this.getInElevator(elevator);
            }
        } else {
            if ((int) floorLevel == destFloor.getLevel()) {
                this.getOutTheElevator(elevator);
            }
        }

    }

    public void spawn(Floor floor) {
        setCurrentFloor(floor);
        do {
            setDestFloor(Floor.getRandomFloor());
        } while (this.destFloor == this.currentFloor);

        setDesiredDirection((destFloor.getLevel() - currentFloor.getLevel() < 0) ? Direction.UP : Direction.DOWN);

        for (Elevator elevator: Elevator.getElevatorPool()) {
            elevator.addObserver(this);
        }

        floor.addPerson(this);
    }

    public void callAnElevator() {
        Elevator.addToExternalQueue(currentFloor.getLevel());
    }

    public void getInElevator(Elevator elevator) {
        this.currentFloor.removePersonFromQueue(this);
        elevator.addPerson(this);
        elevator.addToDestinationsQueue(destFloor.getLevel());
    }

    public void getOutTheElevator(Elevator elevator) {
        elevator.getPeopleList().remove(this);
        Person.getPeoplePool().add(this);
    }
}
