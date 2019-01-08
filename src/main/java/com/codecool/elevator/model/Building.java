package com.codecool.elevator.model;

public class Building {
    private Floor[] floorList;
    private Elevator[] elevatorList;
    private Person[] peopleList;

    public Building() {
        this(Consts.FLOORS_AMOUNT, Consts.ELEVATORS_AMOUNT);
    }

    public Building(int floorsAmount, int elevatorsAmount) {
        createFloors(floorsAmount);
        createElevators(elevatorsAmount);
        createPeople(floorsAmount * Consts.MAX_FLOOR_CAP);

    }

    private void createPeople(int amount) {
        this.peopleList = new Person[amount];
        for (int i = 0; i < amount; i++) {
            peopleList[i] = new Person();
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

    }
}
