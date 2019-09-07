package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args){
        ViewManager manager = ViewManager.getInstance();
        manager.mainMenu();
    }

    /***
     * In charge of drawing the image
     */
    public static class ImagePanel extends JComponent {
        private Image image;
        private int width;
        private int height;

        protected ImagePanel(Image image, int width, int height) {
            this.image = image;
            this.width = width;
            this.height = height;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0,width, height, this);
        }
    }

    /***
     * Creating the image based on the given path.
     */
    public static class CreateImage{
        protected static  BufferedImage createImage(String path, JFrame frame){
            BufferedImage myImage = null;
            try {
                myImage = ImageIO.read(new File(path));
                frame.setContentPane(new ImagePanel(myImage, frame.getWidth(), frame.getHeight()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return myImage;
        }
    }
}
