package Entity.DinamicEntity;

import Entity.Entity;
import GameMap.Coordinates;
import java.util.ArrayList;
import java.util.List;
import GameMap.Path.PathFinder;
import GameMap.GameMap;
import Entity.TypeOfEntity;

public class Herbivore extends Creature {

    private int HealthOfHerbivore = 50;

    public Herbivore(Coordinates coordinates, TypeOfEntity typeOfEntity) {
        super(coordinates, typeOfEntity);
    }

    @Override
    public void makeMove(GameMap map) {
        PathFinder pathFinder = new PathFinder(map);
        List<Coordinates> path = pathFinder.findPath(this.coordinates, TypeOfEntity.GRASS);

        if (!path.isEmpty()) {
            // Двигаемся на первый шаг пути
            Coordinates nextStep = path.get(0);
            map.removeEntity(coordinates);
            coordinates.setCoordinates(nextStep.getCoordinateX(), nextStep.getCoordinateY());
            map.putEntity(this);

            // Проверка — есть ли на новой клетке трава
            Entity entity = map.getEntityAt(nextStep);
            if (entity != null && entity.getType() == TypeOfEntity.GRASS) {
                map.removeEntity(nextStep); // съесть траву
                HealthOfHerbivore += 10;   // увеличить здоровье
            }
        } else {
            // fallback: случайное перемещение, как раньше
            List<Coordinates> possibleMoves = getFreeCoordinates(map);
            if (!possibleMoves.isEmpty()) {
                Coordinates newCoordinates = possibleMoves.get((int)(Math.random() * possibleMoves.size()));
                map.removeEntity(coordinates);
                coordinates.setCoordinates(newCoordinates.getCoordinateX(), newCoordinates.getCoordinateY());
                map.putEntity(this);
            }
        }

    }


    public List<Coordinates> getFreeCoordinates(GameMap map){
        List<Coordinates> directions = List.of(
                new Coordinates(coordinates.getCoordinateX() - 1, coordinates.getCoordinateY()),
                new Coordinates(coordinates.getCoordinateX() + 1, coordinates.getCoordinateY()),
                new Coordinates(coordinates.getCoordinateX(), coordinates.getCoordinateY() - 1),
                new Coordinates(coordinates.getCoordinateX(), coordinates.getCoordinateY() + 1)

        );
        List<Coordinates> result = new ArrayList<>();
        for (Coordinates direction : directions) {
            if (!map.isOutOfBounds(direction) && map.getEntityAt(direction) == null)
            {
                result.add(direction);
            }
        }
        return result;
    }
    public void reduceHealth(int damage) {
        this.HealthOfHerbivore -= damage;
    }
    public int getHealth() {
        return this.HealthOfHerbivore;
    }
}
