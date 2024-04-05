package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

// GameCompletedOverlay class handles the overlay displayed when the game is completed
public class GameCompletedOverlay {
    private Playing playing; // Reference to the Playing state
    private BufferedImage img; // Image for the overlay
    private MenuButton quit, credit; // Buttons for quitting or viewing credits
    private int imgX, imgY, imgW, imgH; // Position and dimensions of the image

    // Constructor to initialize the GameCompletedOverlay with the Playing state reference
    public GameCompletedOverlay(Playing playing) {
        this.playing = playing; // Set the reference to the Playing state
        createImg(); // Create the overlay image
        createButtons(); // Create the buttons for quitting or viewing credits
    }

    // Method to create the buttons
    private void createButtons() {
        quit = new MenuButton(Game.GAME_WIDTH / 2, (int) (270 * Game.SCALE), 2, Gamestate.MENU); // Quit button
        credit = new MenuButton(Game.GAME_WIDTH / 2, (int) (200 * Game.SCALE), 3, Gamestate.CREDITS); // Credits button
    }

    // Method to create the overlay image
    private void createImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.GAME_COMPLETED); // Load the image
        imgW = (int) (img.getWidth() * Game.SCALE); // Calculate width
        imgH = (int) (img.getHeight() * Game.SCALE); // Calculate height
        imgX = Game.GAME_WIDTH / 2 - imgW / 2; // Calculate x-coordinate
        imgY = (int) (100 * Game.SCALE); // Set y-coordinate
    }

    // Method to draw the overlay
    public void draw(Graphics g) {
        // Draw semi-transparent background
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        // Draw the overlay image
        g.drawImage(img, imgX, imgY, imgW, imgH, null);

        // Draw buttons
        credit.draw(g);
        quit.draw(g);
    }

    // Method to update the overlay
    public void update() {
        credit.update();
        quit.update();
    }

    // Method to check if the mouse is inside a button
    private boolean isIn(MenuButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    // Method to handle mouse movement
    public void mouseMoved(MouseEvent e) {
        credit.setMouseOver(false);
        quit.setMouseOver(false);

        if (isIn(quit, e))
            quit.setMouseOver(true);
        else if (isIn(credit, e))
            credit.setMouseOver(true);
    }

    // Method to handle mouse release
    public void mouseReleased(MouseEvent e) {
        if (isIn(quit, e)) {
            if (quit.isMousePressed()) {
                // Reset the game and return to the menu
                playing.resetAll();
                playing.resetGameCompleted();
                playing.setGamestate(Gamestate.MENU);
            }
        } else if (isIn(credit, e)) {
            if (credit.isMousePressed()) {
                // Reset the game and go to the credits screen
                playing.resetAll();
                playing.resetGameCompleted();
                playing.setGamestate(Gamestate.CREDITS);
            }
        }
        quit.resetBools();
        credit.resetBools();
    }

    // Method to handle mouse press
    public void mousePressed(MouseEvent e) {
        if (isIn(quit, e))
            quit.setMousePressed(true);
        else if (isIn(credit, e))
            credit.setMousePressed(true);
    }
}
