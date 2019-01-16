package com.codecool.elevator.view;

import com.codecool.elevator.model.Direction;
import com.codecool.elevator.model.Elevator;
import com.codecool.elevator.model.Person;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class PersonBlock extends Rectangle implements PropertyChangeListener {
    Person person;

    public PersonBlock(Person person) {
        super(DisplayConfig.getShaft_width() / 3, DisplayConfig.getFloor_height() / 2, Color.BLUE);
        this.person = person;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
