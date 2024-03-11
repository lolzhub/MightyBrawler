package main;

// Class representing the game logic and setup
public class Game implements Runnable {

    // Fields to hold the game window, panel, thread, and frames per second
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    private final int UPS_SET = 200;

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

    public void update(){
        gamePanel.updateGame();
    }

    // Run method for the game loop
    @Override
    public void run() {
        // Calculate the time per frame in nanoseconds
        double timePerFrame = 1000000000.0 / FPS_SET;

        double timePerUpdate=1000000000.0 / UPS_SET;

        long previousTime=System.nanoTime();

        int frames = 0;
        int updates=0;
        long lastCheck = System.currentTimeMillis();

        double deltaU=0;
        double deltaF=0;

        // Main game loop
        while (true) {
            long currentTime=System.nanoTime();
            deltaU+=(currentTime-previousTime)/timePerUpdate;
            deltaF+=(currentTime-previousTime)/timePerFrame;

            previousTime=currentTime;
            if (deltaU>=1){
                update();
                updates++;
                deltaU--;
            }

            if (deltaF>=1){
                // Repaint the game panel
                gamePanel.repaint();
                // Increment the frame count
                frames++;
                deltaF--;

            }

            // Check if it's time to update the frames per second count
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                // Update the last check time
                lastCheck = System.currentTimeMillis();
                // Print the frames per second
                System.out.println("FPS: " + frames + "| "+"UPS: "+updates);
                // Reset the frame count
                frames = 0;
                updates=0;
            }
        }
    }
}
