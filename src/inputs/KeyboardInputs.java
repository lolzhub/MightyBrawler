package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

// KeyboardInputs class implements the KeyListener interface to handle keyboard input
public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel; // Reference to the GamePanel instance

    // Constructor for KeyboardInputs class
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel; // Initialize the reference to GamePanel
    }

    // Method called when a key is released
    @SuppressWarnings("incomplete-switch")
    @Override
    public void keyReleased(KeyEvent e) {
        // Switch statement to determine the current game state
        switch (Gamestate.state) {
            // If the current state is MENU, pass the KeyEvent to the Menu class
            case MENU -> gamePanel.getGame().getMenu().keyReleased(e);
            // If the current state is PLAYING, pass the KeyEvent to the Playing class
            case PLAYING -> gamePanel.getGame().getPlaying().keyReleased(e);
            // If the current state is CREDITS, pass the KeyEvent to the Credits class
            case CREDITS -> gamePanel.getGame().getCredits().keyReleased(e);
        }
    }

    // Method called when a key is pressed
    @SuppressWarnings("incomplete-switch")
    @Override
    public void keyPressed(KeyEvent e) {
        // Switch statement to determine the current game state
        switch (Gamestate.state) {
            // If the current state is MENU, pass the KeyEvent to the Menu class
            case MENU -> gamePanel.getGame().getMenu().keyPressed(e);
            // If the current state is PLAYING, pass the KeyEvent to the Playing class
            case PLAYING -> gamePanel.getGame().getPlaying().keyPressed(e);
            // If the current state is OPTIONS, pass the KeyEvent to the GameOptions class
            case OPTIONS -> gamePanel.getGame().getGameOptions().keyPressed(e);
        }
    }

    // Method called when a key is typed (not used in this implementation)
    @Override
    public void keyTyped(KeyEvent e) {
        // Not In Use
    }
}
