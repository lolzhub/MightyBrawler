package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

// Class representing the panel for the game
public class GamePanel extends JPanel {

    // Fields for mouse inputs, delta values, frame count, last check time, color, and random generator
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private int frames = 0;
    private long lastCheck = 0;
    private Color color = new Color(150, 20, 90);
    private Random random;

    // Constructor for the GamePanel class
    public GamePanel(){
        // Initialize the random generator
        random = new Random();
        // Add keyboard inputs listener
        addKeyListener(new KeyboardInputs(this));
        // Initialize mouse inputs object and add listeners
        mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Method to change the x delta value
    public void changeXDelta(int value){
        this.xDelta += value;
    }

    // Method to change the y delta value
    public void changeYDelta(int value){
        this.yDelta += value;
    }

    // Method to set the position of the rectangle
    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }

    // Method to paint components on the panel
    public void paintComponent(Graphics g){
        super.paintComponent(g); // Call the superclass method to ensure proper painting
        updateRectangle(); // Update the position of the rectangle
        g.setColor(color); // Set the color of the rectangle
        g.fillRect((int) xDelta, (int) yDelta, 200, 50); // Draw a filled rectangle on the panel
    }

    // Method to update the position of the rectangle
    private void updateRectangle(){
        // Update x position
        xDelta += xDir;
        // Check for boundary collision on x-axis
        if (xDelta > 400 || xDelta < 0){
            xDir *= -1; // Reverse direction
            color = getRndColor(); // Change color
        }

        // Update y position
        yDelta += yDir;
        // Check for boundary collision on y-axis
        if (yDelta > 400 || yDelta < 0){
            yDir *= -1; // Reverse direction
            color = getRndColor(); // Change color
        }
    }

    // Method to generate a random color
    private Color getRndColor(){
        int r = random.nextInt(256); // Generate random value for red component (0-255)
        int g = random.nextInt(256); // Generate random value for green component (0-255)
        int b = random.nextInt(256); // Generate random value for blue component (0-255)
        return new Color(r, g, b); // Return the generated color
    }
}
