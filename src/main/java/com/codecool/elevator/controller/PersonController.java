package com.codecool.elevator.controller;

import com.codecool.elevator.model.Person;
import com.codecool.elevator.view.PersonBlock;

public class PersonController {
    private Person person;
    private PersonBlock display;


    public PersonController(Person person, PersonBlock display) {
        this.person = person;
        this.display = display;
        this.display.setLayoutX(person.getPosX());
        this.display.setLayoutY(person.getPosY());
    }

    public PersonBlock getPersonBlock() {
        return display;
    }

    public void move() {
        this.person.move();
        this.display.update(person);
    }
}
