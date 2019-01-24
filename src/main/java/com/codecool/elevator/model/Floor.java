package com.codecool.elevator.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;


public class Floor {
    private int level;
    private List<Person> peopleList;
    private List<Elevator> availableElevators;
    private PropertyChangeSupport support;


    public Floor(int level) {
        this.level = level;
        this.peopleList = new ArrayList<>();
        this.availableElevators = new ArrayList<>();
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void processArrivedElevator(Elevator elevator) {

    }

    public void addPerson(Person person) {
        if (!peopleList.contains(person)) this.peopleList.add(person);
    }

    public void removePerson(Person person) {
        this.peopleList.remove(person);
    }

    public void invitePeopleElevator(Elevator elevator) {
        for (int i = 0; i < peopleList.size(); i++) {
            peopleList.get(i).getInElevator(elevator);
        }
    }

    public synchronized String toString() {
        return "Floor - " + level + " \nPeople: " + peopleList + "\nElevators: " + availableElevators;
    }
}
