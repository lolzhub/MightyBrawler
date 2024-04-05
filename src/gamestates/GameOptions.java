package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.AudioOptions;
import ui.PauseButton;
import ui.UrmButton;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class GameOptions extends State implements Statemethods {

    private AudioOptions audioOptions;
    private BufferedImage backgroundImg, optionsBackgroundImg;
    private int bgX, bgY, bgW, bgH;
    private UrmButton menuB;

    // Constructor for GameOptions state
    public GameOptions(Game game) {
        super(game);
        loadImgs(); // Load images for the background and options menu
        loadButton(); // Load the menu button
        audioOptions = game.getAudioOptions(); // Get audio options from the game
    }

    // Method to load the menu button
    private void loadButton() {
        int menuX = (int) (387 * Game.SCALE);
        int menuY = (int) (325 * Game.SCALE);

        menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
    }

    // Method to load images for the background and options menu
    private void loadImgs() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
        optionsBackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.OPTIONS_MENU);

        bgW = (int) (optionsBackgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (optionsBackgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (33 * Game.SCALE);
    }

    // Method to update the GameOptions state
    @Override
    public void update() {
        menuB.update(); // Update the menu button
        audioOptions.update(); // Update the audio options
    }

    // Method to draw the GameOptions state
    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(optionsBackgroundImg, bgX, bgY, bgW, bgH, null);

        menuB.draw(g); // Draw the menu button
        audioOptions.draw(g); // Draw the audio options
    }

    // Method to handle mouse dragged event
    public void mouseDragged(MouseEvent e) {
        audioOptions.mouseDragged(e); // Pass the event to audio options
    }

    // Method to handle mouse pressed event
    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuB)) {
            menuB.setMousePressed(true); // Set menu button pressed
        } else
            audioOptions.mousePressed(e); // Pass the event to audio options
    }

    // Method to handle mouse released event
    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed())
                Gamestate.state = Gamestate.MENU; // Switch to Menu state if menu button is pressed
        } else
            audioOptions.mouseReleased(e); // Pass the event to audio options
        menuB.resetBools(); // Reset button state
    }

    // Method to handle mouse moved event
    @Override
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false); // Set menu button mouse over state to false

        if (isIn(e, menuB))
            menuB.setMouseOver(true); // Set menu button mouse over state to true if mouse is over the button
        else
            audioOptions.mouseMoved(e); // Pass the event to audio options
    }

    // Method to handle key pressed event
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            Gamestate.state = Gamestate.MENU; // Switch to Menu state if escape key is pressed
    }

    // Unused key event methods
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    // Method to check if a mouse event is inside a button's bounds
    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}
