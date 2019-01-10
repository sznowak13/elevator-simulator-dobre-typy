package com.codecool.elevator.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Elevator extends Observable implements Runnable{
    private int currentFloorLevel = 0;
    private List<Person> peopleList = new ArrayList<>();
    private int destinationFloorLevel = 0;
    private Direction direction = Direction.NONE;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private static Elevator[] elevatorPool;
    private static Queue<Person> externalQueue = new LinkedList<>();


    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void informObserverAboutChangingFloorLevel(int nextFloor) {
        support.firePropertyChange("currentFloor", this , nextFloor);
    }

    public static Elevator[] getElevatorPool() {
        return elevatorPool;
    }

    public static void setElevatorPool(Elevator[] elevatorPool) {
        Elevator.elevatorPool = elevatorPool;
    }

    public synchronized int getCurrentFloorLevel() {
        return currentFloorLevel;
    }

    public synchronized void incrementFloorLevel() {
        currentFloorLevel++;
    }

    public synchronized void decrementFloorLevel() {
        currentFloorLevel--;
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<Person> peopleList) {
        this.peopleList = peopleList;
    }

    public void setCurrentFloorLevel(int currentFloorLevel) {
        this.currentFloorLevel = currentFloorLevel;
    }

    public int getDestinationFloorLevel() {
        return destinationFloorLevel;
    }
    public void setDestinationFloorLevel(int floorLevel) {
        this.destinationFloorLevel = floorLevel;
    }

    public void removePersonFromExternalQueue(Person person) {
        externalQueue.remove(person);
    }

    public boolean checkIfPersonIsInside(Person person) {
        return peopleList.contains(person);
    }
    public void updateDestinationFloorLevel(int floorLevel) {
        if (floorLevel > destinationFloorLevel) {
            destinationFloorLevel = floorLevel;
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void addPerson(Person person) {
        this.peopleList.add(person);
    }

    public static void addToExternalQueue(Person person) {
        externalQueue.add(person);
    }

    public boolean checkIfSomebodyWantsToGetOut() {
        for (Person person: peopleList) {
            if (person.getDestFloor().getLevel() == currentFloorLevel) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfSomebodyWantsToGetIn() {
        for (Person person: externalQueue) {
            if (person.getCurrentFloor().getLevel() == currentFloorLevel) {
                return true;
            }
        }
        return false;
    }

    public void moveToNextFloor() {
        if (checkIfSomebodyWantsToGetOut() || checkIfSomebodyWantsToGetIn()) {

            if (currentFloorLevel < Floor.getFloorList().size() - 1 && this.direction == Direction.UP) {
                informObserverAboutChangingFloorLevel(currentFloorLevel + 1);
                incrementFloorLevel();
            } else if ((currentFloorLevel > 0) && this.direction == Direction.DOWN) {
                informObserverAboutChangingFloorLevel(currentFloorLevel - 1);
                decrementFloorLevel();
            }
        }
    }



    @Override
    public void run() {
        while (true) {

        }
    }
}
