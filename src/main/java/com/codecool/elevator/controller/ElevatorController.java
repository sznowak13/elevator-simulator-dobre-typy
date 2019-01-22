package com.codecool.elevator.controller;

import com.codecool.elevator.model.Elevator;
import com.codecool.elevator.view.ElevatorBlock;

public class ElevatorController {
    private Elevator elevator;
    private ElevatorBlock display;


    public ElevatorController(Elevator elevator, ElevatorBlock display) {
        this.elevator = elevator;
        this.display = display;
    }

    public void move() {

    }
}
