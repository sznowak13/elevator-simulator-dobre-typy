package com.codecool.elevator.model;

import java.util.*;


public class Floor {
    private int level;
    private List<Person> peopleList;
    private List<Person> availableElevators;


    public Floor(int level) {
        this.level = level;
        this.peopleList = new ArrayList<>();
        this.availableElevators = new ArrayList<>();
    }

    public void processArrivedElevator(Elevator elevator) {

    }
}
