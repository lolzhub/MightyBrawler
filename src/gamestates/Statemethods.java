// This interface defines methods that must be implemented by classes representing different game states

package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Statemethods {
    // Update method to update the state
    public void update();

    // Draw method to render the state
    public void draw(Graphics g);

    // Method called when the mouse is clicked
    public void mouseClicked(MouseEvent e);

    // Method called when a mouse button is pressed
    public void mousePressed(MouseEvent e);

    // Method called when a mouse button is released
    public void mouseReleased(MouseEvent e);

    // Method called when the mouse is moved
    public void mouseMoved(MouseEvent e);

    // Method called when a key is pressed
    public void keyPressed(KeyEvent e);

    // Method called when a key is released
    public void keyReleased(KeyEvent e);
}
