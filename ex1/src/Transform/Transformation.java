/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Transform;

import Utils.Matrix;
import Utils.MatrixFactory;

public class Transformation {

    public static Matrix translate(double dx, double dy, double dz) {
        Matrix tran = MatrixFactory.createIdentityMatrix(4);
        tran.setValue(0, 3, dx);
        tran.setValue(1, 3, dy);
        tran.setValue(2, 3, dz);
        return tran;
    }

    public static Matrix scale(double a, double b, double c) {
        Matrix scale = MatrixFactory.createIdentityMatrix(4);
        scale.setValue(0, 0, a);
        scale.setValue(1, 1, b);
        scale.setValue(2, 2, c);
        return scale;
    }

    public static Matrix rotateByX(double theta) {
        Matrix rot = MatrixFactory.createIdentityMatrix(4);
        rot.setValue(1, 1, Math.cos(theta));
        rot.setValue(1, 2, -Math.sin(theta));
        rot.setValue(2, 1, Math.sin(theta));
        rot.setValue(2, 2, Math.cos(theta));
        return rot;
    }

    public static Matrix rotateByY(double theta) {
        Matrix rot = MatrixFactory.createIdentityMatrix(4);
        rot.setValue(0, 0, Math.cos(theta));
        rot.setValue(0, 2, -Math.sin(theta));
        rot.setValue(2, 0, Math.sin(theta));
        rot.setValue(2, 2, Math.cos(theta));
        return rot;
    }

    public static Matrix rotateByZ(double theta) {
        Matrix rot = MatrixFactory.createIdentityMatrix(4);
        rot.setValue(0, 0, Math.cos(theta));
        rot.setValue(0, 1, -Math.sin(theta));
        rot.setValue(1, 0, Math.sin(theta));
        rot.setValue(1, 1, Math.cos(theta));
        return rot;
    }

    public static Matrix reflectionXYPlane() {
        Matrix ref = MatrixFactory.createIdentityMatrix(4);
        ref.setValue(2, 2, -1);
        return ref;
    }

    public static Matrix reflectionXZPlane() {
        Matrix ref = MatrixFactory.createIdentityMatrix(4);
        ref.setValue(1, 1, -1);
        return ref;
    }

    public static Matrix reflectionYZPlane() {
        Matrix ref = MatrixFactory.createIdentityMatrix(4);
        ref.setValue(0, 0, -1);
        return ref;
    }

    public static Matrix shearing(double a, double b, double c, double d, double e, double f) {
        Matrix shear = MatrixFactory.createIdentityMatrix(4);
        shear.setValue(0, 1, a);
        shear.setValue(0, 2, b);
        shear.setValue(1, 0, c);
        shear.setValue(1, 2, d);
        shear.setValue(2, 0, e);
        shear.setValue(2, 1, f);
        return shear;
    }
}
