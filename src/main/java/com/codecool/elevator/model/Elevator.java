package com.codecool.elevator.model;

import java.util.HashMap;

public class Elevator {
    private int currentFloorLevel;
    private static int maxPeopleCap;
    private int currentCap;
    private HashMap<Integer, Person[]> destFloor;
    private Direction direction = Direction.NONE;
}
