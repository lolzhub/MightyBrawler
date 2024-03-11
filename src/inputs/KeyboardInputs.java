package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

import static utilz.Constants.Directions.*;

// Class handling keyboard inputs for the game panel
public class KeyboardInputs implements KeyListener {

    // Field to hold the game panel
    private GamePanel gamePanel;

    // Constructor for the KeyboardInputs class
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // Method handling key typed event
    @Override
    public void keyTyped(KeyEvent e) {
        // Not implemented
    }

    // Method handling key released event
    @Override
    public void keyReleased(KeyEvent e) {
        // Switch statement to handle released keys
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setUp(false); // Set moving flag to false
            case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setLeft(false); // Set moving flag to false
            case KeyEvent.VK_S -> gamePanel.getGame().getPlayer().setDown(false); // Set moving flag to false
            case KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(false); // Set moving flag to false
        }
    }

    // Method handling key pressed event
    @Override
    public void keyPressed(KeyEvent e) {
        // Switch statement to handle pressed keys
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setUp(true); // Set moving flag to true
            case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setLeft(true); // Set moving flag to true
            case KeyEvent.VK_S -> gamePanel.getGame().getPlayer().setDown(true); // Set moving flag to true
            case KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(true); // Set moving flag to true
            case KeyEvent.VK_J -> gamePanel.getGame().getPlayer().setAttacking(true); // Set moving flag to true
        }
    }
}
