package com.codecool.elevator.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Elevator extends Observable implements Runnable{

    private int id;
    private int currentFloorLevel = 0;
    private List<Person> peopleList = new ArrayList<>();
    private int destinationFloorLevel = 0;
    private Direction direction = Direction.NONE;

    private static PropertyChangeSupport support;
    private static Elevator[] elevatorPool;
    private static Queue<Person> externalQueue = new LinkedList<>();
    private static int idSequence = 0;

    public Elevator() {
        support = new PropertyChangeSupport(this);
        this.id = idSequence++;
    }

    public int getId() {
        return id;
    }

    public static Elevator[] getElevatorPool() {
        return elevatorPool;
    }

    public int getDestinationFloorLevel() {
        return destinationFloorLevel;
    }

    public static void setElevatorPool(Elevator[] elevatorPool) {
        Elevator.elevatorPool = elevatorPool;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setDestinationFloorLevel(int newFloorLevel) {
        destinationFloorLevel =  newFloorLevel;
    }

    public synchronized int getCurrentFloorLevel() {
        return currentFloorLevel;
    }

    public void incrementFloorLevel() {
        if (currentFloorLevel < Floor.getFloorList().size()-1) currentFloorLevel++;
    }

    public void decrementFloorLevel() {
        if (currentFloorLevel > 0) currentFloorLevel--;
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }

    public static Queue<Person> getExternalQueue() {
        return externalQueue;
    }
    public void setPeopleList(List<Person> peopleList) {
        this.peopleList = peopleList;
    }

    public void setCurrentFloorLevel(int currentFloorLevel) {
        this.currentFloorLevel = currentFloorLevel;
    }

    public boolean checkIfPersonIsInside(Person person) {
        return peopleList.contains(person);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }


    public static void addToExternalQueue(Person person) {
        externalQueue.add(person);
    }

    public static void removeFromExternalQueue(Person person) {
        externalQueue.remove(person);
    }

    public static void addToExternalQueue(int floorLevel) {
        externalQueue.add(floorLevel);
    }

    public void moveToNextFloor() {
        int beforeMovement = currentFloorLevel;
        if (direction == Direction.NONE) {
            return;
        } else if (direction == Direction.UP) {
            incrementFloorLevel();
        } else if (direction == Direction.DOWN) {
            decrementFloorLevel();
        }
        support.firePropertyChange("currentFloor", this, currentFloorLevel);
    }

    public void informPeopleAboutCurrentPosition() {
        support.firePropertyChange("currentFloor", this, currentFloorLevel);
    }

    @Override
    public void run() {
        while (true) {
            informPeopleAboutCurrentPosition();
            System.out.println("MOVEMENT IN DIRECTION: " + direction);
            if (!peopleList.isEmpty()) {
                destinationFloorLevel = peopleList.get(0).getDestFloor().getLevel();
            }
            if (destinationFloorLevel - currentFloorLevel < 0) {
                setDirection(Direction.DOWN);
            } else if (destinationFloorLevel - currentFloorLevel > 0) {
                setDirection(Direction.UP);
            } else {
                setDirection(Direction.NONE);
            }

            if (direction != Direction.NONE) {
                moveToNextFloor();
            } else {
                informPeopleAboutCurrentPosition();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
