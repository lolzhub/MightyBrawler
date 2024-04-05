package entities;

import static utilz.Constants.Dialogue.*;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.IsFloor;

import gamestates.Playing;

public class Shark extends Enemy {

    // Constructor to initialize Shark object
    public Shark(float x, float y) {
        super(x, y, SHARK_WIDTH, SHARK_HEIGHT, SHARK);
        initHitbox(18, 22); // Initialize hitbox dimensions
        initAttackBox(20, 20, 20); // Initialize attack box dimensions
    }

    // Method to update Shark's behavior
    public void update(int[][] lvlData, Playing playing) {
        updateBehavior(lvlData, playing); // Update behavior based on state
        updateAnimationTick(); // Update animation tick
        updateAttackBoxFlip(); // Update attack box flip based on direction
    }

    // Method to update Shark's behavior based on state
    private void updateBehavior(int[][] lvlData, Playing playing) {
        if (firstUpdate)
            firstUpdateCheck(lvlData); // Check if it's the first update

        if (inAir)
            inAirChecks(lvlData, playing); // Check behavior when in air
        else {
            switch (state) {
                case IDLE:
                    if (IsFloor(hitbox, lvlData))
                        newState(RUNNING); // Transition to RUNNING state if on floor
                    else
                        inAir = true; // Transition to inAir state if not on floor
                    break;
                case RUNNING:
                    if (canSeePlayer(lvlData, playing.getPlayer())) {
                        turnTowardsPlayer(playing.getPlayer()); // Turn towards player if visible
                        if (isPlayerCloseForAttack(playing.getPlayer()))
                            newState(ATTACK); // Transition to ATTACK state if player is close enough
                    }

                    move(lvlData); // Move Shark
                    break;
                case ATTACK:
                    if (aniIndex == 0)
                        attackChecked = false; // Reset attack check flag at the beginning of attack animation
                    else if (aniIndex == 3) {
                        if (!attackChecked)
                            checkPlayerHit(attackBox, playing.getPlayer()); // Check if player is hit during attack animation
                        attackMove(lvlData, playing); // Move Shark during attack
                    }

                    break;
                case HIT:
                    if (aniIndex <= GetSpriteAmount(enemyType, state) - 2)
                        pushBack(pushBackDir, lvlData, 2f); // Apply pushback if hit
                    updatePushBackDrawOffset(); // Update pushback draw offset
                    break;
            }
        }
    }

    // Method to handle Shark's movement during attack
    protected void attackMove(int[][] lvlData, Playing playing) {
        float xSpeed = 0;

        if (walkDir == LEFT)
            xSpeed = -walkSpeed; // Set movement speed to the left
        else
            xSpeed = walkSpeed; // Set movement speed to the right

        if (CanMoveHere(hitbox.x + xSpeed * 4, hitbox.y, hitbox.width, hitbox.height, lvlData))
            if (IsFloor(hitbox, xSpeed * 4, lvlData)) {
                hitbox.x += xSpeed * 4; // Move Shark horizontally during attack
                return;
            }
        newState(IDLE); // Transition back to IDLE state if movement is obstructed
        playing.addDialogue((int) hitbox.x, (int) hitbox.y, EXCLAMATION); // Add dialogue bubble
    }
}
