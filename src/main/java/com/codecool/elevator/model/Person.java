package com.codecool.elevator.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class Person implements Observer {
    private Floor currentFloor;
    private Floor destFloor;
    private Direction desiredDirection;

    private static Queue<Person> peoplePool;

    public static Queue<Person> getPeoplePool() {
        return peoplePool;
    }

    public static void setPeoplePool(Queue<Person> peoplePool) {
        Person.peoplePool = peoplePool;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDestFloor(Floor destFloor) {
        this.destFloor = destFloor;
    }

    public void setDesiredDirection(Direction desiredDirection) {
        this.desiredDirection = desiredDirection;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Floor getDestFloor() {
        return destFloor;
    }

    public Direction getDesiredDirection() {
        return desiredDirection;
    }

    @Override
    public void update(Observable o, Object floorLevel) {
        Elevator elevator = (Elevator) o;
        int elevatorsFloorLevel = (int) floorLevel;

        if ((int) elevatorsFloorLevel == this.getCurrentFloor().getLevel() && elevator.getDirection() == desiredDirection) {
            if (!elevator.checkIfPersonIsInside(this)) {
                this.getInElevator(elevator);
                 if (destFloor.getLevel() > elevator.getDestinationFloorLevel() && elevator.getDirection() == Direction.UP) {
                    elevator.setDestinationFloorLevel(destFloor.getLevel());
                } else if (destFloor.getLevel() < elevator.getDestinationFloorLevel() && elevator.getDirection() == Direction.DOWN) {
                    elevator.setDestinationFloorLevel(destFloor.getLevel());
                }
            } else {
                if (elevatorsFloorLevel == this.getDestFloor().getLevel()) {
                    this.getOutTheElevator(elevator);
                }
            }
        }
//        if (!elevator.checkIfPersonIsInside(this)) {
//            if ((int) floorLevel == currentFloor.getLevel() && elevator.getDirection() == desiredDirection) {
//                this.getInElevator(elevator);
//                if (destFloor.getLevel() > elevator.getDestinationFloorLevel() && elevator.getDirection() == Direction.UP) {
//                    elevator.setDestinationFloorLevel(destFloor.getLevel());
//                }
//                else if (destFloor.getLevel() < elevator.getDestinationFloorLevel() && elevator.getDirection() == Direction.DOWN) {
//                    elevator.setDestinationFloorLevel(destFloor.getLevel());
//                }
//            }
//        } else {
//            if ((int) floorLevel == destFloor.getLevel()) {
//                this.getOutTheElevator(elevator);
//            }
//        }

    }

    public void spawn(Floor floor) {
        setCurrentFloor(floor);
        do {
            setDestFloor(Floor.getRandomFloor());
        } while (this.destFloor == this.currentFloor);

        setDesiredDirection((destFloor.getLevel() - currentFloor.getLevel() < 0) ? Direction.UP : Direction.DOWN);

        for (Elevator elevator: Elevator.getElevatorPool()) {
            elevator.addObserver(this);
        }

        floor.addPerson(this);
    }

    public void callAnElevator() {
        Elevator.addToExternalQueue(this);
    }

    public void getInElevator(Elevator elevator) {
        System.out.println(this + " wsiada DO WINDY");
        if (elevator.getPeopleList().size() < Consts.MAX_ELEVATOR_CAP) {
            this.currentFloor.removePersonFromQueue(this);
            elevator.addPerson(this);
            elevator.removePersonFromExternalQueue(this);
        }
    }

    public void getOutTheElevator(Elevator elevator) {
        System.out.println((this + "Wysiada z windy"));
        elevator.getPeopleList().remove(this);
        if (!peoplePool.contains(this)) Person.getPeoplePool().add(this);
    }
}
