package WorldObjects;

import Collision.Collidable;
import Utils.Vector;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;

public class Cube extends BlockWall implements Collidable{
    // members
    protected Texture cubeTexture;
    protected Vector o; //left bottom corner close to view
    protected float length;
    protected Type type;
    private Vector[] arr = new Vector[8];

    /*****
     * Constructor
     * @param v the position of the cube.
     * @param l the length of the cube.
     */
    public Cube(Vector v, float l) {
        this(v, l , null, null);
    }

    /*****
     * Constructor
     * @param v the position of the cube
     * @param l the length of the cube
     * @param texturePath the texture of the cube
     * @param t the type of teh cube
     */
    public Cube(Vector v, float l, String texturePath, Type t) {
        super(v, l, l, l, texturePath, t);
        try {
            type = t;
            o = v;
            length = l;
            String cubeTextureFile = texturePath; // the FileName to open
            if (cubeTextureFile != null) {
                cubeTexture = TextureIO.newTexture(new File(cubeTextureFile), true);
            }
            arr[0] = new Vector(o.getX(), o.getY(), o.getZ());
            arr[1] = new Vector(o.getX()+length, o.getY(), o.getZ());
            arr[2] = new Vector(o.getX()+length, o.getY()+length, o.getZ());
            arr[3] = new Vector(o.getX(), o.getY()+length, o.getZ());
            arr[4] = new Vector(o.getX(), o.getY(), o.getZ()-length);
            arr[5] = new Vector(o.getX(), o.getY()+length, o.getZ()-length);
            arr[6] = new Vector(o.getX()+length, o.getY()+length, o.getZ()-length);
            arr[7] = new Vector(o.getX()+length, o.getY(), o.getZ()-length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /****
     * returns the cube type.
     * @return the cube type.
     */
    public Type getType() {
        return type;
    }

    /****
     * draw the cube.
     * @param gl the gl
     */
    public void draw(GL2 gl) {
        if(Player.isAlive()) {
            super.draw(gl);
        }
    }

    /****
     * returns the origin.
     * @return the origion point as a vector.
     */
    public Vector getOrigin() {
        return o;
    }

    /****
     * returns the length of the cube.
     * @return the length of the cube.
     */
    public float getLength() {
        return this.length;
    }

    /****
     * returns an array of the vertices of the cube.
     * @return Vectors array.
     */
    public Vector[] getVertexes() {
        return this.arr;
    }

}
