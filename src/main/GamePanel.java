package main;

import javax.swing.*;
import java.awt.*;

// Class representing the panel for the game
public class GamePanel extends JPanel {

    // Constructor for the GamePanel class
    public GamePanel(){
        // Constructor body
    }

    // Method to paint components on the panel
    public void paintComponent(Graphics g){
        super.paintComponent(g); // Call the superclass method to ensure proper painting
        g.fillRect(100, 100, 200, 50); // Draw a filled rectangle on the panel
    }
}
