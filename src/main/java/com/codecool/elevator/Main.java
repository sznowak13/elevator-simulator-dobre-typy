package com.codecool.elevator;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.application.Application;

import java.awt.*;

public class Main extends Application {

    private final static double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final static double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private final static int FLOORS_AMOUNT = 50;
    private final static int ELEVATORS_AMOUNT = 4;

    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);
        GridPane floorGrid = new GridPane();
        GridPane elevatorGrid = new GridPane();

        for (int i = 0; i < FLOORS_AMOUNT; i++) {
            Rectangle rect = new Rectangle((SCREEN_WIDTH - (SCREEN_WIDTH / (ELEVATORS_AMOUNT*2))), SCREEN_HEIGHT / FLOORS_AMOUNT);
            rect.setFill(Color.GRAY);
            rect.setStroke(Color.BLACK);
            GridPane.setConstraints(rect, 0 ,i);
            floorGrid.getChildren().addAll(rect);
        }

        for (int i = 0; i < ELEVATORS_AMOUNT; i++) {
            Rectangle rect = new Rectangle((SCREEN_WIDTH / (ELEVATORS_AMOUNT*2))/ELEVATORS_AMOUNT, SCREEN_HEIGHT);
            rect.setFill(Color.DARKGRAY);
            rect.setStroke(Color.GREEN);
            GridPane.setConstraints(rect, i ,0);
            elevatorGrid.getChildren().add(rect);
        }
        hBox.getChildren().addAll(elevatorGrid, floorGrid);

        Scene scene = new Scene(hBox, SCREEN_WIDTH, SCREEN_HEIGHT);

        primaryStage.setTitle("Elewator symulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

//rect.setX(100/ELEVATORS_AMOUNT);