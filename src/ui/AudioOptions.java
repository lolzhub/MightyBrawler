package ui;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;
import static utilz.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static utilz.Constants.UI.VolumeButtons.VOLUME_HEIGHT;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;

// AudioOptions class manages audio settings in the game
public class AudioOptions {

    private VolumeButton volumeButton; // Volume adjustment button
    private SoundButton musicButton, sfxButton; // Sound effect buttons

    private Game game; // Reference to the Game object

    // Constructor to initialize AudioOptions with the game reference
    public AudioOptions(Game game) {
        this.game = game; // Set the game reference
        createSoundButtons(); // Create sound effect buttons
        createVolumeButton(); // Create volume adjustment button
    }

    // Method to create the volume adjustment button
    private void createVolumeButton() {
        int vX = (int) (309 * Game.SCALE);
        int vY = (int) (278 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT); // Initialize volume button
    }

    // Method to create sound effect buttons
    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE); // Initialize music button
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE); // Initialize sound effect button
    }

    // Method to update audio options
    public void update() {
        musicButton.update();
        sfxButton.update();
        volumeButton.update();
    }

    // Method to draw audio options
    public void draw(Graphics g) {
        // Draw sound effect buttons
        musicButton.draw(g);
        sfxButton.draw(g);
        // Draw volume adjustment button
        volumeButton.draw(g);
    }

    // Method to handle mouse drag event for volume adjustment
    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            float valueBefore = volumeButton.getFloatValue();
            volumeButton.changeX(e.getX());
            float valueAfter = volumeButton.getFloatValue();
            if (valueBefore != valueAfter)
                game.getAudioPlayer().setVolume(valueAfter);
        }
    }

    // Method to handle mouse press event
    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if (isIn(e, volumeButton))
            volumeButton.setMousePressed(true);
    }

    // Method to handle mouse release event
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
                game.getAudioPlayer().toggleSongMute();
            }
        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
                game.getAudioPlayer().toggleEffectMute();
            }
        }
        musicButton.resetBools();
        sfxButton.resetBools();
        volumeButton.resetBools();
    }

    // Method to handle mouse move event
    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);
        if (isIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if (isIn(e, volumeButton))
            volumeButton.setMouseOver(true);
    }

    // Method to check if the mouse is inside a button
    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
