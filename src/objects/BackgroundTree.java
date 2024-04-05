package objects;

import java.util.Random;

// BackgroundTree class represents trees in the background scenery
public class BackgroundTree {

    private int x, y; // Position of the tree
    private int type; // Type of the tree
    private int aniIndex; // Animation index for tree variation
    private int aniTick; // Animation tick counter

    // Constructor to initialize a background tree
    public BackgroundTree(int x, int y, int type) {
        this.x = x; // Set x-coordinate
        this.y = y; // Set y-coordinate
        this.type = type; // Set tree type

        // Set the animation index to a random value for variation
        Random r = new Random();
        aniIndex = r.nextInt(4); // Random value between 0 and 3
    }

    // Method to update the background tree
    public void update() {
        aniTick++; // Increment animation tick
        if (aniTick >= 35) { // Check if it's time to change animation frame
            aniTick = 0; // Reset animation tick
            aniIndex++; // Move to the next animation frame
            if (aniIndex >= 4) // Reset animation index if it exceeds the maximum frame count
                aniIndex = 0;
        }
    }

    // Getter method for getting the current animation index
    public int getAniIndex() {
        return aniIndex;
    }

    // Setter method for setting the animation index
    public void setAniIndex(int aniIndex) {
        this.aniIndex = aniIndex;
    }

    // Getter method for getting the x-coordinate of the tree
    public int getX() {
        return x;
    }

    // Setter method for setting the x-coordinate of the tree
    public void setX(int x) {
        this.x = x;
    }

    // Getter method for getting the y-coordinate of the tree
    public int getY() {
        return y;
    }

    // Setter method for setting the y-coordinate of the tree
    public void setY(int y) {
        this.y = y;
    }

    // Getter method for getting the type of the tree
    public int getType() {
        return type;
    }

    // Setter method for setting the type of the tree
    public void setType(int type) {
        this.type = type;
    }
}
