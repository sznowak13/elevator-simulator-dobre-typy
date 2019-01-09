package com.codecool.elevator.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Floor {
    private static int idSequence = 0;
    private static List<Floor> floorList;

    private int currentCap;
    private int level;
    private HashMap<Direction, LinkedList<Person>> peopleQueue = new HashMap<>();
    {
        peopleQueue.put(Direction.UP, new LinkedList<>());
        peopleQueue.put(Direction.DOWN, new LinkedList<>());
    }

    public static List<Floor> getFloorList() {
        return floorList;
    }

    public static void setFloorList(List<Floor> floorList) {
        Floor.floorList = floorList;
    }

    public Floor() {
        this.level = idSequence++;
    }

    public int getLevel() {
        return level;
    }

    public int getCurrentCap() {
        return currentCap;
    }

    public void addPerson(Person person) {
        peopleQueue.get(person.getDesiredDirection()).add(person);
        currentCap++;
    };

    public void removePersonFromQueue(Person person) {
        Direction personDirection = person.getDesiredDirection();
        peopleQueue.get(personDirection).remove(person);
        currentCap--;
    }

    public LinkedList<Person> getPeopleByDesiredDirection(Direction direction) {
        return peopleQueue.get(direction);
    }

    public static Floor getRandomFloor() {
        List<Floor> floorList =  Floor.getFloorList();

        return floorList.get(RandomGenerator.getIntInRange(0, floorList.size()-1));
    }
}
