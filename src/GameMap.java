import java.util.HashMap;

public class GameMap {
    HashMap<Coordinates, Entity> map = new HashMap<>();

    private final int lengthOfMap = 10;
    private final int widthOfMap = 10;


    public void putEntity(Creature creature) {
        map.put(creature.coordinates, creature);
    }

    public void RenderMap(){
        for (int i = 0; i < lengthOfMap; i++) {
            for (int j = 0; j < widthOfMap; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                Creature creature = (Creature) map.get(coordinates);

                if (creature != null) {
                    System.out.print(getSymbolOfEntity(creature)+"  ");
                }else {
                    System.out.print(" ... ");
                }
            }
            System.out.println();
        }
    }
    private String getSymbolOfEntity(Creature creature) {
        return switch (creature.getType()){
            case PREDATOR -> " \uD83D\uDC3A";
            case HERBIVORE -> " \uD83D\uDC11";
        };
    }
    public Entity getEntityAt(Coordinates coordinates) {
        return map.get(coordinates);
    }
    public void removeEntity(Coordinates coordinates) {
        map.remove(coordinates);
    }

}
