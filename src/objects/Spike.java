package objects;

import main.Game;

// Spike class represents spike objects in the game
public class Spike extends GameObject {

    // Constructor to initialize a spike object
    public Spike(int x, int y, int objType) {
        super(x, y, objType); // Call the constructor of the GameObject superclass
        initHitbox(32, 16); // Initialize hitbox for spike
        xDrawOffset = 0; // Set x draw offset
        yDrawOffset = (int)(Game.SCALE * 16); // Set y draw offset based on scale
        hitbox.y += yDrawOffset; // Adjust hitbox position based on y draw offset
    }
}
