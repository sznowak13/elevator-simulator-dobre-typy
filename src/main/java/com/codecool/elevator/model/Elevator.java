package com.codecool.elevator.model;

import java.util.*;

public class Elevator extends Observable implements Runnable{
    private int currentFloorLevel = 0;
    private List<Person> peopleList = new ArrayList<>();
    private int destinationFloorLevel = 0;
    private Direction direction = Direction.NONE;

    private static Elevator[] elevatorPool;
    private static Queue<Person> externalQueue = new LinkedList<>();

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

    public static Queue<Person> getExternalQueue() {
        return externalQueue;
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

    public Direction getDirection() {
        return direction;
    }

    public void addPerson(Person person) {
        this.peopleList.add(person);
    }

    public static void addToExternalQueue(Person person) {
        externalQueue.add(person);
    }


    public void moveToNextFloor() {
        if (currentFloorLevel < Floor.getFloorList().size()-1 && this.direction == Direction.UP) {
            incrementFloorLevel();
        } else if ((currentFloorLevel > 0) && this.direction == Direction.DOWN) {
            decrementFloorLevel();
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    @Override
    public void run() {

    }
}
