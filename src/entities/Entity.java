package entities;

// Abstract class representing an entity in the game
public abstract class Entity {

    // Fields to hold the coordinates of the entity
    protected float x, y;

    // Constructor for the Entity class
    public Entity(float x, float y){
        this.x = x;
        this.y = y;
    }
}
