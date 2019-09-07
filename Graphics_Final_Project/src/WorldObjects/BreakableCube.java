package WorldObjects;

import Utils.Vector;
import javax.media.opengl.GL2;

public class BreakableCube extends Cube{
    // members
    private int hp;

    /****
     * Constructor
     * @param v the position of the cube.
     * @param l the length of the cube.
     * @param lives the lives of the cube.
     */
    public BreakableCube(Vector v, float l, int lives) {
        this(v,l,lives,null,null);
    }

    /****
     * Constructor
     * @param v the position of the cube.
     * @param l the length of the cube.
     * @param lives the lives of the cube.
     * @param texturePath teh texture of the cube.
     * @param t teh type of teh cube.
     */
    public BreakableCube(Vector v, float l, int lives, String texturePath, Type t) {
        super(v, l, texturePath, t);
        this.hp = lives;
    }

    /****
     * returns the hit points of the cube.
     * @return number of hit points.
     */
    public int getHp() {
        return this.hp;
    }

    /***
     * reduces 1 hit point from the cube.
     */
    public void decreaseHp() {
        this.hp = this.hp - 1;
    }

    /***
     * draws the cube.
     * @param gl the gl.
     */
    public void draw(GL2 gl) {
        super.draw(gl);
    }
}
