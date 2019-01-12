package com.codecool.elevator.model;

import com.codecool.elevator.controller.ElevatorController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Queue;

public class Person implements PropertyChangeListener {
    private Floor currentFloor;
    private Floor destFloor;
    private Direction desiredDirection;
    private ArrayList<Object> elevatorCalling;

    private static Queue<Person> peoplePool;

    public static Queue<Person> getPeoplePool() {
        return peoplePool;
    }

    public Floor getDestFloor() {
        return destFloor;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
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

    public void spawn(Floor floor) {
        setCurrentFloor(floor);
        do {
            setDestFloor(Floor.getRandomFloor());
        } while (this.destFloor == this.currentFloor);

        setDesiredDirection((destFloor.getLevel() - currentFloor.getLevel() < 0) ? Direction.UP : Direction.DOWN);
        watchAllElevators();
        floor.addPerson(this);
        elevatorCalling = new ArrayList<>();
        elevatorCalling.add(getCurrentFloor().getLevel());
        elevatorCalling.add(desiredDirection);
        Elevator anyEmptyElevator = getElevatorIfAnyEmptyOneIsOnMyFloor();
        if (anyEmptyElevator != null) {
            getInElevator(anyEmptyElevator);
        } else {
            callAnElevator();
        }
    }

    public ArrayList<Object> getElevatorCalling() {
        return elevatorCalling;
    }

    private Elevator getElevatorIfAnyEmptyOneIsOnMyFloor() {
        for (Elevator elevator: Elevator.getElevatorPool()) {
            if (elevator.getCurrentFloorLevel() == currentFloor.getLevel() && elevator.getDirection() == Direction.NONE) {
                return elevator;
            }
        }
        return null;
    }

    public void callAnElevator() {
        ElevatorController.callAnElevator(elevatorCalling);
    }

    public void watchAllElevators() {
        Elevator[] elevatorPool = Elevator.getElevatorPool();
        if (elevatorPool.length > 0) {
            for (Elevator elevator: elevatorPool) {
                elevator.addPropertyChangeListener(this);
            }
        }
    }

    public void getInElevator(Elevator elevator) {
        currentFloor.removePersonFromQueue(this);
        elevator.getPeopleList().add(this);
        ElevatorController.removePersonsExternalCalling(elevatorCalling);
    }

    public void getOutTheElevator(Elevator elevator) {
        peoplePool.add(this);
        elevator.getPeopleList().remove(this);
        elevator.removePropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        Elevator elevator = (Elevator) evt.getOldValue();
        int elevatorsCurrentFloor = (int) evt.getNewValue();

        if (elevator.getPeopleList().contains(this) && this.destFloor.getLevel() == elevatorsCurrentFloor) {
            getOutTheElevator(elevator);
        }
        else if (this.currentFloor.getLevel() == elevatorsCurrentFloor && elevator.getPeopleList().size() < Consts.MAX_ELEVATOR_CAP && !elevator.getPeopleList().contains(this)) {
            getInElevator(elevator);
        }
    }
}
