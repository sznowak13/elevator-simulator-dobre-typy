package com.codecool.elevator.view;

import com.codecool.elevator.model.Elevator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ElevatorDisplay extends Pane {
    ElevatorBlock elevator;

    public ElevatorDisplay (HBox elevatorShaft, Elevator currentElevator) {
        elevator = new ElevatorBlock((int) DisplayConfig.getShaft_width(), (int) DisplayConfig.getFloor_height(), currentElevator);
        elevator.setFill(Color.RED);
        elevator.setLayoutX(DisplayConfig.getShaft_width() * currentElevator.getId());
        elevator.setLayoutY(DisplayConfig.getFloor_height() * (DisplayConfig.getFloorsAmount() - 1));
        elevatorShaft.getChildren().add(elevator);
    }
}
