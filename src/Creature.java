import java.util.Random;

public abstract class Creature extends Entity {

    private final int HealthOfCreature = 10;
    private final int SpeedOfCreature = 20;

    public final TypeOfEntity typeOfEntity;

    public Creature(Coordinates coordinates, TypeOfEntity typeOfEntity) {
        super(coordinates);
        this.typeOfEntity = typeOfEntity;
    }

    public TypeOfEntity getType() { return typeOfEntity; }

    public abstract void makeMove(GameMap map);

    public static TypeOfEntity getRandomTypeOfEntity() {
        TypeOfEntity[] values = TypeOfEntity.values();
        return values[new Random().nextInt(values.length)];
    }

    public static Creature spawnRandomEntity(Coordinates coordinates) {
        TypeOfEntity typeOfEntity = getRandomTypeOfEntity();
        return switch (typeOfEntity){
            case HERBIVORE -> new Herbivore(coordinates, typeOfEntity);
            case PREDATOR -> new Predator(coordinates, typeOfEntity);
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
