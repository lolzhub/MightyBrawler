package audio;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

    // Constants for identifying different songs and effects
    public static int MENU_1 = 0;
    public static int LEVEL_1 = 1;
    public static int LEVEL_2 = 2;

    public static int DIE = 0;
    public static int JUMP = 1;
    public static int GAMEOVER = 2;
    public static int LVL_COMPLETED = 3;
    public static int ATTACK_ONE = 4;
    public static int ATTACK_TWO = 5;
    public static int ATTACK_THREE = 6;

    // Arrays to store songs and effects
    private Clip[] songs, effects;
    private int currentSongId; // Track the currently playing song
    private float volume = 0.5f; // Default volume
    private boolean songMute, effectMute; // Flags to control muting of songs and effects
    private Random rand = new Random(); // Random number generator for selecting attack sounds

    // Constructor
    public AudioPlayer() {
        loadSongs(); // Load songs
        loadEffects(); // Load effects
        playSong(MENU_1); // Play the menu song by default
    }

    // Load songs from file
    private void loadSongs() {
        String[] names = { "menu", "level1", "level2" }; // Names of the songs
        songs = new Clip[names.length]; // Initialize the array to store clips
        for (int i = 0; i < songs.length; i++)
            songs[i] = getClip(names[i]); // Load each song
    }

    // Load effects from file
    private void loadEffects() {
        String[] effectNames = { "die", "jump", "gameover", "lvlcompleted", "attack1", "attack2", "attack3" }; // Names of the effects
        effects = new Clip[effectNames.length]; // Initialize the array to store clips
        for (int i = 0; i < effects.length; i++)
            effects[i] = getClip(effectNames[i]); // Load each effect
        updateEffectsVolume(); // Update the volume of effects
    }

    // Load a clip from file
    private Clip getClip(String name) {
        URL url = getClass().getResource("/audio/" + name + ".wav"); // Get the URL of the audio file
        AudioInputStream audio;
        try {
            audio = AudioSystem.getAudioInputStream(url); // Get the audio input stream
            Clip c = AudioSystem.getClip(); // Get a clip
            c.open(audio); // Open the clip
            return c; // Return the loaded clip
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace(); // Print error if loading fails
        }
        return null; // Return null if loading fails
    }

    // Set the volume for both songs and effects
    public void setVolume(float volume) {
        this.volume = volume; // Update volume
        updateSongVolume(); // Update song volume
        updateEffectsVolume(); // Update effect volume
    }

    // Stop the currently playing song
    public void stopSong() {
        if (songs[currentSongId].isActive()) // Check if the song is active
            songs[currentSongId].stop(); // Stop the song
    }

    // Set the song for the specified level
    public void setLevelSong(int lvlIndex) {
        if (lvlIndex % 2 == 0)
            playSong(LEVEL_1); // Play level 1 song
        else
            playSong(LEVEL_2); // Play level 2 song
    }

    // Called when a level is completed
    public void lvlCompleted() {
        stopSong(); // Stop the current song
        playEffect(LVL_COMPLETED); // Play the level completed sound effect
    }

    // Play a random attack sound
    public void playAttackSound() {
        int start = 4; // Starting index for attack sounds
        start += rand.nextInt(3); // Get a random index within the range of attack sounds
        playEffect(start); // Play the selected attack sound
    }

    // Play a specific effect
    public void playEffect(int effect) {
        if (effects[effect].getMicrosecondPosition() > 0)
            effects[effect].setMicrosecondPosition(0); // Rewind effect to start if already playing
        effects[effect].start(); // Start playing the effect
    }

    // Play a specific song
    public void playSong(int song) {
        stopSong(); // Stop the current song
        currentSongId = song; // Update current song ID
        updateSongVolume(); // Update song volume
        songs[currentSongId].setMicrosecondPosition(0); // Rewind song to start
        songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY); // Start playing the song in a loop
    }

    // Toggle muting of songs
    public void toggleSongMute() {
        this.songMute = !songMute; // Toggle mute flag
        for (Clip c : songs) {
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE); // Get mute control
            booleanControl.setValue(songMute); // Set mute value
        }
    }

    // Toggle muting of effects
    public void toggleEffectMute() {
        this.effectMute = !effectMute; // Toggle mute flag
        for (Clip c : effects) {
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE); // Get mute control
            booleanControl.setValue(effectMute); // Set mute value
        }
        if (!effectMute)
            playEffect(JUMP); // Play jump effect if effects are unmuted
    }

    // Update volume for the currently playing song
    private void updateSongVolume() {
        FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN); // Get volume control
        float range = gainControl.getMaximum() - gainControl.getMinimum(); // Get volume range
        float gain = (range * volume) + gainControl.getMinimum(); // Calculate volume level
        gainControl.setValue(gain); // Set volume
    }

    // Update volume for effects
    private void updateEffectsVolume() {
        for (Clip c : effects) {
            FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN); // Get volume control
            float range = gainControl.getMaximum() - gainControl.getMinimum(); // Get volume range
            float gain = (range * volume) + gainControl.getMinimum(); // Calculate volume level
            gainControl.setValue(gain); // Set volume
        }
    }
}
