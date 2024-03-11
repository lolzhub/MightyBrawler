package main;

import entities.Player;

import java.awt.*;

// Class representing the game logic and setup
public class Game implements Runnable {

    // Fields to hold the game window, panel, thread, frames per second, and updates per second
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;

    // Constructor for the Game class
    public Game() {
        initClasses();
        // Initialize the game panel and window
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        // Request focus for the game panel
        gamePanel.requestFocus();
        // Start the game loop
        startGameLoop();
    }

    // Method to initialize game-related classes
    private void initClasses() {
        player = new Player(200, 200);
    }

    // Method to start the game loop
    private void startGameLoop() {
        // Create a new thread for the game loop
        gameThread = new Thread(this);
        // Start the thread
        gameThread.start();
    }

    // Method to update game logic
    public void update() {
        player.update();
    }

    // Method to render game graphics
    public void render(Graphics g) {
        player.render(g);
    }

    // Run method for the game loop
    @Override
    public void run() {
        // Calculate the time per frame and per update in nanoseconds
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        // Record the previous time
        long previousTime = System.nanoTime();
        // Variables for tracking frames and updates
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        // Main game loop
        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update(); // Update game logic
                updates++; // Increment update count
                deltaU--; // Decrement update delta
            }

            if (deltaF >= 1) {
                gamePanel.repaint(); // Repaint game panel
                frames++; // Increment frame count
                deltaF--; // Decrement frame delta
            }

            // Check if it's time to update frames and updates per second count
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis(); // Update last check time
                System.out.println("FPS: " + frames + " | UPS: " + updates); // Print FPS and UPS
                frames = 0; // Reset frame count
                updates = 0; // Reset update count
            }
        }
    }

    // Method called when window loses focus
    public void windowFocusLost(){
        player.resetDirBooleans();
    }

    // Getter for the player object
    public Player getPlayer() {
        return player;
    }
}
