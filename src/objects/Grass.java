package objects;
public class Grass {

    private int x, y, type; // Position and type of grass

    // Constructor to initialize a grass object
    public Grass(int x, int y, int type) {
        this.x = x; // Set x-coordinate
        this.y = y; // Set y-coordinate
        this.type = type; // Set type of grass
    }

    // Getter method for retrieving the x-coordinate of the grass
    public int getX() {
        return x;
    }

    // Getter method for retrieving the y-coordinate of the grass
    public int getY() {
        return y;
    }

    // Getter method for retrieving the type of grass
    public int getType() {
        return type;
    }
}
