package main;

// Class representing the game logic and setup
public class Game {

    // Fields to hold the game window and panel
    private GameWindow gameWindow;
    private GamePanel gamePanel;

    // Constructor for the Game class
    public Game(){
        // Initialize the game panel
        gamePanel = new GamePanel();
        // Initialize the game window with the game panel
        gameWindow = new GameWindow(gamePanel);
//        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
    }
}
