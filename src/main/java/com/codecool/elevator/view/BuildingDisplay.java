package com.codecool.elevator.view;

import com.codecool.elevator.controller.ElevatorController;
import com.codecool.elevator.controller.PersonController;
import com.codecool.elevator.model.Building;
import com.codecool.elevator.model.Config;
import com.codecool.elevator.model.Elevator;
import com.codecool.elevator.model.Person;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BuildingDisplay extends Pane {
    private List<ElevatorController> elevatorControllers;
    private List<PersonController> peopleControllers;


    public BuildingDisplay(Building building) {
        this.elevatorControllers = new ArrayList<>();
        this.peopleControllers = new ArrayList<>();

        this.initFloors();
        this.initElevators(building.getAndrzej().getElevatorPool());
        this.initPeople(building.getPeoplePool());
    }

    private void initFloors() {
        double floorPosX = DisplayConfig.ELEVATOR_WIDTH*Config.ELEVATORS_AMOUNT;

        for (int i = 0; i < Config.FLOORS_AMOUNT; i++) {
            double floorPosY = i * DisplayConfig.FLOOR_HEIGHT;
            Rectangle floor = new Rectangle(floorPosX, floorPosY, DisplayConfig.FLOOR_WIDTH, DisplayConfig.FLOOR_HEIGHT);
            floor.setFill(DisplayConfig.FLOOR_COLOR);
            floor.setStroke(Color.BLACK);
            floor.setStrokeType(StrokeType.OUTSIDE);

            this.getChildren().add(floor);
        }
    }

    private void initElevators(Elevator[] elevatorPool) {
        double dividerX;

        for (Elevator elevator: elevatorPool) {
            dividerX = elevator.getPosX() + DisplayConfig.ELEVATOR_WIDTH;

            ElevatorController ec = new ElevatorController(elevator, new ElevatorBlock());
            Line divider = new Line(dividerX, 0, dividerX, DisplayConfig.SCREEN_HEIGHT);

            divider.setFill(Color.BLACK);
            elevatorControllers.add(ec);
            this.getChildren().addAll(ec.getDisplay(), divider);
        }
    }

    private void initPeople(Queue<Person> peoplePool) {
        for (Person person: peoplePool) {
            PersonController pc = new PersonController(person, new PersonBlock());
            peopleControllers.add(pc);
            this.getChildren().add(pc.getPersonBlock());
        }
    }

    public void startAnimation() {
        new AnimationTimer() {

            public void handle(long now) {
                for (ElevatorController ec : elevatorControllers) {
                    ec.move();
                }
                for (PersonController pc : peopleControllers) {
                    if (pc.getPerson().isSpawned()) {
                        pc.move();
                    }
                }
            }
        }.start();
    }
}
