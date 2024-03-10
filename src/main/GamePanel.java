package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

// Class representing the panel for the game
public class GamePanel extends JPanel {

    // Fields for mouse inputs, delta values, frame count, last check time, color, and random generator
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;

    // Constructor for the GamePanel class
    public GamePanel() {
        // Add keyboard inputs listener
        addKeyListener(new KeyboardInputs(this));
        // Initialize mouse inputs object and add listeners
        mouseInputs = new MouseInputs(this);
        importImg();
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        // Call setPanelSize to set the preferred size of the panel
        setPanelSize();
    }

    // Method to import the image
    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to set the preferred size of the panel
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800); // Set the size of the panel
        setMaximumSize(size); // Set maximum size
        setPreferredSize(size); // Set preferred size
        setMaximumSize(size); // Set maximum size
    }

    // Method to change the x delta value
    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    // Method to change the y delta value
    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    // Method to set the position of the rectangle
    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    // Method to paint components on the panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method to ensure proper painting
        subImg = img.getSubimage(1 * 64, 8 * 40, 64, 40); // Get a sub-image from the sprite sheet
        g.drawImage(subImg, (int) xDelta, (int) yDelta, 128, 80, null); // Draw the sub-image on the panel
    }
}
