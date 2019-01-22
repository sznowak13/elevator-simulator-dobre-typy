package main.java.com.codecool.elevator.model;

public abstract class MovingEntity {
    private int posX;
    private int posY;
    private int direction;


    public MovingEntity() {
        this.posX = 0;
        this.posY = 0;
        this.direction = 0;
    }

    public MovingEntity(int x, int y, int direction) {
        this.posX = x;
        this.posY = y;
        this.direction = direction;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public abstract void move();
}
