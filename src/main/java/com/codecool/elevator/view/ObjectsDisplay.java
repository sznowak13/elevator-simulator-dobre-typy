package com.codecool.elevator.view;

import com.codecool.elevator.model.Elevator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ObjectsDisplay extends Pane {
    List<ElevatorBlock> elevators = new ArrayList<>();

    public ObjectsDisplay(Elevator[] elevatorList) {
        for (Elevator elevator : elevatorList) {
            ElevatorBlock el = new ElevatorBlock((int) DisplayConfig.getShaft_width(), (int) DisplayConfig.getFloor_height(), elevator);
            el.setFill(Color.RED);
            el.setLayoutX(DisplayConfig.getShaft_width() * elevator.getId());
            el.setLayoutY(DisplayConfig.getFloor_height() * (DisplayConfig.getFloorsAmount() - 1));
            elevators.add(el);
            this.getChildren().add(el);
        }
    }

    public List<ElevatorBlock> getElevators() {
        return elevators;
    }
}
