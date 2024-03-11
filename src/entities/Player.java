// Package entities contains classes related to game entities.
package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;

// Represents the player entity in the game
public class Player extends Entity {
    private BufferedImage[][] animations; // 2D array to hold animation frames
    private int aniTick, aniIndex, aniSpeed = 15; // Animation tick, index, and speed variables
    private int playerAction = IDLE; // Current player action
    private boolean moving = false, attacking = false; // Flags for movement and attacking
    private boolean left, up, right, down; // Directional movement flags
    private float playerSpeed = 2.0f; // Speed of the player

    // Constructor to initialize the Player object
    public Player(float x, float y) {
        super(x, y);
        loadAnimations(); // Load animations from sprite sheet
    }

    // Updates the player's state
    public void update() {
        updatePos(); // Update player position
        updateAnimationTick(); // Update animation tick
        setAnimation(); // Set animation based on player action
    }

    // Renders the player on the screen
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 256, 160, null); // Draw the current frame of the animation
    }

    // Updates the animation tick
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    // Sets the current animation based on player action
    private void setAnimation() {
        int startAni = playerAction;
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
        if (attacking) {
            playerAction = ATTACK_1;
        }
        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    // Resets the animation tick
    public void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    // Updates the player's position
    private void updatePos() {
        moving = false;
        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }
        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

    // Resets directional movement flags
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    // Sets the attacking flag
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    // Checks if the left direction flag is set
    public boolean isLeft() {
        return left;
    }

    // Sets the left direction flag
    public void setLeft(boolean left) {
        this.left = left;
    }

    // Checks if the up direction flag is set
    public boolean isUp() {
        return up;
    }

    // Sets the up direction flag
    public void setUp(boolean up) {
        this.up = up;
    }

    // Checks if the right direction flag is set
    public boolean isRight() {
        return right;
    }

    // Sets the right direction flag
    public void setRight(boolean right) {
        this.right = right;
    }

    // Checks if the down direction flag is set
    public boolean isDown() {
        return down;
    }

    // Sets the down direction flag
    public void setDown(boolean down) {
        this.down = down;
    }

    // Loads animations from the sprite sheet
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[9][6];
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
