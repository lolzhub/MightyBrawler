package objects;

import java.awt.geom.Rectangle2D;

import main.Game;

import static utilz.Constants.Projectiles.*;

// Projectile class represents projectiles fired in the game
public class Projectile {
    private Rectangle2D.Float hitbox; // Hitbox of the projectile
    private int dir; // Direction of the projectile
    private boolean active = true; // State of the projectile

    // Constructor to initialize a projectile
    public Projectile(int x, int y, int dir) {
        int xOffset = (int) (-3 * Game.SCALE); // X offset for projectile spawn position
        int yOffset = (int) (5 * Game.SCALE); // Y offset for projectile spawn position

        // Adjust spawn position based on direction
        if (dir == 1)
            xOffset = (int) (29 * Game.SCALE);

        // Create hitbox for the projectile
        hitbox = new Rectangle2D.Float(x + xOffset, y + yOffset, CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT);
        this.dir = dir; // Set direction of the projectile
    }

    // Method to update the position of the projectile
    public void updatePos() {
        hitbox.x += dir * SPEED; // Update position based on direction and speed
    }

    // Method to set the position of the projectile
    public void setPos(int x, int y) {
        hitbox.x = x; // Set x-coordinate of the hitbox
        hitbox.y = y; // Set y-coordinate of the hitbox
    }

    // Getter method for retrieving the hitbox of the projectile
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    // Method to set the activity state of the projectile
    public void setActive(boolean active) {
        this.active = active;
    }

    // Method to check if the projectile is active
    public boolean isActive() {
        return active;
    }
}
