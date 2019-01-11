package com.codecool.elevator.model;

import java.util.*;

public class Elevator extends Observable implements Runnable{

    private int id;
    private int currentFloorLevel = 0;
    private List<Person> peopleList = new ArrayList<>();
    private Queue<Integer> destinationsQueue = new LinkedList<>();
    private Direction direction = Direction.NONE;

    private static Elevator[] elevatorPool;
    private static Queue<Integer> externalQueue = new LinkedList<>();
    private static int idSequence = 0;

    public Elevator() {
        this.id = idSequence++;
    }

    public int getId() {
        return id;
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

    public Queue<Integer> getDestinationsQueue() {
        return destinationsQueue;
    }

    public boolean checkIfPersonIsInside(Person person) {
        return peopleList.contains(person);
    }

    public void addToDestinationsQueue(int destinationFloorLevel) {
        this.destinationsQueue.add(destinationFloorLevel);
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

    public void checkForExternalCall() {
        if (externalQueue.size() > 0) {
            this.destinationsQueue.add(externalQueue.poll());
        }
    }

    public static void addToExternalQueue(int floorLevel) {
        externalQueue.add(floorLevel);
    }

    public void moveToNextFloor() {
        if (currentFloorLevel < Floor.getFloorList().size()-1 && this.direction.equals(Direction.UP)) {
            incrementFloorLevel();
        } else if (currentFloorLevel > 0 && this.direction.equals(Direction.DOWN)) {
            decrementFloorLevel();
        }
        setChanged();
        notifyObservers(getCurrentFloorLevel());
        System.out.println("moved to " + getCurrentFloorLevel());
        System.out.println("orders: " + this.destinationsQueue);
        if (destinationsQueue.peek() == currentFloorLevel) {
            System.out.println("JESTEM TU");
            this.direction = Direction.NONE;
            destinationsQueue.poll();
        }
    }



    @Override
    public void run() {
        while (true) {
            if (direction == Direction.NONE) {
                if (destinationsQueue.isEmpty()) {
                    checkForExternalCall();
                }
                else {
                    setDirection((destinationsQueue.peek()-getCurrentFloorLevel() > 0) ? Direction.UP:Direction.DOWN);
                }
            }
            else {
                moveToNextFloor();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
