package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import utilz.LoadSave;

public class Credits extends State implements Statemethods {
    private BufferedImage backgroundImg, creditsImg;
    private int bgX, bgY, bgW, bgH;
    private float bgYFloat;

    private ArrayList<ShowEntity> entitiesList;

    // Constructor for Credits state
    public Credits(Game game) {
        super(game);
        // Load background and credits images
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
        creditsImg = LoadSave.GetSpriteAtlas(LoadSave.CREDITS);
        bgW = (int) (creditsImg.getWidth() * Game.SCALE);
        bgH = (int) (creditsImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = Game.GAME_HEIGHT;
        loadEntities(); // Load entities to display on the screen
    }

    // Method to load entities for display
    private void loadEntities() {
        entitiesList = new ArrayList<>();
        // Add player, crabby, pinkstar, and shark entities to the list
        entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS), 5, 64, 40), (int) (Game.GAME_WIDTH * 0.05), (int) (Game.GAME_HEIGHT * 0.8)));
        entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE), 9, 72, 32), (int) (Game.GAME_WIDTH * 0.15), (int) (Game.GAME_HEIGHT * 0.75)));
        entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.PINKSTAR_ATLAS), 8, 34, 30), (int) (Game.GAME_WIDTH * 0.7), (int) (Game.GAME_HEIGHT * 0.75)));
        entitiesList.add(new ShowEntity(getIdleAni(LoadSave.GetSpriteAtlas(LoadSave.SHARK_ATLAS), 8, 34, 30), (int) (Game.GAME_WIDTH * 0.8), (int) (Game.GAME_HEIGHT * 0.8)));
    }

    // Method to get idle animation frames from an atlas
    private BufferedImage[] getIdleAni(BufferedImage atlas, int spritesAmount, int width, int height) {
        BufferedImage[] arr = new BufferedImage[spritesAmount];
        for (int i = 0; i < spritesAmount; i++)
            arr[i] = atlas.getSubimage(width * i, 0, width, height);
        return arr;
    }

    // Method to update Credits state
    @Override
    public void update() {
        bgYFloat -= 0.2f; // Update background y position for scrolling effect
        for (ShowEntity se : entitiesList)
            se.update(); // Update entities' animations
    }

    // Method to draw Credits state
    @Override
    public void draw(Graphics g) {
        // Draw background and credits image
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(creditsImg, bgX, (int) (bgY + bgYFloat), bgW, bgH, null);

        // Draw entities
        for (ShowEntity se : entitiesList)
            se.draw(g);
    }

    // Method to handle key released event
    @Override
    public void keyReleased(KeyEvent e) {
        // If escape key is released, reset background y position and switch to Menu state
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            bgYFloat = 0;
            setGamestate(Gamestate.MENU);
        }
    }

    // Unused mouse event methods
    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    // Unused key event method
    @Override
    public void keyPressed(KeyEvent e) {}

    // Inner class to represent entities to be shown in the Credits state
    private class ShowEntity {
        private BufferedImage[] idleAnimation;
        private int x, y, aniIndex, aniTick;

        // Constructor for ShowEntity
        public ShowEntity(BufferedImage[] idleAnimation, int x, int y) {
            this.idleAnimation = idleAnimation;
            this.x = x;
            this.y = y;
        }

        // Method to draw the entity
        public void draw(Graphics g) {
            g.drawImage(idleAnimation[aniIndex], x, y, (int) (idleAnimation[aniIndex].getWidth() * 4), (int) (idleAnimation[aniIndex].getHeight() * 4), null);
        }

        // Method to update the entity's animation
        public void update() {
            aniTick++;
            if (aniTick >= 25) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= idleAnimation.length)
                    aniIndex = 0;
            }
        }
    }
}
