package com.codecool.elevator.model;

import java.util.HashMap;
import java.util.LinkedList;

public class Floor {
    private static int idSequence = 0;

    private int level;
    private HashMap<Direction, LinkedList<Person>> peopleQueue = new HashMap<>();
    {
        peopleQueue.put(Direction.UP, new LinkedList<>());
        peopleQueue.put(Direction.DOWN, new LinkedList<>());
    }

    Floor() {
        this.level = idSequence++;
    }

    public int getLevel() {
        return level;
    }

    public void addPerson(Person person) {
        peopleQueue.get(person.getDesiredDirection()).add(person);
    };

    public void removePersonFromQueue(Person person) {
        Direction personDirection = person.getDesiredDirection();
        peopleQueue.get(personDirection).remove(person);
    }

    public LinkedList<Person> getPeopleByDesiredDirection(Direction direction) {
        return peopleQueue.get(direction);
    }
}
