package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
    // Position variables for the button
    private int xPos, yPos;
    // Index of the button's row in the sprite atlas
    private int rowIndex;
    // Current index of the button image array
    private int index;
    // X offset to center the button
    private int xOffsetCenter = B_WIDTH / 2;
    // The game state associated with the button
    private Gamestate state;
    // Array to hold button images for different states
    private BufferedImage[] imgs;
    // Boolean flags to track mouse over and pressed states
    private boolean mouseOver, mousePressed;
    // Rectangle representing the bounds of the button
    private Rectangle bounds;

    // Constructor
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        // Load button images from sprite atlas
        loadImgs();
        // Initialize button bounds
        initBounds();
    }

    // Method to initialize button bounds
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    // Method to load button images from sprite atlas
    private void loadImgs() {
        imgs = new BufferedImage[3];
        // Load sprite atlas containing button images
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        // Extract individual button images from sprite atlas
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
    }

    // Method to draw the button
    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    // Method to update button state
    public void update() {
        index = 0; // Default image index

        // Update image index based on mouse interaction
        if (mouseOver)
            index = 1; // Mouse over image index
        if (mousePressed)
            index = 2; // Mouse pressed image index
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

    // Getter for button bounds
    public Rectangle getBounds() {
        return bounds;
    }

    // Apply the associated game state when the button is clicked
    public void applyGamestate() {
        Gamestate.state = state;
    }

    // Reset mouse interaction flags
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    // Getter for the associated game state
    public Gamestate getState() {
        return state;
    }

}
