/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package View;
import javax.swing.*;

public class Instructions {

    /****
     * opens the instruction window in a new frame.
     */
    protected void instructions(){
        JFrame frame = new JFrame();
        frame.setSize(1091, 719);

        //Create Image and set in the given frame
        Main.CreateImage.createImage("resources/pics/instructions.png", frame);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Instructions");
        frame.setResizable(false);
        frame.validate();
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
