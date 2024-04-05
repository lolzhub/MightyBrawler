package effects;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.Dialogue.*;

public class DialogueEffect {

    // Fields to store position, type, animation index, animation tick, and activity status
    private int x, y, type;
    private int aniIndex, aniTick;
    private boolean active = true;

    // Constructor to initialize position and type of the effect
    public DialogueEffect(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // Method to update the animation frame
    public void update() {
        aniTick++; // Increment animation tick
        if (aniTick >= ANI_SPEED) { // Check if it's time to update animation frame based on animation speed
            aniTick = 0; // Reset animation tick
            aniIndex++; // Increment animation index
            if (aniIndex >= GetSpriteAmount(type)) { // Check if animation index exceeds the total number of sprites for the type
                active = false; // Deactivate the effect if animation reaches the end
                aniIndex = 0; // Reset animation index for potential reuse
            }
        }
    }

    // Method to deactivate the effect
    public void deactive() {
        active = false; // Set active status to false
    }

    // Method to reset the effect with new position
    public void reset(int x, int y) {
        this.x = x; // Set new x coordinate
        this.y = y; // Set new y coordinate
        active = true; // Set active status to true
    }

    // Getter methods to retrieve animation index, x coordinate, y coordinate, and type
    public int getAniIndex() {
        return aniIndex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    // Method to check if the effect is active
    public boolean isActive() {
        return active;
    }
}
