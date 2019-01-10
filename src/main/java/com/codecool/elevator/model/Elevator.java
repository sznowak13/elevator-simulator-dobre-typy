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

    public void moveToNextFloor() {
        System.out.println(direction);
        if (checkIfSomebodyWantsToGetOut()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (currentFloorLevel < Floor.getFloorList().size()-1 && this.direction == Direction.UP) {
            incrementFloorLevel();
        } else if ((currentFloorLevel > 0) && this.direction == Direction.DOWN) {
            decrementFloorLevel();
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        setChanged();
        notifyObservers(getCurrentFloorLevel());
        System.out.println("MOVED TO: " + currentFloorLevel);
        System.out.println("PPL INSIDE" + peopleList.size());
    }



    @Override
    public void run() {
        while (true) {
            if (destinationFloorLevel - currentFloorLevel > 0) {
                setDirection(Direction.UP);
            }
            else if (destinationFloorLevel - currentFloorLevel < 0){
                setDirection(Direction.DOWN);
            } else {
                setDirection(Direction.NONE);
            }


            if (direction == Direction.NONE) {
                if (peopleList.isEmpty()) {

                    if (!(externalQueue.size() == 0)) {
                        setDestinationFloorLevel(externalQueue.peek().getCurrentFloor().getLevel());
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        notifyObservers(currentFloorLevel);
                    }

                } else {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int i = 0; i < peopleList.size(); i++) {
                        temp.add(peopleList.get(i).getDestFloor().getLevel());
                    }

                    int highestDestFloor = Collections.max(temp);
                    int lowestDestFloor = Collections.min(temp);

                    if (highestDestFloor - currentFloorLevel > currentFloorLevel - lowestDestFloor) {
                        destinationFloorLevel = highestDestFloor;
                    } else {
                        destinationFloorLevel = lowestDestFloor;
                    }

                }

            } else {
                moveToNextFloor();
            }

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
