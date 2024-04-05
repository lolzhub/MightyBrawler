package gamestates;

// Enum to represent different game states
public enum Gamestate {

    PLAYING, // Game is currently being played
    MENU, // Main menu state
    OPTIONS, // Options menu state
    QUIT, // Quit state
    CREDITS; // Credits state

    // Static variable to store the current game state
    public static Gamestate state = MENU; // Default to MENU state

}
