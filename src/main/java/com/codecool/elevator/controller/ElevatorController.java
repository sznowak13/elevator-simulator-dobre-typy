package com.codecool.elevator.controller;

import com.codecool.elevator.model.Consts;
import com.codecool.elevator.model.Direction;
import com.codecool.elevator.model.Elevator;
import com.codecool.elevator.model.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.*;

public class ElevatorController implements Runnable, PropertyChangeListener {
    private Elevator[] elevatorPool;
    private static Queue<ArrayList<Object>> externalCallQueue = new LinkedList<>();

    public ElevatorController () {
        this.elevatorPool = Elevator.getElevatorPool();
        for (Elevator elevator: elevatorPool) {
            elevator.addPropertyChangeListener(this);
        }
    }

    public static void removePersonsExternalCalling(ArrayList<Object> elevatorCalling) {
        externalCallQueue.remove(elevatorCalling);
    }

    public static void callAnElevator(ArrayList<Object> elevatorCalling) {
        int callingFloorLevel = (int) elevatorCalling.get(0);
        Direction callingDirection = (Direction) elevatorCalling.get(1);

        if (callingDirection == Direction.NONE || callingFloorLevel < 0 || callingFloorLevel > Consts.FLOORS_AMOUNT-1) {
            System.out.println("Wrong elevator calling floorLevel or direction");
            return;
        }

        externalCallQueue.add(elevatorCalling);
    }

    private boolean checkExternalQueueForCall() {
        return !externalCallQueue.isEmpty();
    }

    private void sendElevatorToExternalCallFloor() {
    }


    private  boolean checkIfThereIsAnyFreeElevator() {
        for (Elevator elevator: elevatorPool) {
            if (elevator.getPeopleList().isEmpty() && elevator.getDirection() == Direction.NONE) {
                return true;
            }
        }
        return false;
    }

    private Elevator getFreeElevator() {
        for (Elevator elevator: elevatorPool) {
            if (elevator.getPeopleList().isEmpty() && elevator.getDirection() == Direction.NONE) return elevator;
        }
        return null;
    }



    public static int getClosestExternalCallLevel(Direction direction, int currentFloorLevel) {
        SortedSet<Integer> floorsInDesiredDirection = new TreeSet<>();
        // if direction == UP we will search for floors higher than currentFloorLevel else lower.

        for (ArrayList<Object> externalCall : externalCallQueue) {
            int externalCallFloorLevel = (int) externalCall.get(0);
            if (direction == Direction.UP) {
                if (externalCallFloorLevel > currentFloorLevel) {
                    floorsInDesiredDirection.add(externalCallFloorLevel);
                }
            } else if (direction == Direction.DOWN){
                if (externalCallFloorLevel < currentFloorLevel) {
                    floorsInDesiredDirection.add(externalCallFloorLevel);
                }
            } else {
                floorsInDesiredDirection.add(externalCallFloorLevel);
            }
        }
        System.out.println(floorsInDesiredDirection);
        if (floorsInDesiredDirection.size() == 0) {
            throw new NullPointerException();
        }
        if (direction == Direction.UP) {
            return floorsInDesiredDirection.first();
        } else if (direction == Direction.DOWN){
            return floorsInDesiredDirection.last();
        } else {
            for (int e : floorsInDesiredDirection) {
                if (e - currentFloorLevel < 0) {
                    e = -(e - currentFloorLevel);
                } else {
                    e = e - currentFloorLevel;
                }
            }
            return floorsInDesiredDirection.first();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (checkExternalQueueForCall() && checkIfThereIsAnyFreeElevator()) {
                Elevator freeElevator = getFreeElevator();
                try {
                    freeElevator.setDestinationFloorLevel(getClosestExternalCallLevel(Direction.NONE, freeElevator.getCurrentFloorLevel()));
                } catch (NullPointerException ex) {
                    System.out.println(ex);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Elevator elevator = (Elevator) evt.getOldValue();

        Direction elevatorsDirection = elevator.getDirection();
        int currentElevatorsFloorLevel = (int) evt.getNewValue();

        if (checkExternalQueueForCall()) {
        }
    }
}
