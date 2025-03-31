import java.util.Objects;
import java.util.Random;

public class Coordinates {
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
