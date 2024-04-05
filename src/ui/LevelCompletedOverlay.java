package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class LevelCompletedOverlay {

    // Reference to the Playing game state
    private Playing playing;
    // Buttons for menu and next level
    private UrmButton menu, next;
    // Image to display upon completing the level
    private BufferedImage img;
    // Background image position and size variables
    private int bgX, bgY, bgW, bgH;

    // Constructor
    public LevelCompletedOverlay(Playing playing) {
        this.playing = playing;
        // Initialize the level completed image
        initImg();
        // Initialize the buttons
        initButtons();
    }

    // Method to initialize menu and next level buttons
    private void initButtons() {
        // Define X and Y positions for the buttons
        int menuX = (int) (330 * Game.SCALE);
        int nextX = (int) (445 * Game.SCALE);
        int y = (int) (195 * Game.SCALE);
        // Initialize menu and next level buttons
        next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    // Method to initialize the level completed image
    private void initImg() {
        // Load the level completed image from file
        img = LoadSave.GetSpriteAtlas(LoadSave.COMPLETED_IMG);
        // Calculate the scaled width and height of the image
        bgW = (int) (img.getWidth() * Game.SCALE);
        bgH = (int) (img.getHeight() * Game.SCALE);
        // Calculate the position to center the image horizontally
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        // Define Y position for the image
        bgY = (int) (75 * Game.SCALE);
    }

    // Method to draw the level completed overlay
    public void draw(Graphics g) {
        // Draw a semi-transparent black background
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        // Draw the level completed image
        g.drawImage(img, bgX, bgY, bgW, bgH, null);
        // Draw menu and next level buttons
        next.draw(g);
        menu.draw(g);
    }

    // Method to update button states
    public void update() {
        next.update();
        menu.update();
    }

    // Method to check if the mouse is within a button's bounds
    private boolean isIn(UrmButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    // Method to handle mouse movement
    public void mouseMoved(MouseEvent e) {
        // Reset mouse over state for both buttons
        next.setMouseOver(false);
        menu.setMouseOver(false);

        // Check if the mouse is over any of the buttons
        if (isIn(menu, e))
            menu.setMouseOver(true);
        else if (isIn(next, e))
            next.setMouseOver(true);
    }

    // Method to handle mouse release
    public void mouseReleased(MouseEvent e) {
        // If menu button is clicked
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) {
                // Reset game and switch to menu state
                playing.resetAll();
                playing.setGamestate(Gamestate.MENU);
            }
        }
        // If next level button is clicked
        else if (isIn(next, e))
            if (next.isMousePressed()) {
                // Load the next level and play its corresponding song
                playing.loadNextLevel();
                playing.getGame().getAudioPlayer().setLevelSong(playing.getLevelManager().getLevelIndex());
            }

        // Reset button states
        menu.resetBools();
        next.resetBools();
    }

    // Method to handle mouse press
    public void mousePressed(MouseEvent e) {
        // Set mouse press state for clicked button
        if (isIn(menu, e))
            menu.setMousePressed(true);
        else if (isIn(next, e))
            next.setMousePressed(true);
    }
}
