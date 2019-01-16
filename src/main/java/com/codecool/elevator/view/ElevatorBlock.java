package com.codecool.elevator.view;

import com.codecool.elevator.model.Direction;
import com.codecool.elevator.model.Elevator;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ElevatorBlock extends Rectangle implements PropertyChangeListener {
    Elevator elevator;

    public ElevatorBlock(int width, int height, Elevator elevator) {
        super(width, height);
        this.elevator = elevator;
        this.elevator.addPropertyChangeListener(this);
        this.setFill(Color.LIGHTSALMON);
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int moveAmount = (int) DisplayConfig.getFloor_height();
        int amount;
        if (elevator.getDirection() == Direction.UP) {
            amount = -moveAmount;
        } else if (elevator.getDirection() == Direction.DOWN) {
            amount = moveAmount;
        } else {
            amount = 0;
        }

        TranslateTransition tt = new TranslateTransition(Duration.millis(800), this);
        tt.setByY(amount);
        tt.play();
    }
}
