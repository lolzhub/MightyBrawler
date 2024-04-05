package main;

import java.awt.Graphics;

import audio.AudioPlayer;
import gamestates.Credits;
import gamestates.GameOptions;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import ui.AudioOptions;

// Game class implements the main game loop and manages game states
public class Game implements Runnable {

    private GamePanel gamePanel; // Reference to the GamePanel instance
    private Thread gameThread; // Thread for the game loop
    private final int FPS_SET = 120; // Target frames per second
    private final int UPS_SET = 200; // Target updates per second

    // Game state instances
    private Playing playing;
    private Menu menu;
    private Credits credits;
    private GameOptions gameOptions;
    private AudioOptions audioOptions;
    private AudioPlayer audioPlayer;

    // Constants defining game dimensions and scale
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.6f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    // Flag to show FPS and UPS in the console
    private final boolean SHOW_FPS_UPS = true;

    // Constructor for the Game class
    public Game() {
        System.out.println("size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
        initClasses(); // Initialize game state classes
        gamePanel = new GamePanel(this); // Create the GamePanel
        new GameWindow(gamePanel); // Create the GameWindow
        gamePanel.requestFocusInWindow(); // Request focus for the GamePanel
        startGameLoop(); // Start the game loop
    }

    // Method to initialize game state classes
    private void initClasses() {
        audioOptions = new AudioOptions(this);
        audioPlayer = new AudioPlayer();
        menu = new Menu(this);
        playing = new Playing(this);
        credits = new Credits(this);
        gameOptions = new GameOptions(this);
    }

    // Method to start the game loop
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Method to update game logic
    public void update() {
        // Update logic based on the current game state
        switch (Gamestate.state) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
            case OPTIONS -> gameOptions.update();
            case CREDITS -> credits.update();
            case QUIT -> System.exit(0);
        }
    }

    // Method to render the game
    @SuppressWarnings("incomplete-switch")
    public void render(Graphics g) {
        // Render based on the current game state
        switch (Gamestate.state) {
            case MENU -> menu.draw(g);
            case PLAYING -> playing.draw(g);
            case OPTIONS -> gameOptions.draw(g);
            case CREDITS -> credits.draw(g);
        }
    }

    // Main game loop
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update(); // Update game logic
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint(); // Render game
                frames++;
                deltaF--;
            }

            // Display FPS and UPS if enabled
            if (SHOW_FPS_UPS)
                if (System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS: " + frames + " | UPS: " + updates);
                    frames = 0;
                    updates = 0;
                }
        }
    }

    // Method called when the game window loses focus
    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING)
            playing.getPlayer().resetDirBooleans(); // Reset player direction booleans
    }

    // Getter methods for various game state instances
    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Credits getCredits() {
        return credits;
    }

    public GameOptions getGameOptions() {
        return gameOptions;
    }

    public AudioOptions getAudioOptions() {
        return audioOptions;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
