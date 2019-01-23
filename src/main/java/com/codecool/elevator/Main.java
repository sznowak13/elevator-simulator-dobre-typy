package com.codecool.elevator;

import com.codecool.elevator.model.Building;
import com.codecool.elevator.model.Elevator;
import com.codecool.elevator.view.BuildingDisplay;
import com.codecool.elevator.view.DisplayConfig;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application {
    @Override
    public void init() {
        DisplayConfig.setup();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Building building = Building.getInstance();
        BuildingDisplay buildingDisplay = new BuildingDisplay(building);
        new Thread(building).start();
        new Thread(building.getAndrzej()).start();
        for (Elevator elevator: building.getAndrzej().getElevatorPool()) {
            new Thread(elevator).start();
        }


        Scene scene = new Scene(buildingDisplay, DisplayConfig.SCREEN_WIDTH, DisplayConfig.SCREEN_HEIGHT);



        primaryStage.setTitle("Elewator symulator");
        primaryStage.setScene(scene);
        primaryStage.show();

        buildingDisplay.startAnimation();


    }

    @Override
    public void stop() {
        System.exit(0);
    }
}
