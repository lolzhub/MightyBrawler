package main;

// Class representing the game logic and setup
public class Game implements Runnable {

    // Fields to hold the game window, panel, thread, and frames per second
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    // Constructor for the Game class
    public Game() {
        // Initialize the game panel and window
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        // Request focus for the game panel
        gamePanel.requestFocus();
        // Start the game loop
        startGameLoop();
    }

    // Method to start the game loop
    private void startGameLoop() {
        // Create a new thread for the game loop
        gameThread = new Thread(this);
        // Start the thread
        gameThread.start();
    }

    // Run method for the game loop
    @Override
    public void run() {
        // Calculate the time per frame in nanoseconds
        double timePerFrame = 1000000000.0 / FPS_SET;
        // Record the time of the last frame
        long lastFrame = System.nanoTime();
        // Variables for tracking frames per second
        long now;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        // Main game loop
        while (true) {
            now = System.nanoTime();
            // Check if it's time to render the next frame
            if (now - lastFrame >= timePerFrame) {
                // Repaint the game panel
                gamePanel.repaint();
                // Update the last frame time
                lastFrame = now;
                // Increment the frame count
                frames++;
            }

            // Check if it's time to update the frames per second count
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                // Update the last check time
                lastCheck = System.currentTimeMillis();
                // Print the frames per second
                System.out.println("FPS: " + frames);
                // Reset the frame count
                frames = 0;
            }
        }
    }
}
