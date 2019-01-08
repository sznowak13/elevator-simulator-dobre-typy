package com.codecool.elevator.model;

import java.util.LinkedList;
import java.util.Queue;

public class Building {
    private Floor[] floorList;
    private Elevator[] elevatorList;
    private Queue<Person> peopleList = new LinkedList<>();

    public Building() {
        this(Consts.FLOORS_AMOUNT, Consts.ELEVATORS_AMOUNT);
    }

    public Building(int floorsAmount, int elevatorsAmount) {
        createFloors(floorsAmount);
        createElevators(elevatorsAmount);
        createPeople(floorsAmount * Consts.MAX_FLOOR_CAP);

    }

    private void createPeople(int amount) {
        for (int i = 0; i < amount; i++) {
            peopleList.add(new Person());
        }
    }

    private void createElevators(int amount) {
        this.elevatorList = new Elevator[amount];
        for (int i = 0; i < amount; i++) {
            this.elevatorList[i] = new Elevator();
        }
    }

    private void createFloors(int amount) {
        this.floorList = new Floor[amount];
        for (int i = 0; i < amount; i++) {
            this.floorList[i] = new Floor();
        }
    }

    public void addPersonToFloor(Person person, Floor floor) {
        floor.addPerson(person);
    }
}
