package com.codecool.elevator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Elevator extends MovingEntity {
    private int currentFloorLevel;
    private int destinationFloorLevel;
    private boolean isMoving;
    private List<Person> peopleInsideList;
    private TreeSet<Integer> internalOrders;



    public Elevator() {
        super();
        this.id = instanceCounter++;
        this.currentFloorLevel = 0;
        this.isMoving = false;
        this.peopleInsideList = new ArrayList<>();
        this.internalOrders = new TreeSet<>();
    }

    @Override
    public void move() {

    }

    public void updateDirection() {

    }

    public void work() {

    }
}
