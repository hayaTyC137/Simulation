package GameMap;

import Entity.Entity;

import java.util.HashMap;
import java.util.Scanner;

import Entity.Entity;

public class GameMap {
    private final HashMap<Coordinates, Entity> map = new HashMap<>();
    private final int lengthOfMap;
    private final int widthOfMap;

    public GameMap(int lengthOfMap, int widthOfMap) {
        this.lengthOfMap = lengthOfMap;
        this.widthOfMap = widthOfMap;
    }

    public void putEntity(Entity entity) {
        map.put(entity.getCoordinates(), entity);
    }

    public Entity getEntityAt(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        map.remove(coordinates);
    }

    public int getLength() {
        return lengthOfMap;
    }

    public int getWidth() {
        return widthOfMap;
    }

    public void render() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < lengthOfMap; i++) {
            for (int j = 0; j < widthOfMap; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                Entity entity = map.get(coordinates);

                if(entity != null){
                    System.out.print(getSymbolOfEntity(entity) + "  ");
                }else {
                    System.out.print(" ... ");
                }
            }
            System.out.println();
        }
        System.out.println("Остановить симуляцию - 1");
    }


    private String getSymbolOfEntity(Entity entity) {
        return switch (entity.getType()) {
            case PREDATOR -> " 🐺";
            case HERBIVORE -> " 🐑";
            case TREE -> " 🌴";
            case ROCK -> " 🪨";
            case GRASS -> " 🌱";
        };
    }


    public boolean isOutOfBounds(Coordinates coordinates) {
        if (coordinates.getCoordinateX() < 0 || coordinates.getCoordinateX() >= lengthOfMap) {
            return true;
        }
        return coordinates.getCoordinateY() < 0 || coordinates.getCoordinateY() >= widthOfMap;
    }
}
