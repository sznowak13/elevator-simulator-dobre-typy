package com.codecool.elevator.controller;

import com.codecool.elevator.model.Direction;
import com.codecool.elevator.model.Elevator;
import com.codecool.elevator.model.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.Queue;

public class ElevatorController implements Runnable, PropertyChangeListener {
    private Elevator[] elevatorPool;
    private Queue<Person> externalCallQueue = new LinkedList<>();

    public ElevatorController () {
        this.elevatorPool = Elevator.getElevatorPool();
        this.externalCallQueue = Elevator.getExternalQueue();
        for (Elevator elevator: elevatorPool) {
            elevator.addPropertyChangeListener(this);
        }
    }

    private boolean checkExternalQueueForCall() {
        return !externalCallQueue.isEmpty();
    }

    private void sendElevatorToExternalCallFloor() {
        if (checkExternalQueueForCall() && isThereAnyFreeElevator()) {
            Elevator elevator = getFreeElevator();
            elevator.setDestinationFloorLevel(externalCallQueue.peek().getCurrentFloor().getLevel());
        }
    }

    private Elevator getFreeElevator() {
        for (Elevator elevator: elevatorPool) {
            if (elevator.getPeopleList().isEmpty()) return elevator;
        }
        return null;
    }

    private boolean isThereAnyFreeElevator() {
        for (Elevator elevator: elevatorPool) {
            if (elevator.getPeopleList().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            sendElevatorToExternalCallFloor();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Elevator elevator = (Elevator) evt.getOldValue();
    }
}
