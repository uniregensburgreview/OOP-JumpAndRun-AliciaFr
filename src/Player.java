import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import de.ur.mi.sound.Sound;

/**
 * This class represent a player, which is setup as a rect and is able to jump.
 */

public class Player {

    /* private constants for the jump */
    private static final double _maxTurnPoint = Configuration.CANVAS_HEIGHT/2;
    private static final double x_speed = 0;
    private static final double y_speed = -3;

    /* setup for player */
    private static final int player_width = Configuration.CANVAS_WIDTH / 50;
    private static final int player_heigt = Configuration.GROUND_HEIGHT / 3;

    /* instance variables for player */
    private Rect player; // Represents the player
    private double dx; // Velocity in the x direction
    private double dy; // Velocity in the y direction

    /* instance vars for jump */
    private boolean jumped = false;
    private double jumpY;

    public Player() {
        double xPos = Configuration.CANVAS_WIDTH / 8;
        double yPos = Configuration.GROUND_Y_POS - PLAYER_HEIGHT;
        int width = player_width;
        int height = player_height;
        Color color = Color.BLACK;
        player = new Rect(xPos, yPos, PLAYER_WIDTH, PLAYER_HEIGHT, color);
        dx = x_speed;
        dy = y_speed;
    }

    /* Called to update, move the yPosition of the player 
    When the maximal height of the jump is reached, the y-direction is going to be reversed:
    the player falls back */
    public void update() {
        if(jumped == false) {
            player.move(dx,dy);
            if(player.getY() <= _maxTurnPoint) {
                dy = -dy;
            } else {
            }
            checkForGround();
            getPlayerBounds().setPosition(player.getX(), player.getY());
    }

    /* When the bottom of the player reaches the ground again, it stops falling down */
    private void checkForGround() {
        if (player.getBottomBorder() >= Configuration.GROUND_Y_POS) {
            dy = 0;
            jumped = false;
        }
    }

    /* Called to draw the player */
    public void draw() {
        player.draw();
    }


    /* controls if player jumps upwards */
    public void jump() {
        boolean jumped;
        if (!jumped) {
            Sound jumpSound = new Sound("/data/assets/jump.wav");
            jumpSound.play();
            jumpY = player.getBottomBorder();
            jumpY = jumpY - 1;
            jumped = true;
        }
        return jumped;
    }

    /* Called when player is falling into a pit to make it look like the player falls off the Canvas */
    public void fallsInPit() {
        double currentXPos = player.getX();
        double currentYPos = player.getY();
        currentYPos =  currentYPos + 10;
        player.setPosition(currentXPos, currentYPos);
        player.draw();
    }


    /* Creates a rect that represents the current movement of the player */
    public Rect getPlayerBounds() {
        Rect playerBounds = new Rect(player.getX(), player.getY(), player.getHeight(), player.getColor());
        return playerBounds;
    }
}

