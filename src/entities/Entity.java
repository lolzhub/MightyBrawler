package entities;

import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.UP;
import static utilz.HelpMethods.CanMoveHere;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public abstract class Entity {

    // Fields to store entity position, dimensions, hitbox, animation state, health, etc.
    protected float x, y; // Entity position
    protected int width, height; // Entity dimensions
    protected Rectangle2D.Float hitbox; // Hitbox for collision detection
    protected int aniTick, aniIndex; // Animation tick and index
    protected int state; // Current state of the entity
    protected float airSpeed; // Speed when entity is in the air
    protected boolean inAir = false; // Flag indicating whether entity is in the air
    protected int maxHealth; // Maximum health of the entity
    protected int currentHealth; // Current health of the entity
    protected Rectangle2D.Float attackBox; // Hitbox for attack detection
    protected float walkSpeed; // Speed of walking/movement

    // Fields for push-back effect
    protected int pushBackDir; // Direction of push-back
    protected float pushDrawOffset; // Offset for drawing push-back effect
    protected int pushBackOffsetDir = UP; // Direction of push-back offset

    // Constructor to initialize entity with position and dimensions
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Method to update push-back effect's draw offset
    protected void updatePushBackDrawOffset() {
        float speed = 0.95f;
        float limit = -30f;

        // Update push-back offset based on direction
        if (pushBackOffsetDir == UP) {
            pushDrawOffset -= speed;
            if (pushDrawOffset <= limit)
                pushBackOffsetDir = DOWN;
        } else {
            pushDrawOffset += speed;
            if (pushDrawOffset >= 0)
                pushDrawOffset = 0;
        }
    }

    // Method to apply push-back effect
    protected void pushBack(int pushBackDir, int[][] lvlData, float speedMulti) {
        float xSpeed = 0;
        if (pushBackDir == LEFT)
            xSpeed = -walkSpeed;
        else
            xSpeed = walkSpeed;

        // Move hitbox horizontally based on push-back direction and speed
        if (CanMoveHere(hitbox.x + xSpeed * speedMulti, hitbox.y, hitbox.width, hitbox.height, lvlData))
            hitbox.x += xSpeed * speedMulti;
    }

    // Method to draw the attack hitbox
    protected void drawAttackBox(Graphics g, int xLvlOffset) {
        g.setColor(Color.red);
        g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

    // Method to draw the entity's hitbox
    protected void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    // Method to initialize the hitbox
    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    // Getter for hitbox
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    // Getter for state
    public int getState() {
        return state;
    }

    // Getter for animation index
    public int getAniIndex() {
        return aniIndex;
    }

    // Method to set a new state for the entity
    protected void newState(int state) {
        this.state = state;
        aniTick = 0;
        aniIndex = 0;
    }
}
