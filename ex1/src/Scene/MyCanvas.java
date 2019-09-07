/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Scene;

import Utils.*;
import Utils.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyCanvas extends Canvas implements MouseListener, MouseMotionListener, KeyListener {
    // members
    private static final long serialVersionUID = 1L;
    private String viwFilePath = "ex1/src/Resources/example.viw";
    private String scnFilePath = "ex1/src/Resources/example.scn";

    private List<Vertex> vertexList;
    private List<Edge> edgeList;
    private Matrix currentTrans;
    private Matrix totalTrans;
    private Vector cameraLocation;
    private Vector lookAtPoint;
    private Vector upVector;
    private Matrix MV1;
    private Matrix MV2;
    private Matrix projectionMat;
    private double[] window;
    private List<Vertex> bordersVertices = new ArrayList<>();
    private List<Edge> borderEdges = new ArrayList<>();
    private int margin;
    private int viewWidth;
    private int viewHeight;
    private double[] clickedPoint = new double[2];
    private boolean isRotate = false;
    private boolean isScale = false;
    private boolean isTranslate = false;
    private boolean clipping = false;
    private String rotateAxis = "Z";

    public MyCanvas(int margin) {
        // add 4 border points
        this.margin = margin;
        readScnFile();
        resetDisplay();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                int height = e.getComponent().getHeight();
                viewHeight = height;
                viewWidth = width;
                setSize(width, height);
                addBorders(width, height);
                MV2 = Scene.calculateMV2(width, height, margin, window[0], window[1], window[2], window[3]);
                repaint();
            }
        });

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    /*****
     * the function adds the borders to the window
     * @param width the width of the view window
     * @param height the height of the view window
     */
    private void addBorders(double width, double height) {
        this.bordersVertices.clear();
        // up left
        this.bordersVertices.add(new Vertex(this.margin, this.margin, 0));
        // up right
        this.bordersVertices.add(new Vertex(width - this.margin, this.margin, 0));
        // down right
        this.bordersVertices.add(new Vertex(width - this.margin, height - this.margin, 0));
        // down left
        this.bordersVertices.add(new Vertex(this.margin, height - this.margin, 0));
        // add 4 border edges
        this.borderEdges.add(new Edge(0, 1));
        this.borderEdges.add(new Edge(2, 1));
        this.borderEdges.add(new Edge(3, 2));
        this.borderEdges.add(new Edge(0, 3));
    }

    /*****
     * initialize all parameters in the viw file
     * (cameraLocation, lookAtPoint, upVector, window, height and width)
     */
    private void readViwFile() {
        List[] lst2 = Parsing.parse_viw(this.viwFilePath);
        List<Double> temp0 = lst2[0];
        this.cameraLocation = new Vector(temp0.get(0), temp0.get(1), temp0.get(2));
        List<Double> temp1 = lst2[1];
        this.lookAtPoint = new Vector(temp1.get(0), temp1.get(1), temp1.get(2));
        List<Double> temp2 = lst2[2];
        this.upVector = new Vector(temp2.get(0), temp2.get(1), temp2.get(2));
        List<Double> temp3 = lst2[3];
        this.window = new double[temp3.size()];
        for (int i = 0; i < temp3.size(); i++) {
            this.window[i] = temp3.get(i);
        }
        List<Double> temp4 = lst2[4];
        this.viewWidth = temp4.get(0).intValue() + 2 * this.margin;
        this.viewHeight = temp4.get(1).intValue() + 2 * this.margin;
    }

    /*****
     * the function initializes the vertex list and the edge list
     * according to the scn file
     */
    private void readScnFile() {
        List[] lst1 = Parsing.parse_scn(this.scnFilePath);
        this.vertexList = lst1[0];
        this.edgeList = lst1[1];
    }

    public void paint(Graphics g) {

        // calculate the new vertices
        ArrayList<Vertex> newVL = new ArrayList<>();
        for (Vertex v : this.vertexList) {
            Matrix newV = this.MV2
                    .mult(this.projectionMat)
                    .mult(this.currentTrans)
                    .mult(this.totalTrans)
                    .mult(this.MV1);
            newVL.add(newV.rightMult(v));
        }

        // draw the edges of the vertices (check for clipping)
        for (Edge e : this.edgeList) {
            Vertex v1 = newVL.get(e.getV1());
            Vertex v2 = newVL.get(e.getV2());
            // check if the clipping toggle is on
            if (this.clipping) {
                Line l = Clipping.clipLine(v1.getVector(), v2.getVector(), this.bordersVertices);
                if (l != null && l.getStart() != null && l.getEnd() != null) {
                    v1 = new Vertex(l.getStart().getX(), l.getStart().getY(), 0);
                    v2 = new Vertex(l.getEnd().getX(), l.getEnd().getY(), 0);
                    g.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
                }
            } else {
                g.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
            }
        }

        // draw the borders
        for (Edge e : this.borderEdges) {
            Vertex v1 = this.bordersVertices.get(e.getV1());
            Vertex v2 = this.bordersVertices.get(e.getV2());
            g.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX(), y = e.getY();
        this.clickedPoint[0] = x;
        this.clickedPoint[1] = y;
        // rotate
        if (x < this.viewWidth / 3 && y < this.viewHeight / 3) {
            changeTransformationIndicatiors("Rotate");
        } else if (x < this.viewWidth / 3 && y > (2 * this.viewHeight) / 3) {
            changeTransformationIndicatiors("Rotate");
        } else if (x > (2 * this.viewWidth) / 3 && y < this.viewHeight / 3) {
            changeTransformationIndicatiors("Rotate");
        } else if (x > (2 * this.viewWidth) / 3 && y > (2 * this.viewHeight / 3)) {
            changeTransformationIndicatiors("Rotate");
            // scale
        } else if (x > this.viewWidth / 3 && x < (2 * this.viewWidth) / 3 && y > (2 * this.viewHeight) / 3) {
            changeTransformationIndicatiors("Scale");
        } else if (x > this.viewWidth / 3 && x < (2 * this.viewWidth) / 3 && y < this.viewHeight / 3) {
            changeTransformationIndicatiors("Scale");
        } else if (x < this.viewWidth / 3 && y > this.viewWidth / 3 && y < (2 * this.viewHeight) / 3) {
            changeTransformationIndicatiors("Scale");
        } else if (x > (2 * this.viewWidth) / 3 && y > this.viewWidth / 3 && y < (2 * this.viewHeight) / 3) {
            changeTransformationIndicatiors("Scale");
            // translate
        } else {
            changeTransformationIndicatiors("Translate");
        }
    }

    /****
     * the function changes the transform indicators according to the string.
     * the string is received according to the pressed board area.
     * @param trans the string
     */
    private void changeTransformationIndicatiors(String trans) {
        switch (trans) {
            case "Rotate":
                this.isScale = false;
                this.isTranslate = false;
                this.isRotate = true;
                break;
            case "Translate":
                this.isScale = false;
                this.isRotate = false;
                this.isTranslate = true;
                break;
            case "Scale":
                this.isTranslate = false;
                this.isRotate = false;
                this.isScale = true;
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // calculate the total transform
        this.totalTrans = this.currentTrans.mult(this.totalTrans);
        // reset the current transform
        this.currentTrans = MatrixFactory.createIdentityMatrix(4);
        // reset the transform indicators
        this.isTranslate = false;
        this.isRotate = false;
        this.isScale = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        double x = e.getX(), y = e.getY();
        // translate
        if (this.isTranslate) {
            this.currentTrans = Scene.calculateTranslateTransform(x, y, this.clickedPoint, this.window,
                    this.viewHeight, this.viewWidth);
            // scale
        } else if (this.isScale) {
            Vector LP = this.lookAtPoint.sub(this.cameraLocation);
            this.currentTrans = Scene.calculateScaleTransform(x, y, this.clickedPoint,
                    this.viewWidth / 2, this.viewHeight / 2, LP);
            // rotate
        } else if (this.isRotate) {
            Vector LP = this.lookAtPoint.sub(this.cameraLocation);
            this.currentTrans = Scene.calculateRotateTransform(this.rotateAxis, x, y, this.clickedPoint,
                    this.viewWidth / 2, this.viewHeight / 2, LP);
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /*****
     * the function resets the display according to the scn and viw files
     */
    private void resetDisplay() {
        double difHeight = 0;
        double difWidth = 0;
        if(this.getParent()!=null) {
            difHeight = this.getParent().getHeight() - this.getHeight();
            difWidth = this.getParent().getWidth() - this.getWidth();
        }
        readViwFile();
        this.rotateAxis = "Z";
        this.projectionMat = MatrixFactory.createOrthographicProjctionMatrix(4, 4);
        this.MV2 = Scene.calculateMV2(this.viewWidth, this.viewHeight, this.margin, this.window[0], this.window[1], this.window[2], this.window[3]);
        this.currentTrans = MatrixFactory.createIdentityMatrix(4);
        this.totalTrans = MatrixFactory.createIdentityMatrix(4);
        this.MV1 = Scene.calculateMV1(this.cameraLocation, this.lookAtPoint, this.upVector);
        this.setSize(this.viewWidth, this.viewHeight);
        if(this.getParent()!=null){
            this.getParent().setSize(this.viewWidth+(int)difWidth, this.viewHeight+(int)difHeight);
        }
        addBorders(this.viewWidth, this.viewHeight);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (String.valueOf(e.getKeyChar()).toUpperCase()) {
            case "L":
                loadFile();
                break;
            case "Z":
                this.rotateAxis = "Z";
                break;
            case "Y":
                this.rotateAxis = "Y";
                break;
            case "X":
                this.rotateAxis = "X";
                break;
            case "R":
                resetDisplay();
                break;
            case "C":
                this.clipping = this.clipping == true ? false : true;
                repaint();
                break;
            case "Q":
                System.exit(0);
                break;
        }
    }

    /*****
     * the function loads a new file and changes the display accordingly
     */
    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser("computer-graphics/ex1/src/Resources");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // check file type
            try {
                if (selectedFile.getName().contains(".scn")) {
                    // create new scn
                    this.scnFilePath = selectedFile.getAbsolutePath();
                    readScnFile();
                    resetDisplay();
                } else if (selectedFile.getName().contains(".viw")) {
                    // create new viw
                    this.viwFilePath = selectedFile.getAbsolutePath();
                    resetDisplay();
                } else {
                    throw new Exception("Error in file type");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
