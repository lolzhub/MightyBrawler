package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
    // Fields to store the playing state, enemy images, and the current level
    private Playing playing;
    private BufferedImage[][] crabbyArr, pinkstarArr, sharkArr;
    private Level currentLevel;

    // Constructor to initialize the enemy manager with the playing state
    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs(); // Load enemy images from sprite atlases
    }

    // Method to load enemies for the specified level
    public void loadEnemies(Level level) {
        this.currentLevel = level;
    }

    // Method to update all active enemies
    public void update(int[][] lvlData) {
        boolean isAnyActive = false;
        for (Crabby c : currentLevel.getCrabs())
            if (c.isActive()) {
                c.update(lvlData, playing);
                isAnyActive = true;
            }

        for (Pinkstar p : currentLevel.getPinkstars())
            if (p.isActive()) {
                p.update(lvlData, playing);
                isAnyActive = true;
            }

        for (Shark s : currentLevel.getSharks())
            if (s.isActive()) {
                s.update(lvlData, playing);
                isAnyActive = true;
            }

        if (!isAnyActive)
            playing.setLevelCompleted(true); // Mark level as completed if no active enemies remain
    }

    // Method to draw all active enemies
    public void draw(Graphics g, int xLvlOffset) {
        drawCrabs(g, xLvlOffset);
        drawPinkstars(g, xLvlOffset);
        drawSharks(g, xLvlOffset);
    }

    // Method to draw all active sharks
    private void drawSharks(Graphics g, int xLvlOffset) {
        for (Shark s : currentLevel.getSharks())
            if (s.isActive()) {
                g.drawImage(sharkArr[s.getState()][s.getAniIndex()], (int) s.getHitbox().x - xLvlOffset - SHARK_DRAWOFFSET_X + s.flipX(),
                        (int) s.getHitbox().y - SHARK_DRAWOFFSET_Y + (int) s.getPushDrawOffset(), SHARK_WIDTH * s.flipW(), SHARK_HEIGHT, null);
            }
    }

    // Method to draw all active pinkstars
    private void drawPinkstars(Graphics g, int xLvlOffset) {
        for (Pinkstar p : currentLevel.getPinkstars())
            if (p.isActive()) {
                g.drawImage(pinkstarArr[p.getState()][p.getAniIndex()], (int) p.getHitbox().x - xLvlOffset - PINKSTAR_DRAWOFFSET_X + p.flipX(),
                        (int) p.getHitbox().y - PINKSTAR_DRAWOFFSET_Y + (int) p.getPushDrawOffset(), PINKSTAR_WIDTH * p.flipW(), PINKSTAR_HEIGHT, null);
            }
    }

    // Method to draw all active crabs
    private void drawCrabs(Graphics g, int xLvlOffset) {
        for (Crabby c : currentLevel.getCrabs())
            if (c.isActive()) {
                g.drawImage(crabbyArr[c.getState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(),
                        (int) c.getHitbox().y - CRABBY_DRAWOFFSET_Y + (int) c.getPushDrawOffset(), CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);
            }
    }

    // Method to check if the attack box intersects with any active enemy hitbox
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Crabby c : currentLevel.getCrabs())
            if (c.isActive())
                if (c.getState() != DEAD && c.getState() != HIT)
                    if (attackBox.intersects(c.getHitbox())) {
                        c.hurt(20); // Inflict damage on crab if hit
                        return;
                    }

        for (Pinkstar p : currentLevel.getPinkstars())
            if (p.isActive()) {
                if (p.getState() == ATTACK && p.getAniIndex() >= 3)
                    return;
                else {
                    if (p.getState() != DEAD && p.getState() != HIT)
                        if (attackBox.intersects(p.getHitbox())) {
                            p.hurt(20); // Inflict damage on pinkstar if hit
                            return;
                        }
                }
            }

        for (Shark s : currentLevel.getSharks())
            if (s.isActive()) {
                if (s.getState() != DEAD && s.getState() != HIT)
                    if (attackBox.intersects(s.getHitbox())) {
                        s.hurt(20); // Inflict damage on shark if hit
                        return;
                    }
            }
    }

    // Method to load enemy images from sprite atlases
    private void loadEnemyImgs() {
        crabbyArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE), 9, 5, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
        pinkstarArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.PINKSTAR_ATLAS), 8, 5, PINKSTAR_WIDTH_DEFAULT, PINKSTAR_HEIGHT_DEFAULT);
        sharkArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.SHARK_ATLAS), 8, 5, SHARK_WIDTH_DEFAULT, SHARK_HEIGHT_DEFAULT);
    }

    // Method to create a 2D array of images from a sprite atlas
    private BufferedImage[][] getImgArr(BufferedImage atlas, int xSize, int ySize, int spriteW, int spriteH) {
        BufferedImage[][] tempArr = new BufferedImage[ySize][xSize];
        for (int j = 0; j < tempArr.length; j++)
            for (int i = 0; i < tempArr[j].length; i++)
                tempArr[j][i] = atlas.getSubimage(i * spriteW, j * spriteH, spriteW, spriteH);
        return tempArr;
    }

    // Method to reset all enemies in the current level
    public void resetAllEnemies() {
        for (Crabby c : currentLevel.getCrabs())
            c.resetEnemy();
        for (Pinkstar p : currentLevel.getPinkstars())
            p.resetEnemy();
        for (Shark s : currentLevel.getSharks())
            s.resetEnemy();
    }
}
