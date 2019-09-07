package WorldObjects;

import Collision.Collidable;
import Enums.MovementEnum;
import Scene.Sounds;
import Utils.Vector;
import View.ViewManager;

import javax.media.opengl.GL2;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/***
 * singleton
 */
public class World {
    // members
    private static World world;
    private ArrayList<Cube> itemsList;
    private ArrayList<BlockWall> walls;
    private ArrayList<BreakableCube> breakWall;
    private ArrayList<Bullet> bullets;
    private Boss boss;

    /**
     * Returns the classes instance.
     * @return the classes current instance
     */
    public static World getInstance(){
        if(world == null) {
            world = new World();
        }
        return world;
    }

    /**
     * Constructor
     */
    private World() {
        itemsList = new ArrayList<>();
        walls = new ArrayList<>();
        breakWall = new ArrayList<>();
        bullets = new ArrayList<>();
        createLevel1();
        createLevel2();
    }

    /****
     * Resets the world
     */
    public void resetWorld(){
        itemsList = new ArrayList<>();
        walls = new ArrayList<>();
        breakWall = new ArrayList<>();
        bullets = new ArrayList<>();
        createLevel1();
        createLevel2();
    }

    /****
     * goes through all the items in the lists of the world and
     * draws them.
     * @param gl the gl
     */
    public void draw(GL2 gl) {

        addLightLevel(gl);

        float mat_ambient[] = {0.7f, 0.7f, 0.7f, 1.0f};
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);

        this.boss.draw(gl);

        for (BlockWall w : walls) {
            w.draw(gl);
        }

        for (BreakableCube b : breakWall) {
            b.draw(gl);
        }

        for (int i = 0; i<itemsList.size(); i++){
            itemsList.get(i).draw(gl);
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(gl);
        }

