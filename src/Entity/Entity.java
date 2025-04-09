package Entity;

import GameMap.Coordinates;

public abstract class Entity {
    public final TypeOfEntity typeOfEntity;
    public Coordinates coordinates;

    public TypeOfEntity getType() { return typeOfEntity; }

    public Entity(Coordinates coordinates, TypeOfEntity typeOfEntity) {
        this.typeOfEntity = typeOfEntity;
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }


}
