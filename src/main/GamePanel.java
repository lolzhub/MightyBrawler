package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

// Class representing the panel for the game
public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int xDelta=100, yDelta=100;
    // Constructor for the GamePanel class
    public GamePanel(){
        // Constructor body
        addKeyListener(new KeyboardInputs(this));
        mouseInputs = new MouseInputs(this); // Initialize the mouseInputs object
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    public void changeXDelta(int value){
        this.xDelta +=value;
        repaint();
    }
    public void changeYDelta(int value){
        this.yDelta +=value;
        repaint();
    }

    public void setRectPos(int x, int y){
        this.xDelta=x;
        this.yDelta=y;
        repaint();
    }

    // Method to paint components on the panel
    public void paintComponent(Graphics g){
        super.paintComponent(g); // Call the superclass method to ensure proper painting
        g.fillRect(xDelta, yDelta, 200, 50); // Draw a filled rectangle on the panel

    }
}
