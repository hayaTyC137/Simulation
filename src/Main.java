public class Main {
    public static void main(String[] args) {
        Coordinates coordinates = new Coordinates(5, 10);
        GameMap map = new GameMap();
        map.putEntity(Creature.spawnRandomEntity(Coordinates.getRandomCoordinate(coordinates)));
        map.RenderMap();
        Creature.getTypeOfCreature(map,coordinates).makeMove(map);
        System.out.println();
        map.RenderMap();
    }
}
