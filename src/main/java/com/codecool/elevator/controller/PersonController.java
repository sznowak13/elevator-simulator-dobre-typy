package com.codecool.elevator.controller;

import com.codecool.elevator.model.Person;
import com.codecool.elevator.view.PersonBlock;

public class PersonController {
    private Person person;
    private PersonBlock display;


    public PersonController(Person person, PersonBlock display) {
        this.person = person;
        this.display = display;
    }

    public void move() {

    }
}
