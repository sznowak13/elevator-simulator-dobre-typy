package com.codecool.elevator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Elevator {
    private Floor currentFloor;
    private int currentCap;
    private HashMap<Integer, ArrayList<Person>> destinationsMap;
    private Direction direction = Direction.NONE;

    public void takePerson(Person person) {
        if (currentCap < Consts.MAX_ELEVATOR_CAP) {
            int personDesiredFloorLevel = person.getDestFloor().getLevel();
            if (destinationsMap.containsKey(personDesiredFloorLevel)) {
                destinationsMap.get(personDesiredFloorLevel).add(person);
            }
            else {
                ArrayList<Person> newDestFloorList = new ArrayList<>();
                newDestFloorList.add(person);
                destinationsMap.put(personDesiredFloorLevel, newDestFloorList);
                currentCap++;
            }
            person.getCurrentFloor().removePersonFromQueue(person);
        }
    }

    public void getCurrentFloorForPassengers() {
        if (direction != Direction.NONE) {
            LinkedList<Person> currentFloorPeople = currentFloor.getPeopleByDesiredDirection(direction);
            if (currentFloorPeople.size() > 0) {
                currentFloorPeople.forEach(person -> destinationsMap.get(person.getDestFloor().getLevel()).add(person));
            }
        }
    }

    public void letPassengersOut() {
        int currentFloorLevel = currentFloor.getLevel();
        if (destinationsMap.containsKey(currentFloorLevel)) {
            destinationsMap.get(currentFloorLevel).forEach(person -> person.leaveTheElevator());

        }
    }

    public void checkForExternalCall() {
        if (direction == Direction.NONE && destinationsMap.isEmpty()) {

        }
    }
}
