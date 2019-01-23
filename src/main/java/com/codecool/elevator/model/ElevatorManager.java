package com.codecool.elevator.model;

import com.codecool.elevator.view.DisplayConfig;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager implements Runnable {
    private Elevator[] elevatorPool;
    private List<Call> externalCalls = new ArrayList<>();


    private static ElevatorManager ourInstance = new ElevatorManager();

    public static ElevatorManager getInstance() {
        return ourInstance;
    }

    private ElevatorManager() {
        this.elevatorPool = new Elevator[Config.ELEVATORS_AMOUNT];
        for (int i = 0; i < Config.ELEVATORS_AMOUNT; i++) {
            elevatorPool[i] = new Elevator(i * DisplayConfig.ELEVATOR_WIDTH, DisplayConfig.SCREEN_HEIGHT - DisplayConfig.FLOOR_HEIGHT);
        }
    }

    public void sendElevatorTo(int floorLevel) {

    }

    public void searchForAvailableElevator(Call externalCall) {

    }

    public Elevator[] getElevatorPool() {
        return elevatorPool;
    }

    public void addExternalCall(Call call){
        this.externalCalls.add(call);
    }

    @Override
    public void run() {

    }
}
