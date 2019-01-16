package com.codecool.elevator;

import com.codecool.elevator.model.Building;
import com.codecool.elevator.model.Consts;
import com.codecool.elevator.view.BuildingBackground;
import com.codecool.elevator.view.DisplayConfig;
import com.codecool.elevator.view.ElevatorDisplay;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;


public class ViewTest extends Application {
    BuildingBackground bb;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Building b = new Building(Consts.FLOORS_AMOUNT,Consts.ELEVATORS_AMOUNT);
        Thread bThread = new Thread(b);
        bThread.start();

        bb = new BuildingBackground(b);

        primaryStage.setScene(new Scene(bb, DisplayConfig.SCREEN_WIDTH, DisplayConfig.SCREEN_HEIGHT));
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}
