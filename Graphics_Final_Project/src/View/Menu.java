/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package View;

import Scene.Sounds;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    /****
     * opens the menu window in a new frame.
     * @param picPath the path to the background picture.
     * @param labelText the label text.
     */
    protected void menu_window(String picPath, String labelText) {
        JFrame frame = new JFrame();
        ViewManager manager = ViewManager.getInstance();
        manager.setMenuFrame(frame);

        frame.setSize(800, 600);

        //Create Image and set in the given frame
        Main.CreateImage.createImage(picPath, frame);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Game");
        frame.setResizable(false);

        //setting label
        JLabel label = new JLabel();
        label.setText(labelText);
        label.setBounds(125,100,550,50);
        label.setForeground(Color.BLACK);
        label.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
        frame.add(label);

        //setting button with icon
        ImageIcon icon = new ImageIcon("resources/pics/start.png");
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance( 150, 50,  java.awt.Image.SCALE_SMOOTH ) ;
        icon = new ImageIcon(newimg);
        JButton button = new JButton(icon);
        button.setBounds(325, 400, 150, 50);

        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    manager.startGame();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        removeButtonKeyBounds(button);

        frame.add(button);
        Sounds.makeLoopSound("resources/sounds/intro.wav");
        frame.validate();
        frame.setLayout(null);
        frame.setVisible(true);
    }

    /***
     * This function is meant to remove the "space" key from the button key bounds.
     * If not called - when pressing "space" in the boss level and winning the "start" button
     * may be clicked and we move directly to the game again.
     * @param button
     */
    private void removeButtonKeyBounds(JButton button) {
        Action blankAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
        ActionMap am = button.getActionMap();
        am.put("pressed", blankAction);
        am.put("released", blankAction);
    }

}
