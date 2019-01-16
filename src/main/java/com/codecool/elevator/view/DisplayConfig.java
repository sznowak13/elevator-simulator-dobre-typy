package com.codecool.elevator.view;

import java.awt.*;

public class DisplayConfig {

    public final static double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final static double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private static double floor_height;
    private static double shaft_width;
    private static double floor_width;
    private static double floorsAmount;
    private static double elevatorsAmount;

    public static void setFloor_height(double floor_height) {
        DisplayConfig.floor_height = floor_height;
    }

    public static void setShaft_width(double shaft_width) {
        DisplayConfig.shaft_width = shaft_width;
    }

    public static void setFloor_width(double floor_width) {
        DisplayConfig.floor_width = floor_width;
    }

    public static double getFloor_height() {
        return floor_height;
    }

    public static double getShaft_width() {
        return shaft_width;
    }

    public static double getFloor_width() {
        return floor_width;
    }

    public static double getFloorsAmount() {
        return floorsAmount;
    }

    public static void setFloorsAmount(double floorsAmount) {
        DisplayConfig.floorsAmount = floorsAmount;
    }

    public static double getElevatorsAmount() {
        return elevatorsAmount;
    }

    public static void setElevatorsAmount(double elevatorsAmount) {
        DisplayConfig.elevatorsAmount = elevatorsAmount;
    }
}
