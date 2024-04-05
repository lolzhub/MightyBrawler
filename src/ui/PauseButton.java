package ui;

import java.awt.Rectangle;

public class PauseButton {

    // Position and size variables for the button
    protected int x, y, width, height;
    // Rectangle representing the bounds of the button
    protected Rectangle bounds;

    // Constructor
    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        // Create bounds for the button
        createBounds();
    }

    // Method to create bounds for the button
    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    // Getter for x-coordinate
    public int getX() {
        return x;
    }

    // Setter for x-coordinate
    public void setX(int x) {
        this.x = x;
    }

    // Getter for y-coordinate
    public int getY() {
        return y;
    }

    // Setter for y-coordinate
    public void setY(int y) {
        this.y = y;
    }

    // Getter for width
    public int getWidth() {
        return width;
    }

    // Setter for width
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter for height
    public int getHeight() {
        return height;
    }

    // Setter for height
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter for button bounds
    public Rectangle getBounds() {
        return bounds;
    }

    // Setter for button bounds
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
