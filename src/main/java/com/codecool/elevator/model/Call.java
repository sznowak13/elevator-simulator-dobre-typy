package com.codecool.elevator.model;

public class Call {
    private int startFloorLevel;
    private int destinationFloorLevel;



    public Call(int start, int dest) {
        this.startFloorLevel = start;
        this.destinationFloorLevel = dest;
    }

    public int getStartFloorLevel() {
        return this.startFloorLevel;
    }

    public int getDestinationFloorLevel() {
        return this.destinationFloorLevel;
    }

    public String toString() {
        return startFloorLevel + "/" + destinationFloorLevel;
    }
}
