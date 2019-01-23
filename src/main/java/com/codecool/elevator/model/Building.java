package com.codecool.elevator.model;

import java.util.*;

public class Building implements Runnable{
    private static Building ourInstance = new Building();
    private List<Floor> floorList;
    private Queue<Person> peoplePool;
    private ElevatorManager Andrzej;


    private Building() {
        this.createElevatorManager();
        this.createFloors();
        this.createPeople();
    }

    public static Building getInstance() {
        return ourInstance;
    }

    public void createPeople() {
        this.peoplePool = new LinkedList<>();

        for (int i = 0; i < Config.PEOPLE_AMOUNT; i++) {
            this.peoplePool.add(new Person());
        }
    }

    public void createFloors() {
        this.floorList = new ArrayList<>();

        for (int i = 0; i < Config.FLOORS_AMOUNT; i++) {
            this.floorList.add(new Floor(i));
        }

    }

    public void createElevatorManager() {
        this.Andrzej = ElevatorManager.getInstance();
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public Queue<Person> getPeoplePool() {
        return peoplePool;
    }

    public ElevatorManager getAndrzej() {
        return Andrzej;
    }

    @Override
    public void run() {

    }
}
