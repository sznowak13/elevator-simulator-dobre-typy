package com.codecool.elevator.model;

import java.util.List;

public class ElevatorManager implements Runnable {
    private Elevator[] elevatorPool;
    private List<Call> externalCalls;


    private static ElevatorManager ourInstance = new ElevatorManager();

    public static ElevatorManager getInstance() {
        return ourInstance;
    }

    private ElevatorManager() {
    }

    public void sendElevatorTo(int floorLevel) {

    }

    public void searchForAvailableElevator(Call externalCall) {

    }

    @Override
    public void run() {

    }
}
