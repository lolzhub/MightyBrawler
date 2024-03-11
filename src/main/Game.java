package main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {

    // Fields declaration
    private GameWindow gameWindow; // Instance of the game window
    private GamePanel gamePanel; // Instance of the game panel
    private Thread gameThread; // Thread for running the game loop
    private final int FPS_SET = 120; // Target frames per second
    private final int UPS_SET = 200; // Target updates per second
    private Player player; // Instance of the player entity
    private LevelManager levelManager; // Instance of the level manager

    // Constants declaration
    public final static int TILES_DEFAULT_SIZE = 32; // Default size of tiles
    public final static float SCALE = 2f; // Scaling factor for the game
    public final static int TILES_IN_WIDTH = 26; // Number of tiles in width
    public final static int TILES_IN_HEIGHT = 14; // Number of tiles in height
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE); // Scaled size of tiles
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH; // Width of the game window
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT; // Height of the game window

    // Constructor
    public Game() {
        initClasses(); // Initialize necessary classes

        gamePanel = new GamePanel(this); // Create a game panel instance
        gameWindow = new GameWindow(gamePanel); // Create a game window instance and associate it with the game panel
        gamePanel.requestFocus(); // Request focus for the game panel

        startGameLoop(); // Start the game loop
    }

    // Method to initialize necessary classes
    private void initClasses() {
        player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE)); // Initialize player entity
        levelManager = new LevelManager(this); // Initialize level manager
    }

    // Method to start the game loop
    private void startGameLoop() {
        gameThread = new Thread(this); // Create a new thread for the game loop
        gameThread.start(); // Start the thread
    }

    // Method to update game state
    public void update() {
        levelManager.update(); // Update level manager
        player.update(); // Update player entity
    }

    // Method to render game graphics
    public void render(Graphics g) {
        levelManager.draw(g); // Draw level graphics
        player.render(g); // Render player graphics
    }

    // Runnable interface's method for running the game loop
    @Override
    public void run() {
        // Variables initialization for timing control
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        // Game loop
        while (true) {
            long currentTime = System.nanoTime();

            // Update timing control variables
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            // Update game logic if it's time for an update
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            // Render game graphics if it's time for a frame
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            // Print FPS and UPS every second
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    // Method to handle window focus lost event
    public void windowFocusLost() {
        player.resetDirBooleans(); // Reset player direction booleans
    }

    // Getter method for the player entity
    public Player getPlayer() {
        return player;
    }
}
