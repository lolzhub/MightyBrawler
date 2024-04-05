package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.Gamestate;
import main.GamePanel;

// MouseInputs class implements MouseListener and MouseMotionListener interfaces to handle mouse input
public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel; // Reference to the GamePanel instance

    // Constructor for MouseInputs class
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel; // Initialize the reference to GamePanel
    }

    // Method called when the mouse is dragged
    @SuppressWarnings("incomplete-switch")
    @Override
    public void mouseDragged(MouseEvent e) {
        // Switch statement to determine the current game state
        switch (Gamestate.state) {
            // If the current state is PLAYING, pass the MouseEvent to the Playing class
            case PLAYING -> gamePanel.getGame().getPlaying().mouseDragged(e);
            // If the current state is OPTIONS, pass the MouseEvent to the GameOptions class
            case OPTIONS -> gamePanel.getGame().getGameOptions().mouseDragged(e);
        }
    }

    // Method called when the mouse is moved
    @SuppressWarnings("incomplete-switch")
    @Override
    public void mouseMoved(MouseEvent e) {
        // Switch statement to determine the current game state
        switch (Gamestate.state) {
            // If the current state is MENU, pass the MouseEvent to the Menu class
            case MENU -> gamePanel.getGame().getMenu().mouseMoved(e);
            // If the current state is PLAYING, pass the MouseEvent to the Playing class
            case PLAYING -> gamePanel.getGame().getPlaying().mouseMoved(e);
            // If the current state is OPTIONS, pass the MouseEvent to the GameOptions class
            case OPTIONS -> gamePanel.getGame().getGameOptions().mouseMoved(e);
        }
    }

    // Method called when the mouse is clicked
    @SuppressWarnings("incomplete-switch")
    @Override
    public void mouseClicked(MouseEvent e) {
        // Switch statement to determine the current game state
        switch (Gamestate.state) {
            // If the current state is PLAYING, pass the MouseEvent to the Playing class
            case PLAYING -> gamePanel.getGame().getPlaying().mouseClicked(e);
        }
    }

    // Method called when a mouse button is pressed
    @SuppressWarnings("incomplete-switch")
    @Override
    public void mousePressed(MouseEvent e) {
        // Switch statement to determine the current game state
        switch (Gamestate.state) {
            // If the current state is MENU, pass the MouseEvent to the Menu class
            case MENU -> gamePanel.getGame().getMenu().mousePressed(e);
            // If the current state is PLAYING, pass the MouseEvent to the Playing class
            case PLAYING -> gamePanel.getGame().getPlaying().mousePressed(e);
            // If the current state is OPTIONS, pass the MouseEvent to the GameOptions class
            case OPTIONS -> gamePanel.getGame().getGameOptions().mousePressed(e);
        }
    }

    // Method called when a mouse button is released
    @SuppressWarnings("incomplete-switch")
    @Override
    public void mouseReleased(MouseEvent e) {
        // Switch statement to determine the current game state
        switch (Gamestate.state) {
            // If the current state is MENU, pass the MouseEvent to the Menu class
            case MENU -> gamePanel.getGame().getMenu().mouseReleased(e);
            // If the current state is PLAYING, pass the MouseEvent to the Playing class
            case PLAYING -> gamePanel.getGame().getPlaying().mouseReleased(e);
            // If the current state is OPTIONS, pass the MouseEvent to the GameOptions class
            case OPTIONS -> gamePanel.getGame().getGameOptions().mouseReleased(e);
        }
    }

    // Method called when the mouse enters a component (not used in this implementation)
    @Override
    public void mouseEntered(MouseEvent e) {
        // Not In Use
    }

    // Method called when the mouse exits a component (not used in this implementation)
    @Override
    public void mouseExited(MouseEvent e) {
        // Not In Use
    }

}
