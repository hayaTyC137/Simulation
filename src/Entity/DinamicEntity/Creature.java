package Entity.DinamicEntity;

import Entity.Entity;
import Entity.StaticEntity.Grass;
import Entity.StaticEntity.Rock;
import Entity.StaticEntity.Tree;
import GameMap.Coordinates;
import java.util.Random;
import GameMap.GameMap;
import Entity.TypeOfEntity;

public abstract class Creature extends Entity {

    private final int HealthOfCreature = 10;
    private final int SpeedOfCreature = 20;

    public Creature(Coordinates coordinates, TypeOfEntity typeOfEntity) {
        super(coordinates, typeOfEntity);
    }


    public abstract void makeMove(GameMap map);

    public static TypeOfEntity getRandomTypeOfEntity() {
        TypeOfEntity[] values = TypeOfEntity.values();
        return values[new Random().nextInt(values.length)];
    }

    public static Entity spawnRandomEntity(Coordinates coordinates) {
        TypeOfEntity typeOfEntity = getRandomTypeOfEntity();
        return switch (typeOfEntity){
            case TypeOfEntity.HERBIVORE -> new Herbivore(coordinates, typeOfEntity);
            case TypeOfEntity.PREDATOR -> new Predator(coordinates, typeOfEntity);
            case TypeOfEntity.GRASS -> new Grass(coordinates, typeOfEntity);
            case TypeOfEntity.ROCK -> new Rock(coordinates, typeOfEntity);
            case TypeOfEntity.TREE -> new Tree(coordinates, typeOfEntity);
        };
    }
    public static Creature getTypeOfCreature(GameMap gameMap, Coordinates coordinates) {
        Entity entity = gameMap.getEntityAt(coordinates);
        if (entity instanceof Creature creature) {
            return creature;
        }
        return null; // Если в этой клетке нет существа
    }

}