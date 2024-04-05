package objects;

import main.Game;

// Potion class represents potion objects in the game
public class Potion extends GameObject {

    private float hoverOffset; // Current offset for hover animation
    private int maxHoverOffset, hoverDir = 1; // Maximum hover offset and direction

    // Constructor to initialize a potion object
    public Potion(int x, int y, int objType) {
        super(x, y, objType); // Call the constructor of the GameObject superclass
        doAnimation = true; // Enable animation for potion

        initHitbox(7, 14); // Initialize hitbox for potion

        // Set draw offsets for positioning the potion sprite
        xDrawOffset = (int) (3 * Game.SCALE);
        yDrawOffset = (int) (2 * Game.SCALE);

        // Set maximum hover offset based on scale
        maxHoverOffset = (int) (10 * Game.SCALE);
    }

    // Method to update the potion object
    public void update() {
        updateAnimationTick(); // Update animation tick
        updateHover(); // Update hover animation
    }

    // Method to update the hover animation of the potion
    private void updateHover() {
        hoverOffset += (0.075f * Game.SCALE * hoverDir); // Increment hover offset

        // Check if reached maximum hover offset, change direction accordingly
        if (hoverOffset >= maxHoverOffset)
            hoverDir = -1; // Reverse direction
        else if (hoverOffset < 0)
            hoverDir = 1; // Restore original direction

        // Update hitbox position based on hover offset
        hitbox.y = y + hoverOffset;
    }
}
