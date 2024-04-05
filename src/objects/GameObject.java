package objects;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

// GameObject class represents generic game objects
public class GameObject {

    protected int x, y; // Position of the game object
    protected int objType; // Type of the game object
    protected Rectangle2D.Float hitbox; // Hitbox of the game object
    protected boolean doAnimation, active; // Flags for animation and activity status
    protected int aniTick, aniIndex; // Animation tick and index for sprite animation
    protected int xDrawOffset, yDrawOffset; // Offsets for drawing the sprite

    // Constructor to initialize a game object
    public GameObject(int x, int y, int objType) {
        this.x = x; // Set x-coordinate
        this.y = y; // Set y-coordinate
        this.objType = objType; // Set object type
        active = true; // Set the object as active by default
    }

    // Method to update the animation tick
    protected void updateAnimationTick() {
        aniTick++; // Increment animation tick
        // Check if it's time to advance to the next animation frame
        if (aniTick >= ANI_SPEED) {
            aniTick = 0; // Reset animation tick
            aniIndex++; // Move to the next animation frame
            // Check if reached the end of animation frames
            if (aniIndex >= GetSpriteAmount(objType)) {
                aniIndex = 0; // Reset animation index
                // If it's a barrel, box, or cannon, deactivate animation
                if (objType == BARREL || objType == BOX || objType == CANNON_LEFT || objType == CANNON_RIGHT) {
                    doAnimation = false;
                    active = false; // Deactivate the object
                }
                // If it's a cannon, deactivate animation
                else if (objType == CANNON_LEFT || objType == CANNON_RIGHT)
                    doAnimation = false;
            }
        }
    }

    // Method to reset the game object
    public void reset() {
        aniIndex = 0; // Reset animation index
        aniTick = 0; // Reset animation tick
        active = true; // Set the object as active
        // If it's a barrel, box, or cannon, deactivate animation
        if (objType == BARREL || objType == BOX || objType == CANNON_LEFT || objType == CANNON_RIGHT)
            doAnimation = false;
        else
            doAnimation = true; // Otherwise, activate animation
    }

    // Method to initialize the hitbox of the game object
    protected void initHitbox(int width, int height) {
        // Create hitbox with scaled dimensions
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    // Method to draw the hitbox of the game object (for debugging)
    public void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK); // Set color to pink
        // Draw hitbox with respect to level offset
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    // Getter method for retrieving the object type
    public int getObjType() {
        return objType;
    }

    // Getter method for retrieving the hitbox of the game object
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    // Getter method for checking if the object is active
    public boolean isActive() {
        return active;
    }

    // Setter method for setting the activity status of the object
    public void setActive(boolean active) {
        this.active = active;
    }

    // Setter method for setting the animation status of the object
    public void setAnimation(boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    // Getter method for retrieving the x-draw offset
    public int getxDrawOffset() {
        return xDrawOffset;
    }

    // Getter method for retrieving the y-draw offset
    public int getyDrawOffset() {
        return yDrawOffset;
    }

    // Getter method for retrieving the current animation index
    public int getAniIndex() {
        return aniIndex;
    }

    // Getter method for retrieving the current animation tick
    public int getAniTick() {
        return aniTick;
    }

}
