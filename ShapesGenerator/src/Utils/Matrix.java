package Utils;

import java.util.concurrent.RecursiveTask;

public class Matrix {
    // members
    private double[][] values;
    private int rows, cols;

    // Constructor
    public Matrix(int rows, int cols) {
        this.values = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public Matrix(double[][] arr) {
        this.rows = arr.length;
        this.cols = arr[0].length;
        this.values = arr;
    }

    public Matrix(Vector v) {
        this.values = new double[4][1];
        this.rows = 4;
        this.cols = 1;
        this.values[0][0] = v.getX();
        this.values[1][0] = v.getY();
        this.values[2][0] = v.getZ();
        this.values[3][0] = 1;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public double getValue(int row, int col) {
        return this.values[row][col];
    }

    public void setValue(int i, int j, double value) {
        this.values[i][j] = value;
    }

    public Matrix mult(Matrix mat2) {
        int r1 = this.rows;
        int r2 = mat2.rows;
        int c1 = this.cols;
        int c2 = mat2.cols;
        if(c1!=r2) {
            System.out.println("ERROR. trying to multiply wrong sizes");
            return null;
        }
        Matrix newMat = new Matrix(r1, c2);
        newMat.zeros(r1, c2);
        double v;
        for(int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    v = newMat.getValue(i, j);
                    newMat.setValue(i, j, v + this.values[i][k] * mat2.values[k][j]);
                }
            }
        }
        return newMat;
    }

    public Vertex rightMult(Vertex v) {
        double vx, vy, vz, vw;
        vx = v.getX() * this.values[0][0] + v.getY() * this.values[0][1] + v.getZ() * this.values[0][2] + v.getW() * this.values[0][3];
        vy = v.getX() * this.values[1][0] + v.getY() * this.values[1][1] + v.getZ() * this.values[1][2] + v.getW() * this.values[1][3];
        vz = v.getX() * this.values[2][0] + v.getY() * this.values[2][1] + v.getZ() * this.values[2][2] + v.getW() * this.values[2][3];
        vw = v.getX() * this.values[3][0] + v.getY() * this.values[3][1] + v.getZ() * this.values[3][2] + v.getW() * this.values[3][3];
        return new Vertex(new double[] {vx, vy, vz, vw});
    }

    public Vertex leftMult(Vertex v) {
        double vx, vy, vz, vw;
        Vertex v2;
        vx = v.getX() * this.values[0][0] + v.getY() * this.values[1][0] + v.getZ() * this.values[2][0] + v.getW() * this.values[3][0];
        vy = v.getX() * this.values[0][1] + v.getY() * this.values[1][1] + v.getZ() * this.values[2][1] + v.getW() * this.values[3][1];
        vz = v.getX() * this.values[0][2] + v.getY() * this.values[1][2] + v.getZ() * this.values[2][2] + v.getW() * this.values[3][2];
        vw = v.getX() * this.values[0][3] + v.getY() * this.values[1][3] + v.getZ() * this.values[2][3] + v.getW() * this.values[3][3];
        v2 = new Vertex(new double[] {vx, vy, vz, vw});
        return v2;
    }

    /****
     * Initialize to identity matrix
     */
    public void resetMatrix() {
        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if(i == j) {
                    this.values[i][j] = 1;
                } else {
                    this.values[i][j] = 0;
                }
            }
        }
    }

    public void zeros(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.values[i][j] = 0;
            }
        }
    }

    public boolean equals(Matrix matrix) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.values[i][j] != matrix.values[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix transpose() {
        Matrix matrix = MatrixFactory.createZerosMatrix(this.cols, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                matrix.setValue(j, i, this.values[i][j]);
            }
        }
        return matrix;
    }

    public void print() {
        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.values[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
