package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Dialogue.*;
import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.IsFloor;
import static utilz.Constants.Directions.*;

import gamestates.Playing;

public class Pinkstar extends Enemy {

    // Fields specific to Pinkstar entity
    private boolean preRoll = true; // Flag to indicate whether Pinkstar is in pre-roll state
    private int tickSinceLastDmgToPlayer; // Counter to track ticks since last damage to player
    private int tickAfterRollInIdle; // Counter to track ticks after roll in idle state
    private int rollDurationTick, rollDuration = 300; // Counters for roll duration

    // Constructor to initialize Pinkstar with position
    public Pinkstar(float x, float y) {
        super(x, y, PINKSTAR_WIDTH, PINKSTAR_HEIGHT, PINKSTAR);
        initHitbox(17, 21); // Initialize hitbox
    }

    // Method to update Pinkstar's behavior
    public void update(int[][] lvlData, Playing playing) {
        updateBehavior(lvlData, playing); // Update Pinkstar's behavior
        updateAnimationTick(); // Update animation tick
    }

    // Method to update Pinkstar's behavior based on current state
    private void updateBehavior(int[][] lvlData, Playing playing) {
        // Check if it's the first update
        if (firstUpdate)
            firstUpdateCheck(lvlData); // Check if Pinkstar is in the air in the first update

        // Check Pinkstar's behavior based on its state
        if (inAir)
            inAirChecks(lvlData, playing); // Handle behavior when Pinkstar is in the air
        else {
            switch (state) {
                case IDLE:
                    preRoll = true; // Pinkstar is in pre-roll state
                    // Check if enough ticks have passed after roll in idle state to switch state
                    if (tickAfterRollInIdle >= 120) {
                        // Check if Pinkstar is on the floor to switch to running state or in the air
                        if (IsFloor(hitbox, lvlData))
                            newState(RUNNING);
                        else
                            inAir = true;
                        // Reset counters
                        tickAfterRollInIdle = 0;
                        tickSinceLastDmgToPlayer = 60;
                    } else
                        tickAfterRollInIdle++; // Increment tick counter
                    break;
                case RUNNING:
                    // Check if Pinkstar can see the player to switch to attack state
                    if (canSeePlayer(lvlData, playing.getPlayer())) {
                        newState(ATTACK);
                        setWalkDir(playing.getPlayer()); // Set walking direction towards the player
                    }
                    move(lvlData, playing); // Move Pinkstar
                    break;
                case ATTACK:
                    if (preRoll) {
                        // Check if Pinkstar is in pre-roll state
                        if (aniIndex >= 3)
                            preRoll = false; // Set pre-roll flag to false
                    } else {
                        // Move Pinkstar during attack state
                        move(lvlData, playing);
                        // Check for damage to player during attack
                        checkDmgToPlayer(playing.getPlayer());
                        // Check if Pinkstar should roll over to idle state
                        checkRollOver(playing);
                    }
                    break;
                case HIT:
                    // Handle behavior when Pinkstar is hit
                    if (aniIndex <= GetSpriteAmount(enemyType, state) - 2)
                        pushBack(pushBackDir, lvlData, 2f); // Apply push-back effect
                    updatePushBackDrawOffset(); // Update push-back draw offset
                    tickAfterRollInIdle = 120; // Reset tick counter after roll in idle state
                    break;
            }
        }
    }

    // Method to check for damage to player during attack
    private void checkDmgToPlayer(Player player) {
        if (hitbox.intersects(player.getHitbox()))
            if (tickSinceLastDmgToPlayer >= 60) {
                // Apply damage to player if enough ticks have passed since last damage
                tickSinceLastDmgToPlayer = 0;
                player.changeHealth(-GetEnemyDmg(enemyType), this);
            } else
                tickSinceLastDmgToPlayer++; // Increment tick counter
    }

    // Method to set walking direction towards the player
    private void setWalkDir(Player player) {
        if (player.getHitbox().x > hitbox.x)
            walkDir = RIGHT; // Set walking direction to right if player is to the right of Pinkstar
        else
            walkDir = LEFT; // Set walking direction to left if player is to the left of Pinkstar
    }

    // Method to move Pinkstar
    protected void move(int[][] lvlData, Playing playing) {
        float xSpeed = 0;

        // Calculate movement speed based on walking direction
        if (walkDir == LEFT)
            xSpeed = -walkSpeed;
        else
            xSpeed = walkSpeed;

        // Double movement speed during attack state
        if (state == ATTACK)
            xSpeed *= 2;

        // Move Pinkstar horizontally if there is space
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
            if (IsFloor(hitbox, xSpeed, lvlData)) {
                hitbox.x += xSpeed; // Update Pinkstar's position
                return;
            }

        // Check if Pinkstar should roll over to idle state during attack
        if (state == ATTACK) {
            rollOver(playing); // Roll over to idle state
            rollDurationTick = 0; // Reset roll duration tick counter
        }

        changeWalkDir(); // Change walking direction if movement is obstructed
    }

    // Method to check if Pinkstar should roll over to idle state during attack
    private void checkRollOver(Playing playing) {
        rollDurationTick++; // Increment roll duration tick counter
        // Check if roll duration has reached its limit
        if (rollDurationTick >= rollDuration) {
            rollOver(playing); // Roll over to idle state
            rollDurationTick = 0; // Reset roll duration tick counter
        }
    }

    // Method to roll over Pinkstar to idle state
    private void rollOver(Playing playing) {
        newState(IDLE); // Switch to idle state
        playing.addDialogue((int) hitbox.x, (int) hitbox.y, QUESTION); // Add dialogue bubble
    }

}
