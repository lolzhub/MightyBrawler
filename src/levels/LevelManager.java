package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import utilz.LoadSave;

// LevelManager class manages the loading and drawing of game levels
public class LevelManager {

    private Game game; // Reference to the Game instance
    private BufferedImage[] levelSprite; // Array to hold level sprites
    private BufferedImage[] waterSprite; // Array to hold water sprites
    private ArrayList<Level> levels; // List to hold all game levels
    private int lvlIndex = 0, aniTick, aniIndex; // Current level index and animation variables

    // Constructor for LevelManager class
    public LevelManager(Game game) {
        this.game = game; // Initialize reference to Game instance
        importOutsideSprites(); // Import level and water sprites
        createWater(); // Create water sprites
        levels = new ArrayList<>(); // Initialize list to hold levels
        buildAllLevels(); // Build all levels from saved images
    }

    // Method to create water sprites
    private void createWater() {
        waterSprite = new BufferedImage[5]; // Initialize array to hold water sprites
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.WATER_TOP); // Load water sprite atlas
        for (int i = 0; i < 4; i++)
            waterSprite[i] = img.getSubimage(i * 32, 0, 32, 32); // Extract water sprites from atlas
        waterSprite[4] = LoadSave.GetSpriteAtlas(LoadSave.WATER_BOTTOM); // Load bottom water sprite
    }

    // Method to load the next level
    public void loadNextLevel() {
        Level newLevel = levels.get(lvlIndex); // Get the next level
        // Load enemies, player data, and objects for the new level
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    // Method to build all levels from saved images
    private void buildAllLevels() {
        BufferedImage[] allLevels = LoadSave.GetAllLevels(); // Load all level images
        for (BufferedImage img : allLevels)
            levels.add(new Level(img)); // Build level objects from images
    }

    // Method to import level and water sprites
    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS); // Load level sprite atlas
        levelSprite = new BufferedImage[48]; // Initialize array to hold level sprites
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32); // Extract level sprites from atlas
            }
    }

    // Method to draw the current level
    public void draw(Graphics g, int lvlOffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < levels.get(lvlIndex).getLevelData()[0].length; i++) {
                int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                int x = Game.TILES_SIZE * i - lvlOffset;
                int y = Game.TILES_SIZE * j;
                // Draw water sprites
                if (index == 48)
                    g.drawImage(waterSprite[aniIndex], x, y, Game.TILES_SIZE, Game.TILES_SIZE, null);
                else if (index == 49)
                    g.drawImage(waterSprite[4], x, y, Game.TILES_SIZE, Game.TILES_SIZE, null);
                    // Draw level sprites
                else
                    g.drawImage(levelSprite[index], x, y, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }

    // Method to update the level manager
    public void update() {
        updateWaterAnimation(); // Update water animation
    }

    // Method to update water animation
    private void updateWaterAnimation() {
        aniTick++;
        if (aniTick >= 40) {
            aniTick = 0;
            aniIndex++;

            if (aniIndex >= 4)
                aniIndex = 0;
        }
    }

    // Getter method to retrieve the current level
    public Level getCurrentLevel() {
        return levels.get(lvlIndex);
    }

    // Getter method to retrieve the amount of levels
    public int getAmountOfLevels() {
        return levels.size();
    }

    // Getter method to retrieve the current level index
    public int getLevelIndex() {
        return lvlIndex;
    }

    // Setter method to set the current level index
    public void setLevelIndex(int lvlIndex) {
        this.lvlIndex = lvlIndex;
    }
}
