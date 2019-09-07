package WorldObjects;

import Collision.Collidable;
import Collision.CollisionDetector;
import Collision.CollisionHandler;
import Coordinates.Cartesian;
import Coordinates.Coordinates;
import Enums.LevelEnum;
import Enums.MovementEnum;
import Utils.Vector;
import View.ViewManager;

import static Enums.MovementEnum.*;

/****
 * a player class
 */
public class Player implements Collidable {
    // members
    private static Vector pos;
    private Vector direction;
    private Vector up;
    private static Coordinates Coordinates;
    private Type type = Type.player;
    private static float step = 0.5f;
    private static int lives;
    private static boolean weaponUse = false;
    private static LevelEnum level;

    /****
     * Constructor
     */
    public Player(){
        Coordinates = new Cartesian();
        pos = new Vector(0f, 0.5f, 10f);
        up = getUp();
        direction = getDirection();
        lives = 2;
        level = LevelEnum.LEVEL_1;
        ViewManager manager = ViewManager.getInstance();
        manager.drawBar();
    }

    /**
     * returns the current level of the player
     * @return the current level as an int.
     */
    public static int getLevel() {
        return level.ordinal()+1;
    }

    /***
     * set the current level of the player
     * @param level the new level
     */
    public static void setLevel(LevelEnum level) {
        Player.level = level;
    }

    /****
     * returns the status of the player aliveness
     * @return true if the player is still alive, false otherwise
     */
    public static boolean isAlive() {
        if (lives > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /***
     * returns the number of lives
     * @return
     */
    public static int getLives() {
        return lives;
    }

    /****
     * decreases 1 life from the player
     */
    public static void decreaseLives() {
        lives = lives -1;
    }

    /****
     * returns the coordinates of the player
     * @return the coordinates of the player.
     */
    public Coordinates getCoordinates() {
        return Coordinates;
    }

    /****
     * returns the position point of the player.
     * @return a vector representing the player's location.
     */
    public static Vector getPos() {
        return pos;
    }

    /****
     * resets the coordinates of the player to starting coordinates.
     */
    public static void resetCoordinates() {
        Coordinates = new Cartesian();
    }

    /*****
     * returns the direction vector of the player.
     * @return a vector of direction.
     */
    public Vector getDirection() {
        Vector zAxis = Coordinates.getzAxis();
        direction = new Vector(zAxis.getX(), zAxis.getY(), zAxis.getZ());
        return direction;
    }

    /***
     * returns the up vector of the player.
     * @return the up vector.
     */
    public Vector getUp() {
        Vector yAxis = Coordinates.getyAxis();
        up = new Vector(yAxis.getX(), yAxis.getY(), yAxis.getZ());
        return up;
    }

    /***
     * returns the step of teh player.
     * @return the step rate of the player.
     */
    public float getStep() {
        return step;
    }

    /***
     * sets the current position of the player.
     * @param new_pos the new position.
     */
    public static void setPos(Vector new_pos) {
        pos = new_pos;
    }

    /****
     * sets the direction of the player.
     * @param direction the new direction vector.
     */
    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    /****
     * sets the up vector of the player.
     * @param up the new up vector.
     */
    public void setUp(Vector up) {
        this.up = up;
    }

    /*****
     * applies movement of 1 step to the player's location, towards to the direction vector.
     * @param direction
     */
    public void move(MovementEnum direction) {
        boolean itemsCollision, wallsCollision;
        Vector nextPos = checkNextPos(pos, direction);
        itemsCollision = CollisionDetector.checkPlayerItemsCollisions(nextPos);
        wallsCollision = CollisionDetector.point_walls(nextPos);
        //player touches boss
        if(World.getInstance().getBoss().getAABB_collision().checkInside(nextPos)){
            CollisionHandler.lose();
        }
        if (!wallsCollision && !itemsCollision) {
            switch(direction) {
                case FORWARD: // move forward
                    Coordinates.move(FORWARD, pos, step);
                    break;
                case BACKWARD: // move backward
                    Coordinates.move(BACKWARD, pos, step);
                    break;
                case RIGHT: // move right
                    Coordinates.move(RIGHT, pos, step);
                    break;
                case LEFT: // move left
                    Coordinates.move(LEFT, pos, step);
                    break;
                case UP: // move up
                    Coordinates.move(UP, pos, step);
                    break;
                case DOWN: // move down
                    Coordinates.move(DOWN, pos, step);
                    break;
            }
        }
    }

    /******
     * simulates the next move of the player in a given direction.
     * @param pos the current position of the player.
     * @param direction the direction of the wanted movement.
     * @return the next position.
     */
    private Vector checkNextPos(Vector pos, MovementEnum direction) {
        Vector nextPos = Coordinates.move(direction, new Vector(pos), step);
        return nextPos;
    }

    /****
     * applies a value to the weapon usage of the player.
     * @param use true or false;
     */
    public static void useWeapon(boolean use) {
        weaponUse = use;
    }

    /****
     * check if the weapon is in use.
     * @return true or false.
     */
    public static boolean isWeaponUsed() {
        return weaponUse;
    }
}
