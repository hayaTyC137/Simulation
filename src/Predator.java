public class Predator extends Creature{

    public Predator(Coordinates coordinates, TypeOfEntity typeOfEntity) {
        super(coordinates, typeOfEntity);
    }

    @Override
    public void makeMove(GameMap map) {
       map.removeEntity(this.coordinates);
       coordinates.setCoordinates(coordinates.getCoordinateX() + 1, coordinates.getCoordinateY());
       map.putEntity(this);
    }

    @Override
    public TypeOfEntity getType() {
        return super.getType();
    }
}
