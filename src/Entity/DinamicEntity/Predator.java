package Entity.DinamicEntity;

import Entity.Entity;
import GameMap.Coordinates;
import java.util.ArrayList;
import java.util.List;
import GameMap.Path.PathFinder;
import GameMap.GameMap;
import Entity.TypeOfEntity;


public class Predator extends Creature {

    private int HealthOfPredator = 100;
    private int DamageOfPredator = 25;

    public Predator(Coordinates coordinates, TypeOfEntity typeOfEntity) {
        super(coordinates, typeOfEntity);
    }

    @Override
    public void makeMove(GameMap map) {
        PathFinder pathFinder = new PathFinder(map);
        List<Coordinates> path = pathFinder.findPath(this.coordinates, TypeOfEntity.HERBIVORE);


        if (!path.isEmpty()) {
            Coordinates nextStep = path.get(0);
            Entity targetEntity = map.getEntityAt(nextStep);

            // Если на следующей клетке травоядное — атакуем
            if (targetEntity != null && targetEntity.getType() == TypeOfEntity.HERBIVORE) {
                // Атака — уменьшаем здоровье травоядного на 25
                Herbivore herbivore = (Herbivore) targetEntity;
                herbivore.reduceHealth(25);  // Уменьшаем здоровье травоядного на 25

                // Если травоядное умирает, убираем его с карты
                if (herbivore.getHealth() <= 0) {
                    map.removeEntity(nextStep); // убить травоядного
                }

                // Восстанавливаем здоровье хищника
                HealthOfPredator += 20;  // Восстановление здоровья хищника при убийстве травоядного
                if (HealthOfPredator > 100) {
                    HealthOfPredator = 100; // максимальное здоровье
                }
            }

            // Перемещаемся
            map.removeEntity(coordinates);
            coordinates.setCoordinates(nextStep.getCoordinateX(), nextStep.getCoordinateY());
            map.putEntity(this);

        } else {
            // fallback — случайное движение
            List<Coordinates> possibleMoves = getFreeCoordinates(map);
            if (!possibleMoves.isEmpty()) {
                Coordinates newCoordinates = possibleMoves.get((int)(Math.random() * possibleMoves.size()));
                map.removeEntity(coordinates);
                coordinates.setCoordinates(newCoordinates.getCoordinateX(), newCoordinates.getCoordinateY());
                map.putEntity(this);
            }
        }

    }




    @Override
    public TypeOfEntity getType() {
        return super.getType();
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
}
