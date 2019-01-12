package com.codecool.elevator.model;

import com.codecool.elevator.controller.ElevatorController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Building implements Runnable{
    private List<Floor> floorList = new ArrayList<>();
    private Elevator[] elevatorPool;
    private Queue<Person> peoplePool = new LinkedList<>();
    private ElevatorController Andrzej;

    public Building() {
        this(Consts.FLOORS_AMOUNT, Consts.ELEVATORS_AMOUNT);
    }

    public Building(int floorsAmount, int elevatorsAmount) {
        createPeople(floorsAmount * Consts.MAX_FLOOR_CAP);
        createFloors(floorsAmount);
        createElevators(elevatorsAmount);
        Andrzej = new ElevatorController();
    }

    private void createPeople(int amount) {
        for (int i = 0; i < amount; i++) {
            peoplePool.add(new Person());
        }

        Person.setPeoplePool(this.peoplePool);
    }

    private void createElevators(int amount) {
        this.elevatorPool = new Elevator[amount];
        for (int i = 0; i < amount; i++) {
            this.elevatorPool[i] = new Elevator();
        }
        Elevator.setElevatorPool(this.elevatorPool);
    }

    private void createFloors(int amount) {
        for (int i = 0; i < amount; i++) {
            this.floorList.add(new Floor());
        }
        Floor.setFloorList(this.floorList);
    }

    public Elevator[] getElevatorPool() {
        return elevatorPool;
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    @Override
    public void run() {
        new Thread(Andrzej).start();
        for (Elevator elevator: elevatorPool) {
            new Thread(elevator).start();
        }


        while (true) {
            Floor randomFloor;
            do {
                randomFloor = Floor.getRandomFloor();
            } while (randomFloor.getCurrentCap() >= Consts.MAX_FLOOR_CAP);

            //if (!peoplePool.isEmpty()) {
                Person person = peoplePool.poll();
                person.spawn(randomFloor);
            //}

            System.out.println(peoplePool.size());
            for (Floor floor: floorList) {
                System.out.println("Level: " + floor.getLevel() + " Size: " + floor.getCurrentCap());
            }
            for (Elevator elevator: elevatorPool) {
                System.out.println("Current level: " + elevator.getCurrentFloorLevel() + " Current Capacity: " + elevator.getPeopleList().size() + " Dir: " + elevator.getDirection());
            }

            System.out.println("TU JESTEM: " + person.getCurrentFloor().getLevel() + " A TU CHCE BYC: " + person.getDestFloor().getLevel());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
