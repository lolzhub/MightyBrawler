package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;

public class SoundButton extends PauseButton {

    // Array to hold images for sound button
    private BufferedImage[][] soundImgs;
    // Variables to track mouse interaction states
    private boolean mouseOver, mousePressed;
    // Variable to indicate whether the sound is muted or not
    private boolean muted;
    // Indices to determine which image to display
    private int rowIndex, colIndex;

    // Constructor
    public SoundButton(int x, int y, int width, int height) {
        // Call superclass constructor
        super(x, y, width, height);
        // Load sound button images
        loadSoundImgs();
    }

    // Method to load sound button images from sprite atlas
    private void loadSoundImgs() {
        // Load sprite atlas containing sound button images
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
        // Initialize array to hold sound button images
        soundImgs = new BufferedImage[2][3];
        // Extract individual sound button images from sprite atlas
        for (int j = 0; j < soundImgs.length; j++)
            for (int i = 0; i < soundImgs[j].length; i++)
                soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
    }

    // Method to update the state of the sound button
    public void update() {
        // Determine row index based on whether sound is muted or not
        if (muted)
            rowIndex = 1;
        else
            rowIndex = 0;

        // Default to first column index
        colIndex = 0;
        // Update column index based on mouse interaction states
        if (mouseOver)
            colIndex = 1;
        if (mousePressed)
            colIndex = 2;
    }

    // Method to reset mouse interaction flags
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    // Method to draw the sound button
    public void draw(Graphics g) {
        g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
    }

    // Getter for mouseOver flag
    public boolean isMouseOver() {
        return mouseOver;
    }

    // Setter for mouseOver flag
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    // Getter for mousePressed flag
    public boolean isMousePressed() {
        return mousePressed;
    }

    // Setter for mousePressed flag
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    // Getter for muted flag
    public boolean isMuted() {
        return muted;
    }

    // Setter for muted flag
    public void setMuted(boolean muted) {
        this.muted = muted;
    }

}
