package com.codecool.elevator;

import com.codecool.elevator.model.Building;
import com.codecool.elevator.view.BuildingBackground;
import com.codecool.elevator.view.DisplayConfig;
import com.codecool.elevator.view.ObjectsDisplay;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;


public class ViewTest extends Application {
    BuildingBackground bb;
    ObjectsDisplay od;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Building b = new Building(20,2);
        Thread bThread = new Thread(b);
        bThread.start();

        bb = new BuildingBackground(b);
        od = new ObjectsDisplay(b.getElevatorPool());
        bb.getChildren().add(od);

        primaryStage.setScene(new Scene(bb, DisplayConfig.SCREEN_WIDTH, DisplayConfig.SCREEN_HEIGHT));
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}
