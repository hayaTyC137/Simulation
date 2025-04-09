package Action;

import Entity.DinamicEntity.Creature;
import Entity.Entity;
import Simulation.Simulation;

import java.util.ArrayList;
import java.util.List;

import GameMap.Coordinates;
import GameMap.GameMap;

public class PopulationMakeMove implements Action {

    @Override
    public void execute(Simulation simulation) {
        GameMap gameMap = simulation.getGameMap();
        int width = gameMap.getWidth();
        int length = gameMap.getLength();

        List<Creature> creatures = new ArrayList<>();

        for (int i = 0; i < width; i++) { // собираем информацию об всех существах
            for (int j = 0; j < length; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                Entity entity = gameMap.getEntityAt(coordinates);

                if (entity instanceof Creature creature) {
                    creatures.add((creature));
                }
            }
        }
        for (Creature creature : creatures) {
            creature.makeMove(gameMap);
        }
    }
}
