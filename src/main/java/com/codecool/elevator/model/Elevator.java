package com.codecool.elevator.model;

import com.codecool.elevator.view.DisplayConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Elevator extends MovingEntity implements Runnable {
    private int currentFloorLevel;
    private Integer destinationFloorLevel;
    private boolean moving;
    private List<Person> peopleInsideList;
    private TreeSet<Integer> internalOrders;



    public Elevator(double X, double Y) {
        super(X, Y, 0);
        this.currentFloorLevel = 0;
        this.moving = false;
        this.peopleInsideList = new ArrayList<>();
        this.internalOrders = new TreeSet<>();
    }

    public int getCurrentFloorLevel() {
        return currentFloorLevel;
    }

    public void updateDirection() {
        if (destinationFloorLevel - currentFloorLevel > 0) {
            this.setDirection(1);
        } else if (destinationFloorLevel - currentFloorLevel == 0 && internalOrders.isEmpty()) {
            this.setDirection(0);
        } else if (destinationFloorLevel - currentFloorLevel < 0) {
            this.setDirection(-1);
        }
    }

    public synchronized TreeSet<Integer> getInternalOrders() {
        return this.internalOrders;
    }

    public void addNewCall(int floorLevel) {
        this.getInternalOrders().add(floorLevel);
    }

    public int getNextFloorLevel() {
        int nextFloorLevel;

        if (getDirection() == 1) {
            nextFloorLevel = this.getInternalOrders().first();
        } else  {
            nextFloorLevel = this.getInternalOrders().last();
        }

        return nextFloorLevel;
    }

    public void letPeopleOut(){
        for (int i = 0; i < peopleInsideList.size(); i++) {
            peopleInsideList.get(i).getOutTheElevator(this);
        }

    }

    public void removePerson(Person person) {
        peopleInsideList.remove(person);
    }

    public void addPerson(Person person) {
        if (!this.peopleInsideList.contains(person)) this.peopleInsideList.add(person);
    }

    public void waitForPeople() {
        this.getInternalOrders().remove(destinationFloorLevel);
        if (!getInternalOrders().isEmpty()){
            this.destinationFloorLevel = this.getNextFloorLevel();
        }

        this.updateDirection();
        this.letPeopleOut();
        Building.getFloorList().get(currentFloorLevel).invitePeopleElevator(this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isMoving() {
        return moving;
    }

    @Override
    public void move() {
        this.setPosY(this.getPosY() - this.getDirection());
        double elevatorEdge = (getDirection() == 1) ? DisplayConfig.SCREEN_HEIGHT-DisplayConfig.FLOOR_HEIGHT: DisplayConfig.SCREEN_HEIGHT;
        this.currentFloorLevel = (int) ((elevatorEdge - this.getPosY()) / DisplayConfig.FLOOR_HEIGHT);
        for (Person person: peopleInsideList) {
            person.setPosX(this.getPosX() + DisplayConfig.ELEVATOR_WIDTH / 4);
            person.setPosY(this.getPosY() + DisplayConfig.PERSON_HEIGHT);
        }
    }

    @Override
    public void run() {
        while (true) {
            if (this.getInternalOrders().isEmpty()) {
            } else {
                this.destinationFloorLevel = this.getNextFloorLevel();
                this.updateDirection();

                if (currentFloorLevel != destinationFloorLevel) {
                    move();
                    moving = true;
                } else {
                    moving = false;
                    System.out.println(getDirection());
                    this.waitForPeople();
                }

                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
