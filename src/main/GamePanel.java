package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

// Class representing the panel for the game
public class GamePanel extends JPanel {

    // Fields for mouse inputs, delta values, image, animations, animation tick, animation index, animation speed, player action, player direction, and movement flag
    private MouseInputs mouseInputs;
    private Game game;

    // Constructor for the GamePanel class
    public GamePanel(Game game) {
        // Initialize mouse inputs object and add listeners
        mouseInputs = new MouseInputs(this);
        this.game = game;

        // Set preferred size of the panel
        setPanelSize();
        // Add keyboard and mouse inputs listeners
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Method to set the preferred size of the panel
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    // Method to update game logic
    public void updateGame() {
        game.update();
    }

    // Method to paint components on the panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method to ensure proper painting
        game.render(g);
    }

    // Getter for the game object
    public Game getGame() {
        return game;
    }
}
