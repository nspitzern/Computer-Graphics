package View;

import Scene.SceneBuilder;
import Scene.Sounds;
import WorldObjects.Player;
import com.jogamp.opengl.util.Animator;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Game {

    /***
     * Setting the game window
     */
    protected void game_window(){
        JFrame game_frame = new JFrame();
        ViewManager manager = ViewManager.getInstance();
        manager.setGameFrame(game_frame);

        //setting lives label
        JLabel label = new JLabel();
        label.setText("Level: 1, Lives: " + Player.getLives());
        label.setBounds(10,10,250,50);
        label.setForeground(Color.BLACK);
        label.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        label.setVisible(false);
        game_frame.add(label);
        manager.setLivesLabel(label);

        GLCanvas canvas = new GLCanvas();
        Animator animator = new Animator(canvas);
        manager.setAnimator(animator);
        canvas.addGLEventListener(new SceneBuilder());
        game_frame.add(canvas);
        game_frame.setUndecorated(true);
        game_frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        game_frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });

        Sounds.makeLoopSound("resources/sounds/game_music.wav");
        game_frame.validate();
        game_frame.setVisible(true);
        animator.start();
        canvas.requestFocus();
    }
}
