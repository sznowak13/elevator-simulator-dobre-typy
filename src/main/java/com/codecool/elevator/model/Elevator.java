package com.codecool.elevator.model;

import com.codecool.elevator.view.DisplayConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Elevator extends MovingEntity implements Runnable {
    private int currentFloorLevel;
    private Integer destinationFloorLevel;
    private boolean isMoving;
    private List<Person> peopleInsideList;
    private TreeSet<Integer> internalOrders;



    public Elevator(double X, double Y) {
        super(X, Y, 0);
        this.currentFloorLevel = 0;
        this.isMoving = false;
        this.peopleInsideList = new ArrayList<>();
        this.internalOrders = new TreeSet<>();
    }

    public int getCurrentFloorLevel() {
        return currentFloorLevel;
    }

    public void updateDirection() {
        if (destinationFloorLevel - currentFloorLevel > 0) {
            this.setDirection(1);
        } else if (destinationFloorLevel - currentFloorLevel == 0) {
            this.setDirection(0);
        } else {
            this.setDirection(-1);
        }
    }

    public void work() {

    }

    public synchronized TreeSet<Integer> getInternalOrders() {
        return this.internalOrders;
    }

    public void addNewCall(int floorLevel) {
        this.getInternalOrders().add(floorLevel);
    }

    @Override
    public void move() {
        this.setPosY(this.getPosY() - this.getDirection());
        this.currentFloorLevel = (int) (((DisplayConfig.SCREEN_HEIGHT-DisplayConfig.FLOOR_HEIGHT) - this.getPosY()) / DisplayConfig.FLOOR_HEIGHT);
    }

    @Override
    public void run() {
        while (true) {
            if (this.getInternalOrders().isEmpty()) {
                // smth
            } else {
                this.destinationFloorLevel = this.getInternalOrders().first();
                this.updateDirection();

                if (currentFloorLevel != destinationFloorLevel) {
                    move();
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
