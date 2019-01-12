package com.codecool.elevator.view;

import com.codecool.elevator.model.Direction;
import com.codecool.elevator.model.Elevator;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

public class ElevatorBlock extends Rectangle implements PropertyChangeListener {
    Elevator elevator;

    public ElevatorBlock(int width, int height, Elevator elevator) {
        super(width, height);
        this.elevator = elevator;
        this.elevator.addPropertyChangeListener(this);
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
        System.out.println("ruch windy");
        TranslateTransition tt = new TranslateTransition(Duration.millis(800), this);
        int amount = (elevator.getDirection() == Direction.UP) ? -moveAmount : moveAmount;
        tt.setByY(amount);
        tt.play();
        this.setTranslateY(amount);
        System.out.println(this.getLayoutY());
    }
}
