package com.codecool.elevator;

import com.codecool.elevator.view.DisplayConfig;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;

import java.awt.*;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        StackPane sp = new StackPane();
        Scene scene = new Scene(sp, DisplayConfig.SCREEN_WIDTH, DisplayConfig.SCREEN_HEIGHT);

        primaryStage.setTitle("Elewator symulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
