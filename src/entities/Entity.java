package entities;

// Abstract class representing a game entity
public abstract class Entity {

    // Protected fields to represent the position and dimensions of the entity
    protected float x, y;
    protected int width, height;

    // Constructor to initialize the entity with a given position and dimensions
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

}
