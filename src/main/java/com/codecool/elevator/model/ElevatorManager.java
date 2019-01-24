package com.codecool.elevator.model;

import com.codecool.elevator.view.DisplayConfig;

import java.util.*;

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

    public Elevator searchForAvailableElevator(Call externalCall) {
        int callDirection = externalCall.getDestinationFloorLevel() - externalCall.getStartFloorLevel() > 0 ? 1 : -1;
        List<Elevator> sameDirectionElevators = new ArrayList<>();

        for (Elevator elevator : this.getElevatorPool()) {
            if (elevator.getDirection() == callDirection) {
                if ((externalCall.getStartFloorLevel() - elevator.getCurrentFloorLevel()) * callDirection > 0 ) {
                    sameDirectionElevators.add(elevator);
                }
            } else if (elevator.getDirection() == 0) {
                sameDirectionElevators.add(elevator);
            }
        }

        System.out.println(sameDirectionElevators);

        if (sameDirectionElevators.isEmpty()) {
            return null;
        }

        Elevator tempElevator = sameDirectionElevators.get(0);
        for (int i = 1; i < sameDirectionElevators.size(); i++) { // starting from 1 because we want to skip the first element as it was already assigned line above.
            Elevator elevatorToCheck =  sameDirectionElevators.get(i);
            int floorDifference = Math.abs(elevatorToCheck.getCurrentFloorLevel() - externalCall.getStartFloorLevel());
            if (floorDifference < tempElevator.getCurrentFloorLevel()) {
                tempElevator = elevatorToCheck;
            }
        }



        return tempElevator;
    }

    public Elevator[] getElevatorPool() {
        return elevatorPool;
    }

    public synchronized void addExternalCall(Call call){
        this.externalCalls.add(call);
    }

    @Override
    public void run() {
        while (true) {
            List<Call> tempList = this.getExternalCalls();
            if (!tempList.isEmpty()) {
                for (int i = 0; i < tempList.size(); i++) {
                    Call currentCall = tempList.get(i);
                    Elevator elevator = this.searchForAvailableElevator(currentCall);
                    System.out.println(elevator.getInternalOrders());
                    if (elevator != null) {
                        elevator.addNewCall(currentCall.getStartFloorLevel());
                        externalCalls.remove(i);
                    }
                }
            }
        }
    }

    public synchronized List<Call> getExternalCalls() {
        return this.externalCalls;
    }
}
