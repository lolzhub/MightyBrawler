package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

// Class responsible for managing game levels
public class LevelManager {

    // Field declaration
    private Game game; // Reference to the main game instance
    private BufferedImage[] levelSprite; // Array to store sprites for the level
    private Level levelOne; // Instance representing the first level

    // Constructor
    public LevelManager(Game game) {
        this.game = game; // Set reference to the main game instance
        importOutsideSprites(); // Import sprites from an external atlas
        levelOne = new Level(LoadSave.GetLevelData()); // Initialize level one with level data from file
    }

    // Method to import sprites from an external atlas
    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS); // Load sprite atlas
        levelSprite = new BufferedImage[48]; // Initialize array to hold sprites
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32); // Extract individual sprites from atlas
            }
    }

    // Method to draw the level
    public void draw(Graphics g) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j); // Get sprite index from level data
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null); // Draw sprite at appropriate position
            }
    }

    // Method to update the level
    public void update() {
        // No update logic currently implemented
    }

    public Level getCurrentLevel(){
        return levelOne;
    }
}
