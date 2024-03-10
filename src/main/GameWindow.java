package main;

import javax.swing.*;

// Class representing the main window of the game
public class GameWindow {

    // Field to hold the JFrame object
    private JFrame jframe;

    // Constructor for the GameWindow class
    public GameWindow(GamePanel gamePanel) {
        // Initialize the JFrame
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        jframe.add(gamePanel); // Add the game panel to the window
        jframe.setLocationRelativeTo(null); // Center the window on the screen
        jframe.setResizable(false); // Disable window resizing
        jframe.pack(); // Pack the components within the frame
        jframe.setVisible(true); // Make the window visible
    }
}
