package gamestates;

import java.awt.event.MouseEvent;

import audio.AudioPlayer;
import main.Game;
import ui.MenuButton;

public class State {

    protected Game game; // Reference to the game object

    // Constructor
    public State(Game game) {
        this.game = game;
    }

    // Checks if a mouse event occurs within the bounds of a menu button
    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    // Sets the game state
    @SuppressWarnings("incomplete-switch")
    public void setGamestate(Gamestate state) {
        switch (state) {
            // Play menu music when transitioning to the menu state
            case MENU -> game.getAudioPlayer().playSong(AudioPlayer.MENU_1);
            // Set level music when transitioning to the playing state
            case PLAYING -> game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
        }

        // Update the current game state
        Gamestate.state = state;
    }

}
