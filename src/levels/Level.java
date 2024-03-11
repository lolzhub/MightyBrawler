package levels;

// Class representing a game level
public class Level {

    // Field declaration
    private int[][] lvlData; // 2D array to store level data

    // Constructor
    public Level(int[][] lvlData) {
        this.lvlData = lvlData; // Initialize the level data with the provided array
    }

    // Method to get the sprite index at a specific position in the level
    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x]; // Return the sprite index at the given coordinates in the level data
    }
}
