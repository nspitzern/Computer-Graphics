package Scene;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args){
        int margin = 20;
        Frame myFrame = new Frame("Exercise1");
        MyCanvas myCanvas = new MyCanvas(margin);
        myFrame.add(myCanvas);

        WindowAdapter myWindowAdapter = new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        myFrame.addWindowListener(myWindowAdapter);
        myFrame.pack();
        myFrame.setVisible(true);
        myCanvas.requestFocus();
    }
}
