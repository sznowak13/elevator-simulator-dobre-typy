package com.codecool.elevator.view;

import com.codecool.elevator.model.Building;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class BuildingBackground extends StackPane {
    HBox bg = new HBox();
    HBox elevatorShafts = new HBox();
    VBox floors = new VBox();

    public BuildingBackground(Building building) {
        setupConfig(building.getElevatorPool().length, building.getFloorList().size());

        for (int i = 0; i < DisplayConfig.getFloorsAmount(); i++) {
            Rectangle floor = new Rectangle(DisplayConfig.getFloor_width(), DisplayConfig.getFloor_height());
            floor.setFill(Color.BLACK);
            floor.setStrokeType(StrokeType.INSIDE);
            floors.getChildren().add(floor);
        }
        for (int i = 0; i < DisplayConfig.getElevatorsAmount(); i++) {
            Rectangle elevatorShaft = new Rectangle(DisplayConfig.getShaft_width(), DisplayConfig.SCREEN_HEIGHT);
            elevatorShaft.setStroke(Color.DARKGRAY);
            elevatorShaft.setStrokeType(StrokeType.INSIDE);
            elevatorShaft.setFill(Color.GRAY);
            elevatorShafts.getChildren().add(elevatorShaft);
        }
        bg.getChildren().addAll(elevatorShafts, floors);
        this.getChildren().add(bg);
    }

    private static void setupConfig(int elevatorsAmount, int floorAmount) {
        DisplayConfig.setElevatorsAmount(elevatorsAmount);
        DisplayConfig.setFloorsAmount(floorAmount);
        DisplayConfig.setFloor_height(DisplayConfig.SCREEN_HEIGHT / floorAmount);
        DisplayConfig.setShaft_width(DisplayConfig.getFloor_height() / 0.6);
        DisplayConfig.setFloor_width(DisplayConfig.SCREEN_WIDTH - (DisplayConfig.getShaft_width() * elevatorsAmount));
    }
}
