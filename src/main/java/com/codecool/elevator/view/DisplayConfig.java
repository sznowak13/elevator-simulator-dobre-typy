package com.codecool.elevator.view;

import com.codecool.elevator.model.Config;
import javafx.scene.paint.Color;
import java.awt.*;


public class DisplayConfig {
    public static double FLOOR_HEIGHT;
    public static double FLOOR_WIDTH;
    public static double ELEVATOR_WIDTH;
    public static double PERSON_WIDTH;
    public static double PERSON_HEIGHT;
    public final static Color FLOOR_COLOR = Color.GRAY;
    public final static Color ELEVATOR_COLOR = Color.RED;
    public final static Color PERSON_COLOR = Color.BLUE;
    public final static double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.9;
    public final static double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.9;

    public static void setup() {
        FLOOR_HEIGHT = SCREEN_HEIGHT / Config.FLOORS_AMOUNT;
        ELEVATOR_WIDTH = FLOOR_HEIGHT * 0.6; // scaling value of elevator width
        FLOOR_WIDTH = SCREEN_WIDTH - (ELEVATOR_WIDTH * Config.ELEVATORS_AMOUNT);
        PERSON_HEIGHT = FLOOR_HEIGHT / 2;
        PERSON_WIDTH = ELEVATOR_WIDTH / 2;
    }
}
