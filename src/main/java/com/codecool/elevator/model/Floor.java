package com.codecool.elevator.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Floor {
    private static int idSequence = 0;
    private static List<Floor> floorList;

    private int currentCap;
    private int level;
    private LinkedList<Person> peopleQueue = new LinkedList<>();

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
        peopleQueue.add(person);
        refreshCurrentCap();
    };

    public void removePersonFromQueue(Person person) {
        peopleQueue.remove(person);
        refreshCurrentCap();
    }

    public void refreshCurrentCap() {
        this.currentCap = peopleQueue.size();
    }

    public static Floor getRandomFloor() {
        List<Floor> floorList =  Floor.getFloorList();

        return floorList.get(RandomGenerator.getIntInRange(0, floorList.size()-1));
    }
}
