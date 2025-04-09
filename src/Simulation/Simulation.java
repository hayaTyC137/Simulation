package Simulation;

import Action.Action;
import Entity.TypeOfEntity;
import GameMap.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import GameMap.Coordinates;
import Action.SpawnMoreGrassAction;

public class Simulation {
    private final GameMap gameMap;
    private int turnCount = 0;
    private boolean running = false;

    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();

    public Simulation(int length, int width) {
        this.gameMap = new GameMap(length, width);
    }

    public void addInitAction(Action action) {
        initActions.add(action);
    }

    public void addTurnAction(Action action) {
        turnActions.add(action);
    }

    public void startSimulation() {
        running = true;
        for (Action action : initActions) {
            action.execute(this);
        }

        while (running) {
            nextTurn();
            try {
                Thread.sleep(1000); // Пауза между ходами
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void pauseSimulation() {
        running = false;
    }

    public void nextTurn() {
        turnCount++;
        if(getCountOfGrass() < 2) {
            Action spawnMoreGrassAction = new SpawnMoreGrassAction();
            spawnMoreGrassAction.execute(this);
        }
        else if (getCountOfHerbivores() < 2){
            Action spawnMoreHerivoresAction = new SpawnMoreGrassAction();
            spawnMoreHerivoresAction.execute(this);
        }
        for (Action action : turnActions) {
            action.execute(this);
        }
        gameMap.render();
        System.out.println();
    }

    public int getCountOfGrass() {
        int count = 0;
        for (int i = 0; i < gameMap.getWidth(); i++) {
            for (int j = 0; j < gameMap.getLength(); j++) {
                Coordinates currentCoordinates = new Coordinates(i, j);
                if (gameMap.getEntityAt(currentCoordinates) != null && gameMap.getEntityAt(currentCoordinates).getType() == TypeOfEntity.GRASS) {
                    count++;
                }
            }
        }
        return count;
    }
    public int getCountOfHerbivores() {
        int countOfHerbivores = 0;
        for (int i = 0; i < gameMap.getWidth(); i++) {
            for (int j = 0; j < gameMap.getLength(); j++) {
                Coordinates currentCoordinates = new Coordinates(i, j);
                if (gameMap.getEntityAt(currentCoordinates) != null && gameMap.getEntityAt(currentCoordinates).getType() == TypeOfEntity.HERBIVORE) {
                    countOfHerbivores++;
                }
            }
        }
        return countOfHerbivores;
    }

    // Методы-прокси
    public GameMap getGameMap() {
        return gameMap;
    }

    public Coordinates getCoordinates(){
        return Coordinates.getRandomFreeCoordinate(gameMap, gameMap.getWidth(), gameMap.getLength());
    }

}
