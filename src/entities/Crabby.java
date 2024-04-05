package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.IsFloor;
import static utilz.Constants.Dialogue.*;

import gamestates.Playing;

public class Crabby extends Enemy {

    // Constructor to initialize Crabby enemy with position and dimensions
    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY); // Call superclass constructor with position, width, height, and type
        initHitbox(22, 19); // Initialize hitbox
        initAttackBox(82, 19, 30); // Initialize attack box
    }

    // Method to update Crabby's behavior based on level data and game state
    public void update(int[][] lvlData, Playing playing) {
        updateBehavior(lvlData, playing); // Update Crabby's behavior
        updateAnimationTick(); // Update animation tick for sprite animation
        updateAttackBox(); // Update attack box position
    }

    // Method to update Crabby's behavior
    private void updateBehavior(int[][] lvlData, Playing playing) {
        if (firstUpdate)
            firstUpdateCheck(lvlData); // Check for first update to initialize variables

        if (inAir) {
            inAirChecks(lvlData, playing); // Perform checks when Crabby is in the air
        } else {
            switch (state) {
                case IDLE:
                    if (IsFloor(hitbox, lvlData)) // Check if Crabby is on the floor
                        newState(RUNNING); // Transition to running state
                    else
                        inAir = true; // Crabby is in the air
                    break;
                case RUNNING:
                    if (canSeePlayer(lvlData, playing.getPlayer())) { // Check if Crabby can see the player
                        turnTowardsPlayer(playing.getPlayer()); // Turn Crabby towards the player
                        if (isPlayerCloseForAttack(playing.getPlayer())) // Check if player is close for attack
                            newState(ATTACK); // Transition to attack state
                    }
                    move(lvlData); // Move Crabby

                    if (inAir)
                        playing.addDialogue((int) hitbox.x, (int) hitbox.y, EXCLAMATION); // Add dialogue bubble if Crabby is in the air
                    break;
                case ATTACK:
                    if (aniIndex == 0)
                        attackChecked = false;
                    if (aniIndex == 3 && !attackChecked)
                        checkPlayerHit(attackBox, playing.getPlayer()); // Check if Crabby's attack hits the player
                    break;
                case HIT:
                    if (aniIndex <= GetSpriteAmount(enemyType, state) - 2)
                        pushBack(pushBackDir, lvlData, 2f); // Push back Crabby when hit by player
                    updatePushBackDrawOffset(); // Update push back draw offset for animation
                    break;
            }
        }
    }
}
