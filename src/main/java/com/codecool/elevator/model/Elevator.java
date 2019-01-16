package com.codecool.elevator.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Elevator implements Runnable {
    private int id;
    private int currentFloorLevel = 0;
    private int destinationFloorLevel = 0;
    private boolean isMoving;
    private Direction direction = Direction.NONE;
    private List<Person> peopleList = new ArrayList<>();

    private static int idSequence = 0;
    private static PropertyChangeSupport support;
    private static Elevator[] elevatorPool;


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


    public Elevator() {
        id = idSequence++;
        support = new PropertyChangeSupport(this);
    }

    public int getId() {
        return id;
    }

    public void setDestinationFloorLevel(int newFloorLevel) {
        destinationFloorLevel =  newFloorLevel;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public Direction getDirection() {
        return direction;
    }

    private int getNextDestinationFloorLevel() {
        SortedSet<Integer> internalOrders = new TreeSet<>();
        for (Person person: peopleList) {
            internalOrders.add(person.getDestFloor().getLevel());
        }
        if (direction == Direction.DOWN) {
            return internalOrders.first();
        } else if (direction == Direction.UP) {
            return internalOrders.last();
        } else {
            return internalOrders.first();
        }
    }


    public void moveToNextFloor() {
        int floorLevelBeforeMovement = currentFloorLevel;
        if (direction == Direction.NONE) {
            return;
        } else if (direction == Direction.UP) {
            incrementFloorLevel();
            if (currentFloorLevel == Consts.FLOORS_AMOUNT-1) {
                setDirection(Direction.NONE);
            }
        } else if (direction == Direction.DOWN) {
            decrementFloorLevel();
            if (currentFloorLevel == 0) {
                setDirection(Direction.NONE);
            }
        }
        informAboutCurrentPosition(floorLevelBeforeMovement);
    }

    private void setupDirection() {
        if (destinationFloorLevel - currentFloorLevel < 0) {
            setDirection(Direction.DOWN);
        } else if (destinationFloorLevel - currentFloorLevel > 0) {
            setDirection(Direction.UP);
        }
    }

    public void informAboutCurrentPosition(int value) {
        support.firePropertyChange("currentFloor", this, value);
    }



    @Override
    public void run() {
        while (true) {
            if (!peopleList.isEmpty()) {
                destinationFloorLevel = getNextDestinationFloorLevel();
            } else {
                direction = Direction.NONE;
            }

            setupDirection();

            if (direction != Direction.NONE) {
                moveToNextFloor();
            }

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
