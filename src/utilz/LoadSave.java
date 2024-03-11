package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

// Utility class for loading and saving resources
public class LoadSave {

    // Constants for file names
    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";

    // Method to load a sprite atlas image
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName); // Get input stream for the specified file
        try {
            img = ImageIO.read(is); // Read the image from the input stream

        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace if an IO error occurs
        } finally {
            try {
                is.close(); // Close the input stream
            } catch (IOException e) {
                e.printStackTrace(); // Print stack trace if an IO error occurs while closing the stream
            }
        }
        return img; // Return the loaded image
    }

    // Method to get level data from an image
    public static int[][] GetLevelData() {
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH]; // Create 2D array for level data
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA); // Load the level data image

        // Loop through each pixel of the image
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j)); // Get color of the pixel
                int value = color.getRed(); // Get the red component of the color
                if (value >= 48)
                    value = 0; // If the value exceeds 48, set it to 0 (assuming it's a level indicator)
                lvlData[j][i] = value; // Store the value in the level data array
            }
        return lvlData; // Return the level data
    }
}
