package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

// Class handling mouse inputs for the game panel
public class MouseInputs implements MouseListener, MouseMotionListener {

    // Field to hold the game panel
    private GamePanel gamePanel;

    // Constructor for the MouseInputs class
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // Method handling mouse dragged event
    @Override
    public void mouseDragged(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse moved event
    @Override
    public void mouseMoved(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse clicked event
    @Override
    public void mouseClicked(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse pressed event
    @Override
    public void mousePressed(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse released event
    @Override
    public void mouseReleased(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse entered event
    @Override
    public void mouseEntered(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse exited event
    @Override
    public void mouseExited(MouseEvent e) {
        // Not implemented
    }
}
