package objects;

import static utilz.Constants.ObjectConstants.*;

import main.Game;

// GameContainer class represents game containers (e.g., boxes, barrels) in the game
public class GameContainer extends GameObject {

    // Constructor to initialize a game container object
    public GameContainer(int x, int y, int objType) {
        super(x, y, objType); // Call the constructor of the GameObject superclass
        createHitbox(); // Create the hitbox for the game container
    }

    // Method to create the hitbox for the game container based on its type
    private void createHitbox() {
        // Determine the object type and create hitbox accordingly
        if (objType == BOX) { // If it's a box
            // Initialize hitbox with specific dimensions
            initHitbox(25, 18);

            // Set draw offsets to position the container sprite correctly
            xDrawOffset = (int) (7 * Game.SCALE);
            yDrawOffset = (int) (12 * Game.SCALE);
        } else { // If it's not a box (e.g., barrel)
            // Initialize hitbox with different dimensions
            initHitbox(23, 25);
            // Set draw offsets for positioning
            xDrawOffset = (int) (8 * Game.SCALE);
            yDrawOffset = (int) (5 * Game.SCALE);
        }

        // Adjust hitbox position based on draw offsets and scale
        hitbox.y += yDrawOffset + (int) (Game.SCALE * 2);
        hitbox.x += xDrawOffset / 2;
    }

    // Method to update the game container
    public void update() {
        if (doAnimation) // Check if animation is enabled
            updateAnimationTick(); // Update animation tick
    }
}
