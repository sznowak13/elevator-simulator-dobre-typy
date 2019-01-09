package com.codecool.elevator.model;

import java.util.*;

public class Elevator {
    private Floor currentFloor;
    private int currentCap;
    private HashMap<Integer, ArrayList<Person>> destinationsMap;
    private Direction direction = Direction.NONE;

    private static Queue<Person> peoplePool;
    private static List<Floor> floorList;

    public static void setPeoplePool(Queue<Person> newPeoplePool) {
        peoplePool = newPeoplePool;
    }

    public static void setFloorList(List<Floor> floorList) {
        Elevator.floorList = floorList;
    }

    private void takePerson(Person person) {
        if (this.currentCap < Consts.MAX_ELEVATOR_CAP) {
            int personDesiredFloorLevel = person.getDestFloor().getLevel();
            if (destinationsMap.containsKey(personDesiredFloorLevel)) {
                destinationsMap.get(personDesiredFloorLevel).add(person);
            }
            else {
                ArrayList<Person> newDestFloorList = new ArrayList<>();
                newDestFloorList.add(person);
                destinationsMap.put(personDesiredFloorLevel, newDestFloorList);
            }
            this.currentCap++;
            person.getCurrentFloor().removePersonFromQueue(person);
        }
    }

    public void letPassengersIn() {
        LinkedList<Person> currentFloorPeople = currentFloor.getPeopleByDesiredDirection(this.direction);
        if (currentFloorPeople.size() > 0) {
            currentFloorPeople.forEach(this::takePerson);
        }
    }

    public void letPassengersOut() {
        int currentFloorLevel = currentFloor.getLevel();
        if (destinationsMap.containsKey(currentFloorLevel)) {
            ArrayList<Person> currentFloorPassengers = destinationsMap.get(currentFloorLevel);
            for (Person passenger : currentFloorPassengers) {
                currentFloorPassengers.remove(passenger);
                peoplePool.add(passenger);
            }

        }
    }

    public void checkForExternalCall() {
        if (direction == Direction.NONE && destinationsMap.isEmpty()) {

        }
    }

    public void moveToNextFloor() {
        int currentFloorLevel = this.currentFloor.getLevel();
        if (currentFloorLevel < Elevator.floorList.size() && this.direction.equals(Direction.UP)) {
            this.currentFloor = Elevator.floorList.get(currentFloorLevel + 1);
        } else if (currentFloorLevel > 0 && this.direction.equals(Direction.DOWN)) {
            this.currentFloor = Elevator.floorList.get(currentFloorLevel - 1);
        }
    }
}
