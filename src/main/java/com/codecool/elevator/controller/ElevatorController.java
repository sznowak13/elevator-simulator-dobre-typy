package com.codecool.elevator.controller;

import com.codecool.elevator.model.Elevator;
import com.codecool.elevator.model.Person;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class ElevatorController implements Runnable, Observer {
    private Elevator[] elevatorPool;
    private Queue<Person> externalCallQueue = new LinkedList<>();

    public ElevatorController () {
        this.elevatorPool = Elevator.getElevatorPool();
        this.externalCallQueue = Elevator.getExternalQueue();
    }

    private boolean checkExternalQueueForCall() {
        return externalCallQueue.isEmpty();
    }

    @Override
    public void run() {
        while (true) {
            
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
