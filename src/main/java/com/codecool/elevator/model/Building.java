package com.codecool.elevator.model;

import java.util.*;

public class Building implements Runnable{
    private static Building ourInstance = new Building();
    private static List<Floor> floorList;
    private Queue<Person> peoplePool;
    private ElevatorManager Andrzej;


    private Building() {
        this.createFloors();
        this.createElevatorManager();
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
        floorList = new ArrayList<>();

        for (int i = 0; i < Config.FLOORS_AMOUNT; i++) {
            floorList.add(new Floor(i));
        }

    }
    private void createElevatorManager() {
        this.Andrzej = ElevatorManager.getInstance();
    }

    public static synchronized List<Floor> getFloorList() {
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
        while (true) {
            try {
                Thread.sleep(Config.PEOPLE_SPAWN_TIME*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!peoplePool.isEmpty()) peoplePool.poll().spawn();
        }
    }
}
