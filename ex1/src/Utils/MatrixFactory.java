/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Utils;

public class MatrixFactory {

    public static Matrix createIdentityMatrix(int dim) {
        Matrix mat = new Matrix(dim, dim);
        mat.resetMatrix();
        return mat;
    }

    public static Matrix createZerosMatrix(int rows, int cols) {
        Matrix mat = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat.setValue(i, j, 0);
            }
        }
        return mat;
    }

    public static Matrix createMV1Matrix(Vector cameraLocation, Vector Zv, Vector Xv, Vector Yv) {
        Matrix mat1 = createIdentityMatrix(4);
        Matrix mat2 = createIdentityMatrix(4);
        // assign values to the matrix
        mat2.setValue(0, 3, -cameraLocation.getX());
        mat2.setValue(1, 3, -cameraLocation.getY());
        mat2.setValue(2, 3, -cameraLocation.getZ());
        mat1.setValue(0, 0, Xv.getX());
        mat1.setValue(0, 1, Xv.getY());
        mat1.setValue(0, 2, Xv.getZ());
        mat1.setValue(1, 0, Yv.getX());
        mat1.setValue(1, 1, Yv.getY());
        mat1.setValue(1, 2, Yv.getZ());
        mat1.setValue(2, 0, Zv.getX());
        mat1.setValue(2, 1, Zv.getY());
        mat1.setValue(2, 2, Zv.getZ());
        return mat1.mult(mat2);
    }

    public static Matrix createMV2Matrix(double v_width, double v_height, double margin, double left, double right, double top, double buttom, double[] windowCenter) {
        // calculate T2
        Matrix t2 = createIdentityMatrix(4);
        t2.setValue(0, 3, margin + v_width / 2);
        t2.setValue(1, 3, margin + v_height / 2);
        // calculate T1
        Matrix t1 = createIdentityMatrix(4);
        t1.setValue(0, 3, -windowCenter[0]);
        t1.setValue(1, 3, -windowCenter[1]);
        // calculate scale matrix
        Matrix scale = createIdentityMatrix(4);
        scale.setValue(0, 0, v_width / (right - left));
        scale.setValue(1, 1,  v_height / (top - buttom));
        return t2.mult(scale).mult(t1);
    }

    public static Matrix createOrthographicProjctionMatrix(int rows, int cols) {
        Matrix mat = createIdentityMatrix(rows);
        mat.setValue(2, 2, 0);
        return mat;
    }
}
