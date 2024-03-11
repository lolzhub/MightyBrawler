package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

// Class representing the main panel of the game
public class GamePanel extends JPanel {

    // Fields declaration
    private MouseInputs mouseInputs; // Instance for handling mouse inputs
    private Game game; // Reference to the main game instance

    // Constructor
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this); // Initialize mouse inputs handler
        this.game = game; // Set the reference to the main game instance
        setPanelSize(); // Set panel size
        addKeyListener(new KeyboardInputs(this)); // Add keyboard input listener
        addMouseListener(mouseInputs); // Add mouse click listener
        addMouseMotionListener(mouseInputs); // Add mouse motion listener
    }

    // Method to set the panel size
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT); // Create dimension with game width and height
        setPreferredSize(size); // Set preferred panel size
    }

    // Method to update the game state
    public void updateGame() {
        // Method body can be implemented to update the game state if necessary
    }

    // Method to paint component (override from JPanel)
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass's paintComponent method
        game.render(g); // Render the game graphics
    }

    // Method to get the main game instance
    public Game getGame() {
        return game; // Return the main game instance
    }
}