        ViewManager manager = ViewManager.getInstance();
        if (!Player.isAlive()) {
            manager.lose();
        }

    }

    /****
     * initializes the items and space of level 1
     */
    private void createLevel1() {
        createWallsLevel1();
        createItemsLevel1();
    }

    /******
     * creates the walls, floor and ceiling of the first level
     */
    private void createWallsLevel1() {
        // floor
        walls.add(new BlockWall(new Vector(-11f,-2f,25f),
                22,1,100,
                "resources/pics/floor.jpeg",
                Collidable.Type.stay));
        // ceiling
        walls.add(new BlockWall(new Vector(-11f,10f,25f),
                22,1,100,
                "resources/pics/cave.jpg",
                Collidable.Type.stay));
        // front wall
        walls.add(new BlockWall(new Vector(-11,-2,25),
                22,13,1,
                "resources/pics/steel-box.jpg",
                Collidable.Type.stay));
        // back wall
        walls.add(new BlockWall(new Vector(-11,-2,-75),
                22,13,1,
                "resources/pics/steel-box.jpg",
                Collidable.Type.stay));
        // right wall
        walls.add(new BlockWall(new Vector(10,-2,25),
                1,13,90,
                "resources/pics/steel-box.jpg",
                Collidable.Type.stay));
        // left wall
        walls.add(new BlockWall(new Vector(-11,-2,25),
                1,13,100,
                "resources/pics/steel-box.jpg",
                Collidable.Type.stay));

        // after turn
        // floor
        walls.add(new BlockWall(new Vector(10f,-2f,-65f),
                40,1,20,
                "resources/pics/floor.jpeg",
                Collidable.Type.stay));
        // ceiling
        walls.add(new BlockWall(new Vector(10f,11f,-65f),
                40,1,10,
                "resources/pics/cave.jpg",
                Collidable.Type.stay));
        // back wall
        walls.add(new BlockWall(new Vector(50,-2,-65),
                1,13,11,
                "resources/pics/portal.jpg",
                Collidable.Type.portal));
        // right wall
        walls.add(new BlockWall(new Vector(10,-2,-65),
                40,13,1,
                "resources/pics/steel-box.jpg",
                Collidable.Type.stay));
        // left wall
        walls.add(new BlockWall(new Vector(10,-2,-75),
                40,13,1,
                "resources/pics/steel-box.jpg",
                Collidable.Type.stay));
    }

    /*****
     * creates the items of the first level
     */
    private void createItemsLevel1() {
        Cube tnt1 = new Cube(new Vector(-9,-1,1),2,"resources/pics/tnt.jpg", Collidable.Type.tnt);
        Cube tnt2 = new Cube(new Vector(-1,-1,1),2,"resources/pics/tnt.jpg", Collidable.Type.tnt);
        Cube tnt3 = new Cube(new Vector(7,-1,1),2,"resources/pics/tnt.jpg", Collidable.Type.tnt);

        MovingCube movingCube1 = new MovingCube(new Vector(-9, -1, -30), 2,
                "resources/pics/moving_box.png", Collidable.Type.stay, 0.1f, MovementEnum.RIGHT);
        MovingCube movingCube2 = new MovingCube(new Vector(7, -1, -30), 2,
                "resources/pics/moving_box.png", Collidable.Type.stay, -0.1f, MovementEnum.LEFT);
        MovingCube movingCube3 = new MovingCube(new Vector(-9, -1, -20), 2,
                "resources/pics/moving_box.png", Collidable.Type.stay, 0.1f, MovementEnum.RIGHT);
        MovingCube movingCube4 = new MovingCube(new Vector(7, -1, -10), 2,
                "resources/pics/moving_box.png", Collidable.Type.stay, -0.1f, MovementEnum.LEFT);

        itemsList.add(tnt1);
        itemsList.add(tnt2);
        itemsList.add(tnt3);
        itemsList.add(movingCube1);
        itemsList.add(movingCube2);
        itemsList.add(movingCube3);
        itemsList.add(movingCube4);
        for (int i = -10; i < 10; i+=2) {
            itemsList.add(new MovingCube(new Vector(i, 7, -45), 2,
                    "resources/pics/moving_box.png", Collidable.Type.stay, 0.05f, MovementEnum.DOWN));
        }

        Vector p;
        for (int z = -65; z > -75; z-=2) {
            for (int y = -1; y < 9; y+=2) {
                p = new Vector(10, y, z);
                itemsList.add(new BreakableCube(p, 2, 3, "resources/pics/box.jpg", Collidable.Type.breakable));
            }
        }
    }

    /****
     * initializes the items and space of level 2
     */
    private void createLevel2() {
        createWallsLevel2();
        this.boss = new Boss(new Vector(0, -0.9f, -170),
                "resources/obj/fire_guy/fire_guy.obj",
                "resources/obj/fire_guy/fire_guy_texture.png",
                  Collidable.Type.boss);
    }

    /******
     * creates the walls, floor and ceiling of the second level
     */
    private void createWallsLevel2() {
        // floor
        walls.add(new BlockWall(new Vector(-75,-2f,-80f),
                150,1,140,
                "resources/pics/floor2.jpg",
                Collidable.Type.stay));
        // ceiling
        walls.add(new BlockWall(new Vector(-75,70,-80f),
                150,1,140,
                "resources/pics/stars.jpg",
                Collidable.Type.stay));
        // front wall
        walls.add(new BlockWall(new Vector(-75,-2,-80f),
                150,72,1,
                "resources/pics/wall3.jpg",
                Collidable.Type.stay));
        // back wall
        walls.add(new BlockWall(new Vector(-75,-2,-220),
                150,72,1,
                "resources/pics/wall3.jpg",
                Collidable.Type.stay));
        // right wall
        walls.add(new BlockWall(new Vector(74,-2,-80),
                1,72,140,
                "resources/pics/wall3.jpg",
                Collidable.Type.stay));
        // left wall
        walls.add(new BlockWall(new Vector(-74,-2,-80),
                1,72,140,
                "resources/pics/wall3.jpg",
                Collidable.Type.stay));

    }

    /****
     * Adds lighting to the world.
     * contains diffuse, ambient and specular lighting.
     * @param gl the gl
     */
    private void addLightLevel(GL2 gl) {

        // define diffusive purple light
        float diffuse0[] = {0.2f, 0f, 0.2f,1.0f};
        float position0[] = {0f, 3f, 3f, 1.0f};
        float direction0[] = {0f, -1f, -1f, 1.0f};
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position0, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse0, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, diffuse0, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPOT_DIRECTION, direction0, 0);

        // define specular red light
        float specular1[] = {0.6f, 0.0f, 0.0f, 1.0f};
        float position1[] = {0.0f, 0.5f, 2f, 1.0f};
        float direction1[] = {0.0f, 0.0f, -1.0f, 1.0f};
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, specular1, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPOT_DIRECTION, direction1, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position1, 0);

        // define ambient white light
        float ambient2[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float position2[] = {0f, 200f, 0f, 1.0f};
        float direction2[] = {0f, -1f, 0f, 1.0f};
        gl.glEnable(GL2.GL_LIGHT2);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_AMBIENT, ambient2, 0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_POSITION, position2, 0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPOT_DIRECTION, direction2, 0);

        gl.glEnable(GL2.GL_NORMALIZE);
    }

    /****
     * returns the list of items in the world
     * @return list of items
     */
    public ArrayList<Cube> getItemsList() {
        return itemsList;
    }

    /****
     * removes an item from the items list
     * @param c the removed item
     */
    public void removeFromList(Cube c) {
        itemsList.remove(c);
    }

    /***
     * returns a list of walls
     * @return a list of walls
     */
    public ArrayList<BlockWall> getWalls() {
        return walls;
    }

    /****
     * returns the boss
     * @return the boss
     */
    public Boss getBoss(){
        return this.boss;
    }

    /***
     * teleports the player to the second level
     */
    public void moveToLevel2() {
        Sounds.emptySounds();
        Player.setPos(new Vector(0f, 4f, -85f));
        Player.resetCoordinates();
        Sounds.makeLoopSound("resources/sounds/evil_laugh.wav");
    }

    /****
     * creates a new bullet and adds it to the bullets list
     * @param dir the direction of the bullet
     */
    public void createBullet(Vector dir) {
        bullets.add(new Bullet(new Vector(Player.getPos()),
                dir, 0.4f, 0.4f, 0.4f,
                "resources/pics/stars.jpg", Collidable.Type.bullet));
    }

    /****
     * removes a bullet from the bullets list
     * @param bullet the removed bullet
     */
    public void deleteBullet(Bullet bullet){
        this.bullets.remove(bullet);
    }

    /*****
     * Clears the bullets list
     */
    public void resetBulletsList(){
        bullets.clear();
    }

}
