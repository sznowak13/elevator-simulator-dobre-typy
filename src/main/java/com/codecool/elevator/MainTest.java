package com.codecool.elevator;

import com.codecool.elevator.model.Building;
import com.codecool.elevator.model.Direction;

public class MainTest {
    public static void main(String[] args) {
        Building building = new Building();

        Thread buildingThread = new Thread(building);
        buildingThread.start();
    }
}
