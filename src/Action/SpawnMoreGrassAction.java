package Action;

import Entity.StaticEntity.Grass;
import Entity.TypeOfEntity;
import GameMap.GameMap;
import Simulation.Simulation;
import GameMap.Coordinates;

public class SpawnMoreGrassAction implements Action {
    @Override
    public void execute(Simulation simulation) {
        GameMap gameMap = simulation.getGameMap();
        int width = gameMap.getWidth();
        int length = gameMap.getLength();

        for (int i = 0; i < 5; i++) { // создание 5 кустов травы
            Coordinates coordinates = Coordinates.getRandomFreeCoordinate(gameMap, width, length);
            gameMap.putEntity(new Grass(coordinates, TypeOfEntity.GRASS));
        }
    }
}
