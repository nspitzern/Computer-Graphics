/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Scene;

import Collision.CollisionDetector;
import Collision.CollisionHandler;
import Enums.LevelEnum;
import Enums.MovementEnum;
import Enums.SteerEnum;
import View.ViewManager;
import WorldObjects.*;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.glu.GLU;
import Utils.Vector;

public class SceneBuilder extends KeyAdapter implements GLEventListener {

    private static GLU glu = new GLU();
    private World world;
    private Player player;
    private float alpha = (float)Math.toRadians(5);

    public void display(GLAutoDrawable gLDrawable) {
        Vector direction = player.getDirection();
        Vector up = player.getUp();
        Vector pos = Player.getPos();

        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();  // Reset The View
        glu.gluLookAt(pos.getX(), pos.getY(), pos.getZ(),
                pos.getX() - direction.getX(),
                pos.getY() - direction.getY(),
                pos.getZ() - direction.getZ(),
                up.getX(),
                up.getY(),
                up.getZ());

        gl.glColor4f(1f, 1f, 1f, 1f); //NEEDS to be white before drawing, else stuff will tint.
        gl.glTexParameteri ( GL2.GL_TEXTURE_2D,GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT );
        gl.glTexParameteri( GL2.GL_TEXTURE_2D,GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT );

        world.draw(gl);

    }

    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
        gl.glEnable(GL2.GL_DEPTH_TEST);              // Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL);               // The Type Of Depth Testing To Do
        // Really Nice Perspective Calculations
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

        player = new Player();
        world  = World.getInstance();
        world.resetWorld();

        gl.glEnable(GL2.GL_LIGHTING);
        if (drawable instanceof Window) {
            Window window = (Window) drawable;
            window.addKeyListener(this);
        } else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) drawable;
            new AWTKeyAdapter(this, drawable).addTo(comp);
        }
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
    }

    public void reshape(GLAutoDrawable drawable, int x,
                        int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        if(height <= 0) {
            height = 1;
        }
        float h = (float)width / (float)height;
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'i': // rotate up (x axis)
                player.getCoordinates().rotate(SteerEnum.UP_X, -alpha);
                break;
            case 'k': // rotate down (x axis)
                player.getCoordinates().rotate(SteerEnum.DOWN_X, alpha);
                break;
            case 'l': // rotate right (y axis)
                player.getCoordinates().rotate(SteerEnum.RIGHT_Y, -alpha);
                break;
            case 'j': // rotate left (y axis)
                player.getCoordinates().rotate(SteerEnum.LEFT_Y, alpha);
                break;
            case 'o': // rotate right (z axis)
                player.getCoordinates().rotate(SteerEnum.RIGHT_Z, alpha);
                break;
            case 'u': // rotate left (z axis)
                player.getCoordinates().rotate(SteerEnum.LEFT_Z, -alpha);
                break;
            case 'w': // move forward
                player.move(MovementEnum.FORWARD);
                break;
            case 's': // move backward
                player.move(MovementEnum.BACKWARD);
                break;
            case 'd': // move right
                player.move(MovementEnum.RIGHT);
                break;
            case 'a': // move left
                player.move(MovementEnum.LEFT);
                break;
            case 'e': // move up
                player.move(MovementEnum.UP);
                break;
            case 'q': // move down
                player.move(MovementEnum.DOWN);
                break;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_F1) {
            ViewManager manager = ViewManager.getInstance();
            manager.showInstructions();
        }
        else if(e.getKeyCode() == KeyEvent.VK_F2) {
            CollisionHandler.nextLevel();
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (Player.getLevel() == LevelEnum.LEVEL_1.ordinal() + 1) {
                Player.useWeapon(true);
                Vector nextPos = new Vector(Player.getPos());
                nextPos.setX(Player.getPos().getX() + 3f);
                CollisionDetector.checkPlayerItemsCollisions(nextPos);
                Player.useWeapon(false);
            } else {
                world.createBullet(player.getDirection());
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}
