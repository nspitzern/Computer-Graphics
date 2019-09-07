package WorldObjects;

import Collision.Collidable;
import Collision.CollisionDetector;
import Collision.CollisionHandler;
import Enums.MovementEnum;
import Utils.Vector;
import javax.media.opengl.GL2;
import java.util.List;

public class MovingCube extends Cube implements Collidable {
    // members
    private float step;
    private MovementEnum direction;

    /****
     * Constructor
     * @param v position vector
     * @param l length of the cube
     * @param texturePath texture path
     * @param t the cube type
     * @param s step rate
     * @param dir movement direction
     */
    public MovingCube(Vector v, float l, String texturePath, Type t, float s, MovementEnum dir) {
        super(v, l, texturePath, t);
        this.step = s;
        this.direction = dir;
    }

    /****
     * draws the cube.
     * the cube has a material of diffuse and specular
     * @param gl the gl
     */
    public void draw(GL2 gl) {
        float high_shininess[] = {80.0f};
        float mat_specular[] = {0.8f, 0.2f, 0.2f, 1.0f};
        float mat_diffuse[] = {0.2f, 0.0f, 0.0f, 1.0f};
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, high_shininess, 0);

        this.step = moveCube(this.direction);
        super.draw(gl);
    }


    /****
     * apply 1 step of movement to the cube in the given direction.
     * @param direction the required direction.
     * @return the new step value.
     */
    public float moveCube(MovementEnum direction) {
        boolean collide = false;
        Vector nextPos = checkNextPos(step, super.getOrigin(), direction);
        // check collision with player
        collide = CollisionDetector.point_cube(Player.getPos(), new Cube(nextPos, length));
        if(collide) {
            World.getInstance().removeFromList(this);
            CollisionHandler.lose();
            return step;
        }

        collisionWithBoxes();
        if(CollisionDetector.AABB_walls(this)){
            changeStep();
        }

        switch (direction) {
            case DOWN:
            case UP:
                o.setY(o.getY()+step);
                break;
            case LEFT:
            case RIGHT:
                o.setX(o.getX()+step);
                break;
            case FORWARD:
            case BACKWARD:
                o.setZ(o.getZ()+step);
                break;
        }
        return step;
    }

    /****
     * check collision with other boxes.
     * change direction on collision.
     */
    private void collisionWithBoxes() {
        boolean collide;
        List<Cube> arr = World.getInstance().getItemsList();
        for (Cube c : arr) {
            if (c == this) { continue;}
            collide = CollisionDetector.AABB_AABB(this, c);
            if (collide) {
                this.changeStep();
                if (c instanceof MovingCube) {
                    ((MovingCube) c).changeStep();
                }
            }
        }
    }

    /****
     * simulate the next position of the cube.
     * @param step the step rate.
     * @param pos the current position of the cube.
     * @param direction the direction of the cube.
     * @return the new position of the cube.
     */
    private Vector checkNextPos(float step, Vector pos, MovementEnum direction) {
        Vector newPos = new Vector(pos);
        switch (direction) {
            case FORWARD:
                newPos.setZ(newPos.getZ() - step);
                break;
            case BACKWARD:
                newPos.setZ(newPos.getZ() + step);
                break;
            case UP:
                newPos.setY(newPos.getY() + step);
                break;
            case DOWN:
                newPos.setY(newPos.getY() - step);
                break;
            case RIGHT:
                newPos.setX(newPos.getX() + step);
                break;
            case LEFT:
                newPos.setX(newPos.getX() - step);
                break;
        }
        return newPos;
    }

    /****
     * change the step of the cube.
     */
    public void changeStep() {
        this.step = this.step * -1;
    }
}
