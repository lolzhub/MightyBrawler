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
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                gamePanel.setMoving(false); // Set moving flag to false
                break;
        }
    }

    // Method handling key pressed event
    @Override
    public void keyPressed(KeyEvent e) {
        // Switch statement to handle pressed keys
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.setDirection(UP); // Set direction to UP
                break;
            case KeyEvent.VK_A:
                gamePanel.setDirection(LEFT); // Set direction to LEFT
                break;
            case KeyEvent.VK_S:
                gamePanel.setDirection(DOWN); // Set direction to DOWN
                break;
            case KeyEvent.VK_D:
                gamePanel.setDirection(RIGHT); // Set direction to RIGHT
                break;
        }
    }
}
