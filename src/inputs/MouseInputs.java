package inputs;

import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// Class handling mouse inputs for the game panel
public class MouseInputs implements MouseListener, MouseMotionListener {

    // Field to hold the game panel
    private GamePanel gamePanel;

    // Constructor for the MouseInputs class
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // Method handling mouse click event
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked!");
    }

    // Method handling mouse press event
    @Override
    public void mousePressed(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse release event
    @Override
    public void mouseReleased(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse enter event
    @Override
    public void mouseEntered(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse exit event
    @Override
    public void mouseExited(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse drag event
    @Override
    public void mouseDragged(MouseEvent e) {
        // Not implemented
    }

    // Method handling mouse move event
    @Override
    public void mouseMoved(MouseEvent e) {
        // Set the position of the rectangle in the game panel based on mouse coordinates
        gamePanel.setRectPos(e.getX(), e.getY());
    }
}
