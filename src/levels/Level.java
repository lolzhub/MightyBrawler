package levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Crabby;
import entities.Pinkstar;
import entities.Shark;
import main.Game;
import objects.BackgroundTree;
import objects.Cannon;
import objects.GameContainer;
import objects.Grass;
import objects.Potion;
import objects.Spike;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.ObjectConstants.*;

public class Level {

    private BufferedImage img; // The image representing the level
    private int[][] lvlData; // Array to store level data (tile types)

    // ArrayLists to store various game objects/entities present in the level
    private ArrayList<Crabby> crabs = new ArrayList<>();
    private ArrayList<Pinkstar> pinkstars = new ArrayList<>();
    private ArrayList<Shark> sharks = new ArrayList<>();
    private ArrayList<Potion> potions = new ArrayList<>();
    private ArrayList<Spike> spikes = new ArrayList<>();
    private ArrayList<GameContainer> containers = new ArrayList<>();
    private ArrayList<Cannon> cannons = new ArrayList<>();
    private ArrayList<BackgroundTree> trees = new ArrayList<>();
    private ArrayList<Grass> grass = new ArrayList<>();

    private int lvlTilesWide; // Width of the level in tiles
    private int maxTilesOffset; // Maximum tiles offset
    private int maxLvlOffsetX; // Maximum level offset on the X-axis
    private Point playerSpawn; // Spawn point for the player

    // Constructor for the Level class
    public Level(BufferedImage img) {
        this.img = img;
        lvlData = new int[img.getHeight()][img.getWidth()]; // Initialize level data array
        loadLevel(); // Load the level from the image
        calcLvlOffsets(); // Calculate level offsets
    }

    // Method to load level data from the image
    private void loadLevel() {
        for (int y = 0; y < img.getHeight(); y++)
            for (int x = 0; x < img.getWidth(); x++) {
                Color c = new Color(img.getRGB(x, y));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                // Load level data, entities, and objects based on color values
                loadLevelData(red, x, y);
                loadEntities(green, x, y);
                loadObjects(blue, x, y);
            }
    }

    // Method to load level data based on red color values
    private void loadLevelData(int redValue, int x, int y) {
        if (redValue >= 50)
            lvlData[y][x] = 0;
        else
            lvlData[y][x] = redValue;

        // Load grass objects based on specific red values
        switch (redValue) {
            case 0, 1, 2, 3, 30, 31, 33, 34, 35, 36, 37, 38, 39 ->
                    grass.add(new Grass((int) (x * Game.TILES_SIZE), (int) (y * Game.TILES_SIZE) - Game.TILES_SIZE, getRndGrassType(x)));
        }
    }

    // Method to determine grass type based on X position
    private int getRndGrassType(int xPos) {
        return xPos % 2;
    }

    // Method to load entities based on green color values
    private void loadEntities(int greenValue, int x, int y) {
        switch (greenValue) {
            case CRABBY -> crabs.add(new Crabby(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
            case PINKSTAR -> pinkstars.add(new Pinkstar(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
            case SHARK -> sharks.add(new Shark(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
            case 100 -> playerSpawn = new Point(x * Game.TILES_SIZE, y * Game.TILES_SIZE);
        }
    }

    // Method to load objects based on blue color values
    private void loadObjects(int blueValue, int x, int y) {
        switch (blueValue) {
            case RED_POTION, BLUE_POTION -> potions.add(new Potion(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
            case BOX, BARREL -> containers.add(new GameContainer(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
            case SPIKE -> spikes.add(new Spike(x * Game.TILES_SIZE, y * Game.TILES_SIZE, SPIKE));
            case CANNON_LEFT, CANNON_RIGHT -> cannons.add(new Cannon(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
            case TREE_ONE, TREE_TWO, TREE_THREE -> trees.add(new BackgroundTree(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
        }
    }

    // Method to calculate level offsets
    private void calcLvlOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
    }

    // Getter method to retrieve sprite index from level data
    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    // Getter method to retrieve the level data array
    public int[][] getLevelData() {
        return lvlData;
    }

    // Getter method to retrieve the maximum level offset on the X-axis
    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    // Getter method to retrieve the player spawn point
    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    // Getter methods for various game objects/entities
    public ArrayList<Crabby> getCrabs() {
        return crabs;
    }

    public ArrayList<Shark> getSharks() {
        return sharks;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<GameContainer> getContainers() {
        return containers;
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

    public ArrayList<Cannon> getCannons() {
        return cannons;
    }

    public ArrayList<Pinkstar> getPinkstars() {
        return pinkstars;
    }

    public ArrayList<BackgroundTree> getTrees() {
        return trees;
    }

    public ArrayList<Grass> getGrass() {
        return grass;
    }

}
