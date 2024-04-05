package objects;

import main.Game;

// Cannon class represents cannon objects in the game
public class Cannon extends GameObject {

    private int tileY; // Y-coordinate of the tile on which the cannon is placed

    // Constructor to initialize a cannon object
    public Cannon(int x, int y, int objType) {
        super(x, y, objType); // Call the constructor of the GameObject superclass
        tileY = y / Game.TILES_SIZE; // Calculate the tile Y-coordinate
        initHitbox(40, 26); // Initialize the hitbox of the cannon
        // Adjust hitbox position (commented out)
//		hitbox.x -= (int) (1 * Game.SCALE);
        hitbox.y += (int) (6 * Game.SCALE);
    }

    // Method to update the cannon object
    public void update() {
        if (doAnimation) // Check if animation is enabled
            updateAnimationTick(); // Update animation tick
    }

    // Getter method for retrieving the tile Y-coordinate
    public int getTileY() {
        return tileY;
    }
}
