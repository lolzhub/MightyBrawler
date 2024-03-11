package entities;

import static utilz.Constants.PlayerConstants.*; // Importing static constants from PlayerConstants class.

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import utilz.LoadSave;

public class Player extends Entity { // Declaring a class named Player which extends Entity.

    // Member variables declaration.
    private BufferedImage[][] animations; // A 2D array to hold animation frames.
    private int aniTick, aniIndex, aniSpeed = 25; // Variables for animation control.
    private int playerAction = IDLE; // Variable to represent the current action of the player.
    private boolean moving = false, attacking = false; // Flags to indicate player movement and attack.
    private boolean left, up, right, down; // Flags to indicate player direction.
    private float playerSpeed = 2.0f; // Variable to represent player movement speed.

    // Constructor to initialize Player object with position and dimensions.
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height); // Calling superclass constructor to initialize position and dimensions.
        loadAnimations(); // Loading player animations.
    }

    // Method to update player state.
    public void update() {
        updatePos(); // Update player position.
        updateAnimationTick(); // Update animation frame.
        setAnimation(); // Set appropriate animation based on player action.
    }

    // Method to render player graphics.
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width, height, null); // Rendering player sprite.
    }

    // Method to update animation tick.
    private void updateAnimationTick() {
        aniTick++; // Increment animation tick.
        if (aniTick >= aniSpeed) { // Check if it's time to change animation frame.
            aniTick = 0; // Reset animation tick.
            aniIndex++; // Move to the next frame.
            if (aniIndex >= GetSpriteAmount(playerAction)) { // Check if reached end of animation sequence.
                aniIndex = 0; // Reset animation frame index.
                attacking = false; // If attacking, set attacking flag to false.
            }
        }
    }

    // Method to set player animation based on current state.
    private void setAnimation() {
        int startAni = playerAction; // Store current player action.

        // Determine player action based on movement and attacking flags.
        if (moving)
            playerAction = RUNNING; // If player is moving, set animation to running.
        else
            playerAction = IDLE; // If player is not moving, set animation to idle.

        if (attacking)
            playerAction = ATTACK_1; // If player is attacking, set animation to attack.

        if (startAni != playerAction) // Check if animation changed.
            resetAniTick(); // If animation changed, reset animation tick.
    }

    // Method to reset animation tick and index.
    private void resetAniTick() {
        aniTick = 0; // Reset animation tick.
        aniIndex = 0; // Reset animation index.
    }

    // Method to update player position based on direction flags.
    private void updatePos() {
        moving = false; // Reset moving flag.

        // Update player position based on direction flags.
        if (left && !right) {
            x -= playerSpeed; // Move player left.
            moving = true; // Set moving flag.
        } else if (right && !left) {
            x += playerSpeed; // Move player right.
            moving = true; // Set moving flag.
        }

        if (up && !down) {
            y -= playerSpeed; // Move player up.
            moving = true; // Set moving flag.
        } else if (down && !up) {
            y += playerSpeed; // Move player down.
            moving = true; // Set moving flag.
        }
    }

    // Method to load player animations from sprite atlas.
    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS); // Load sprite atlas for player.

        animations = new BufferedImage[9][6]; // Initialize animation array.

        // Populate animation array with sub-images from sprite atlas.
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40); // Extract sub-image for animation frame.
    }

    // Method to reset direction flags.
    public void resetDirBooleans() {
        left = false; // Reset left flag.
        right = false; // Reset right flag.
        up = false; // Reset up flag.
        down = false; // Reset down flag.
    }

    // Setter method to set attacking flag.
    public void setAttacking(boolean attacking) {
        this.attacking = attacking; // Set attacking flag.
    }

    // Getter method to check left direction flag.
    public boolean isLeft() {
        return left; // Return left flag.
    }

    // Setter method to set left direction flag.
    public void setLeft(boolean left) {
        this.left = left; // Set left flag.
    }

    // Getter method to check up direction flag.
    public boolean isUp() {
        return up; // Return up flag.
    }

    // Setter method to set up direction flag.
    public void setUp(boolean up) {
        this.up = up; // Set up flag.
    }

    // Getter method to check right direction flag.
    public boolean isRight() {
        return right; // Return right flag.
    }

    // Setter method to set right direction flag.
    public void setRight(boolean right) {
        this.right = right; // Set right flag.
    }

    // Getter method to check down direction flag.
    public boolean isDown() {
        return down; // Return down flag.
    }

    // Setter method to set down direction flag.
    public void setDown(boolean down) {
        this.down = down; // Set down flag.
    }
}
