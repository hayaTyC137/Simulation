package GameMap.Path;

import Entity.DinamicEntity.Creature;
import Entity.Entity;
import GameMap.GameMap;
import GameMap.Coordinates;
import Entity.TypeOfEntity;

import java.util.*;

public class PathFinder {
    private final GameMap gameMap;

    public PathFinder(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public List<Coordinates> findPath(Coordinates start, TypeOfEntity targetType) {
        Queue<Coordinates> queue = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();

        queue.add(start);
        visited.add(start);

        Coordinates target = null;

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            Entity entity = gameMap.getEntityAt(current);

            if (entity != null && entity.getType() == targetType) {
                target = current;
                break;
            }

            for (Coordinates neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        if (target == null) return Collections.emptyList();

        List<Coordinates> path = new ArrayList<>();
        for (Coordinates at = target; at != null && !at.equals(start); at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }





    private boolean isTarget(Coordinates coordinates, Creature targetType) {
        if (targetType == null) {
            return false;  // Если цель не указана, сразу возвращаем false
        }

        Entity entity = gameMap.getEntityAt(coordinates);
        return entity != null && entity.getType() == targetType.getType();
    }
    private List<Coordinates> getNeighbors(Coordinates current){
        List<Coordinates> neighbors = new ArrayList<>();

        int coordinateX = current.getCoordinateX();
        int coordinateY = current.getCoordinateY();

        Coordinates[] directions = {
                new Coordinates(coordinateX - 1 ,coordinateY),
                new Coordinates(coordinateX + 1 ,coordinateY),
                new Coordinates(coordinateX ,coordinateY - 1 ),
                new Coordinates(coordinateX,coordinateY + 1),
        };

        for(Coordinates coordinates : directions){
            if(!gameMap.isOutOfBounds(coordinates)){
                Entity entity = gameMap.getEntityAt(coordinates);
                if(entity == null || entity.getType() == TypeOfEntity.GRASS || entity.getType() == TypeOfEntity.HERBIVORE) {
                    neighbors.add(coordinates);
                }
            }
        }
        return neighbors;
    }
}
