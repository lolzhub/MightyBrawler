package ui;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class GameOverOverlay {

    // Reference to the Playing game state
    private Playing playing;
    // Image to display as game over screen
    private BufferedImage img;
    // Image position and size variables
    private int imgX, imgY, imgW, imgH;
    // Buttons for menu and play
    private UrmButton menu, play;

    // Constructor
    public GameOverOverlay(Playing playing) {
        this.playing = playing;
        // Initialize the game over image
        createImg();
        // Initialize the buttons
        createButtons();
    }

    // Method to create menu and play buttons
    private void createButtons() {
        // Define X and Y positions for the buttons
        int menuX = (int) (335 * Game.SCALE);
        int playX = (int) (440 * Game.SCALE);
        int y = (int) (195 * Game.SCALE);
        // Initialize menu and play buttons
        play = new UrmButton(playX, y, URM_SIZE, URM_SIZE, 0);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    // Method to load the game over image
    private void createImg() {
        // Load the game over image from file
        img = LoadSave.GetSpriteAtlas(LoadSave.DEATH_SCREEN);
        // Calculate the scaled width and height of the image
        imgW = (int) (img.getWidth() * Game.SCALE);
        imgH = (int) (img.getHeight() * Game.SCALE);
        // Calculate the position to center the image horizontally
        imgX = Game.GAME_WIDTH / 2 - imgW / 2;
        // Define Y position for the image
        imgY = (int) (100 * Game.SCALE);
    }

    // Method to draw the game over overlay
    public void draw(Graphics g) {
        // Draw a semi-transparent black background
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        // Draw the game over image
        g.drawImage(img, imgX, imgY, imgW, imgH, null);

        // Draw menu and play buttons
        menu.draw(g);
        play.draw(g);
    }

    // Method to update button states
    public void update() {
        menu.update();
        play.update();
    }

    // Method to check if the mouse is within a button's bounds
    private boolean isIn(UrmButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    // Method to handle mouse movement
    public void mouseMoved(MouseEvent e) {
        // Reset mouse over state for both buttons
        play.setMouseOver(false);
        menu.setMouseOver(false);

        // Check if the mouse is over any of the buttons
        if (isIn(menu, e))
            menu.setMouseOver(true);
        else if (isIn(play, e))
            play.setMouseOver(true);
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
        // If play button is clicked
        else if (isIn(play, e))
            if (play.isMousePressed()) {
                // Reset game and play level song
                playing.resetAll();
                playing.getGame().getAudioPlayer().setLevelSong(playing.getLevelManager().getLevelIndex());
            }

        // Reset button states
        menu.resetBools();
        play.resetBools();
    }

    // Method to handle mouse press
    public void mousePressed(MouseEvent e) {
        // Set mouse press state for clicked button
        if (isIn(menu, e))
            menu.setMousePressed(true);
        else if (isIn(play, e))
            play.setMousePressed(true);
    }
}
