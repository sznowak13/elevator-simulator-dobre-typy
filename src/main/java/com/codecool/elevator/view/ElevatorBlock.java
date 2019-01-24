package com.codecool.elevator.view;

import com.codecool.elevator.model.Elevator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ElevatorBlock extends Rectangle {

    public ElevatorBlock() {
        super(DisplayConfig.ELEVATOR_WIDTH, DisplayConfig.FLOOR_HEIGHT);
        setFill(DisplayConfig.ELEVATOR_COLOR_MOVING);
        setStroke(Color.BLACK);
    }

    public void update(Elevator elevator) {
        this.setLayoutY(elevator.getPosY());
        if (elevator.isMoving()) {
            setFill(DisplayConfig.ELEVATOR_COLOR_MOVING);
        } else {
            setFill(DisplayConfig.ELEVATOR_COLOR_STOP);
        }
    }
}
