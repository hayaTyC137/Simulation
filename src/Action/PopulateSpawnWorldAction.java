package Action;

import Entity.DinamicEntity.Herbivore;
import Entity.DinamicEntity.Predator;
import Entity.StaticEntity.Grass;
import Entity.StaticEntity.Rock;
import Entity.StaticEntity.Tree;
import Simulation.Simulation;
import GameMap.Coordinates;
import Entity.TypeOfEntity;
import GameMap.GameMap;

public class PopulateSpawnWorldAction implements Action {
    @Override
    public void execute(Simulation simulation) {
        GameMap gameMap = simulation.getGameMap();
        int width = gameMap.getWidth();
        int length = gameMap.getLength();

        for (int i = 0; i < 4; i++) { // создание 3 хищников
            Coordinates coordinates = Coordinates.getRandomFreeCoordinate(gameMap, width, length);
            gameMap.putEntity(new Predator(coordinates, TypeOfEntity.PREDATOR));
        }
        for (int i = 0; i < 5; i++) { // создание 5 травоядных
            Coordinates coordinates = Coordinates.getRandomFreeCoordinate(gameMap, width, length);
            gameMap.putEntity(new Herbivore(coordinates, TypeOfEntity.HERBIVORE));
        }
        for (int i = 0; i < 5; i++) { // создание 5 деревьев
            Coordinates coordinates = Coordinates.getRandomFreeCoordinate(gameMap, width, length);
            gameMap.putEntity(new Tree(coordinates, TypeOfEntity.TREE));
        }

        for (int i = 0; i < 5; i++) { // создание 5 кустов травы
            Coordinates coordinates = Coordinates.getRandomFreeCoordinate(gameMap, width, length);
            gameMap.putEntity(new Grass(coordinates, TypeOfEntity.GRASS));
        }
        for (int i = 0; i < 7; i++) {
            Coordinates coordinates = Coordinates.getRandomFreeCoordinate(gameMap, width, length);
            gameMap.putEntity(new Rock(coordinates, TypeOfEntity.ROCK));
        }



    }
}
