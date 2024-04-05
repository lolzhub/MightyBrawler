package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class PauseOverlay {

    // Reference to the Playing game state
    private Playing playing;
    // Background image for the pause overlay
    private BufferedImage backgroundImg;
    // Background image position and size variables
    private int bgX, bgY, bgW, bgH;
    // Audio options component for adjusting audio settings
    private AudioOptions audioOptions;
    // Buttons for menu, replay, and unpause actions
    private UrmButton menuB, replayB, unpauseB;

    // Constructor
    public PauseOverlay(Playing playing) {
        this.playing = playing;
        // Load background image
        loadBackground();
        // Initialize audio options component
        audioOptions = playing.getGame().getAudioOptions();
        // Create buttons for menu, replay, and unpause actions
        createUrmButtons();
    }

    // Method to create buttons for menu, replay, and unpause actions
    private void createUrmButtons() {
        // Define X positions for the buttons
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        // Define Y position for the buttons
        int bY = (int) (325 * Game.SCALE);
        // Initialize menu, replay, and unpause buttons
        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    // Method to load the background image
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        // Calculate the scaled width and height of the background image
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        // Calculate the position to center the background image horizontally
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        // Define Y position for the background image
        bgY = (int) (25 * Game.SCALE);
    }

    // Method to update the pause overlay
    public void update() {
        // Update buttons for menu, replay, and unpause actions
        menuB.update();
        replayB.update();
        unpauseB.update();
        // Update audio options
        audioOptions.update();
    }

    // Method to draw the pause overlay
    public void draw(Graphics g) {
        // Draw the background image
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
        // Draw menu, replay, and unpause buttons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
        // Draw audio options
        audioOptions.draw(g);
    }

    // Method to handle mouse dragging
    public void mouseDragged(MouseEvent e) {
        // Pass mouse dragging event to audio options
        audioOptions.mouseDragged(e);
    }

    // Method to handle mouse press
    public void mousePressed(MouseEvent e) {
        // Check if mouse press occurs within any button's bounds
        if (isIn(e, menuB))
            menuB.setMousePressed(true);
        else if (isIn(e, replayB))
            replayB.setMousePressed(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMousePressed(true);
        else
            audioOptions.mousePressed(e);
    }

    // Method to handle mouse release
    public void mouseReleased(MouseEvent e) {
        // Check if mouse release occurs within any button's bounds
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                // Reset game and switch to menu state when menu button is clicked
                playing.resetAll();
                playing.setGamestate(Gamestate.MENU);
                playing.unpauseGame();
            }
        } else if (isIn(e, replayB)) {
            if (replayB.isMousePressed()) {
                // Reset game and unpause when replay button is clicked
                playing.resetAll();
                playing.unpauseGame();
            }
        } else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed())
                // Unpause when unpause button is clicked
                playing.unpauseGame();
        } else
            // Pass mouse release event to audio options
            audioOptions.mouseReleased(e);

        // Reset button press flags
        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
    }

    // Method to handle mouse movement
    public void mouseMoved(MouseEvent e) {
        // Reset mouse over state for all buttons
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);

        // Check if mouse is over any of the buttons
        if (isIn(e, menuB))
            menuB.setMouseOver(true);
        else if (isIn(e, replayB))
            replayB.setMouseOver(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMouseOver(true);
        else
            // Pass mouse movement event to audio options
            audioOptions.mouseMoved(e);
    }

    // Method to check if mouse event occurs within the bounds of a button
    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
