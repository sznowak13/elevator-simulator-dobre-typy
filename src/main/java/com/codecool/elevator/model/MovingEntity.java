package com.codecool.elevator.model;

public abstract class MovingEntity {
    private double posX;
    private double posY;
    private int direction;


    public MovingEntity() {
        this(0, 0,0);
    }

    public MovingEntity(double x, double y, int direction) {
        this.posX = x;
        this.posY = y;
        this.direction = direction;
    }

    public double getPosX() {
        return this.posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public abstract void move();
}
