package main.java.com.codecool.elevator.model;

import java.util.List;
import java.util.Queue;

public class Building implements Runnable{
    private static Building ourInstance = new Building();
    private List<Floor> floorList;
    private Queue<Person> peoplePool;


    private Building() {
    }

    public static Building getInstance() {
        return ourInstance;
    }

    public void createPeople(int amount) {

    }

    public void createFloors(int amount) {

    }

    public void createElevators(int amount) {

    }

    @Override
    public void run() {

    }
}
