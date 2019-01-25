package com.codecool.elevator.view;

import com.codecool.elevator.model.Person;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class PersonBlock extends Rectangle {

    public PersonBlock() {
        super(DisplayConfig.PERSON_WIDTH, DisplayConfig.PERSON_HEIGHT, DisplayConfig.PERSON_COLOR);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.INSIDE);
    }

    public void update(Person person) {
        this.setLayoutX(person.getPosX());
        this.setLayoutY(person.getPosY());
        if (person.isArrived()) {
            setFill(Color.GREEN);
        }
    }
}
