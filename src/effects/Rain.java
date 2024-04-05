package effects;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;
import utilz.LoadSave;

public class Rain {

    // Array to store raindrop positions, random number generator, rain speed, and rain particle image
    private Point2D.Float[] drops;
    private Random rand;
    private float rainSpeed = 1.25f;
    private BufferedImage rainParticle;

    // Constructor to initialize rain effect
    public Rain() {
        rand = new Random(); // Initialize random number generator
        drops = new Point2D.Float[1000]; // Initialize array to store raindrop positions
        rainParticle = LoadSave.GetSpriteAtlas(LoadSave.RAIN_PARTICLE); // Load rain particle image
        initDrops(); // Initialize raindrop positions
    }

    // Method to initialize raindrop positions
    private void initDrops() {
        for (int i = 0; i < drops.length; i++)
            drops[i] = getRndPos(); // Set random positions for each raindrop
    }

    // Method to get a random position for a raindrop
    private Point2D.Float getRndPos() {
        return new Point2D.Float((int) getNewX(0), rand.nextInt(Game.GAME_HEIGHT)); // Random x within game width, random y within game height
    }

    // Method to update raindrop positions
    public void update(int xLvlOffset) {
        for (Point2D.Float p : drops) {
            p.y += rainSpeed; // Move raindrop down by rain speed
            if (p.y >= Game.GAME_HEIGHT) { // If raindrop goes below game window
                p.y = -20; // Reset raindrop above the game window
                p.x = getNewX(xLvlOffset); // Reset x position randomly
            }
        }
    }

    // Method to get a new x position for raindrop
    private float getNewX(int xLvlOffset) {
        float value = (-Game.GAME_WIDTH) + rand.nextInt((int) (Game.GAME_WIDTH * 3f)) + xLvlOffset; // Random x position within extended game width
        return value;
    }

    // Method to draw raindrops on screen
    public void draw(Graphics g, int xLvlOffset) {
        for (Point2D.Float p : drops)
            g.drawImage(rainParticle, (int) p.getX() - xLvlOffset, (int) p.getY(), 3, 12, null); // Draw rain particle at raindrop position
    }
}
