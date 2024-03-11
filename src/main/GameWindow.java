package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

// Class representing the main window of the game
public class GameWindow {

    // Field to hold the JFrame object
    private JFrame jframe;

    // Constructor for the GameWindow class
    public GameWindow(GamePanel gamePanel) {
        // Initialize the JFrame
        jframe = new JFrame();

        // Set default close operation
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the game panel to the window
        jframe.add(gamePanel);

        // Center the window on the screen
        jframe.setLocationRelativeTo(null);

        // Disable window resizing
        jframe.setResizable(false);

        // Pack the components within the frame
        jframe.pack();

        // Make the window visible
        jframe.setVisible(true);

        // Add window focus listener to handle focus events
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Call windowFocusLost method of the game panel's associated game instance
                gamePanel.getGame().windowFocusLost();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                // Method not used
            }
        });
    }
}
