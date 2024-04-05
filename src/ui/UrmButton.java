package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class UrmButton extends PauseButton {
    // Array to hold images for the button
    private BufferedImage[] imgs;
    // Index of the row in the sprite atlas
    private int rowIndex;
    // Index of the current image to be displayed
    private int index;
    // Flags to track mouse interaction states
    private boolean mouseOver, mousePressed;

    // Constructor
    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        // Call superclass constructor
        super(x, y, width, height);
        // Set the row index for the button images
        this.rowIndex = rowIndex;
        // Load button images
        loadImgs();
    }

    // Method to load button images from sprite atlas
    private void loadImgs() {
        // Load sprite atlas containing button images
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS);
        // Initialize array to hold button images
        imgs = new BufferedImage[3];
        // Extract individual button images from sprite atlas
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
    }

    // Method to update the state of the button
    public void update() {
        // Default to first image index
        index = 0;
        // Update image index based on mouse interaction states
        if (mouseOver)
            index = 1; // Mouse over image index
        if (mousePressed)
            index = 2; // Mouse pressed image index
    }

    // Method to draw the button
    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
    }

    // Method to reset mouse interaction flags
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    // Getter for mouseOver flag
    public boolean isMouseOver() {
        return mouseOver;
    }

    // Setter for mouseOver flag
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    // Getter for mousePressed flag
    public boolean isMousePressed() {
        return mousePressed;
    }

    // Setter for mousePressed flag
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
