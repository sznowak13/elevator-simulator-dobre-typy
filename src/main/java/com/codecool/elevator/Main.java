package com.codecool.elevator;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;

import java.awt.*;

public class Main extends Application {

    private final static double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final static double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public void start(Stage primaryStage) throws Exception {
        StackPane sp = new StackPane();
        Scene scene = new Scene(sp, SCREEN_WIDTH, SCREEN_HEIGHT);

        primaryStage.setTitle("Elewator symulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
