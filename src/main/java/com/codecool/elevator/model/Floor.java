package com.codecool.elevator.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class Floor {
    private HashMap<Direction, Queue<Person>> peopleQueue = new HashMap<Direction, Queue<Person>>();
    {
        peopleQueue.put(Direction.UP, new LinkedList<Person>());
        peopleQueue.put(Direction.DOWN, new LinkedList<Person>());
    };

    public void addPerson(Person person) {
        //peopleQueue.get(person.getDesiredDirection()).add(person);
    };

}
