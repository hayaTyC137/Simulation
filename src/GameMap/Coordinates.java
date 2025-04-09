package GameMap;

import java.util.Objects;
import java.util.Random;

public class Coordinates {
    static Random random = new Random();
    private int coordinateX;
    private int coordinateY;

    public Coordinates(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }
    public int getCoordinateX() {
        return coordinateX;
    }
    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinates(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }
    @Override
    public String toString() {
        return "(" + coordinateX + ", " + coordinateY + ")";
    }

    public static Coordinates getRandomCoordinate(Coordinates coordinates) {
        Random random = new Random();
        coordinates.setCoordinates(random.nextInt(coordinates.getCoordinateX()), random.nextInt(coordinates.getCoordinateY()));
        return coordinates;
    }
    public static Coordinates getRandomFreeCoordinate(GameMap map, int width, int height) {
        Coordinates coord;
        do {
            int x = random.nextInt(height);
            int y = random.nextInt(width);
            coord = new Coordinates(x, y);
        } while (map.getEntityAt(coord) != null); // пока не найдём свободную клетку
        return coord;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return coordinateX == that.coordinateX && coordinateY == that.coordinateY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY);
    }
}
