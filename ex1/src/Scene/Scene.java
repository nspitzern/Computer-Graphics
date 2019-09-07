/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Scene;

import Transform.Transformation;
import Utils.Matrix;
import Utils.MatrixFactory;
import Utils.Vector;

public class Scene {

    public static Matrix calculateMV1(Vector cameraLocation, Vector lookAtPoint, Vector upVector) {
        Vector Zv = cameraLocation.sub(lookAtPoint);
        Zv = Zv.normalizeVector();
        Vector Xv = upVector.crossProduct(Zv);
        Xv = Xv.normalizeVector();
        Vector Yv = Zv.crossProduct(Xv);
        return MatrixFactory.createMV1Matrix(cameraLocation, Zv, Xv, Yv);
    }

    public static Matrix calculateMV2(double v_width, double v_height, double margin, double left, double right, double buttom, double top) {
        // calculate window center
        double[] windowCenter = new double[2];
        windowCenter[0] = left + ((right-left) / 2);
        windowCenter[1] = buttom + ((top-buttom) / 2);
        return MatrixFactory.createMV2Matrix(v_width, v_height, margin, left, right, top, buttom, windowCenter);
    }

    public static Matrix calculateRotateTransform(String rotateAxis, double x, double y, double[] clickedPoint,
                                                  double viewWidth, double viewHeight, Vector LP) {
        Matrix lookAtMat = Transformation.translate(0, 0, -LP.getVectorSize());
        Matrix inverseLookAtMat = Transformation.translate(0, 0, LP.getVectorSize());
        // D - destination, S - source, C - window center
        Vector D, C, S;
        D = new Vector(x, y, 0);
        S = new Vector(clickedPoint[0], clickedPoint[1], 0);
        C = new Vector(viewWidth, viewHeight, 0);
        // calculate the angle between D and C
        double DCAngle = Math.toDegrees(Math.atan2(D.sub(C).getY(), D.sub(C).getX()));
        // calculate the angle between S and C
        double SCAngle = Math.toDegrees(Math.atan2(S.sub(C).getY(), S.sub(C).getX()));
        double theta = Math.toRadians(DCAngle - SCAngle);
        Matrix rot;
        switch (rotateAxis.toUpperCase()) {
            case "X":
                rot = Transformation.rotateByX(theta);
                break;
            case "Y":
                rot = Transformation.rotateByY(theta);
                break;
            case "Z":
                rot = Transformation.rotateByZ(theta);
                break;
            default:
                rot = Transformation.rotateByZ(theta);
                break;
        }
        return lookAtMat.mult(rot).mult(inverseLookAtMat);
    }

    public static Matrix calculateScaleTransform(double x, double y, double[] clickedPoint,
                                                 double viewWidth, double viewHeight, Vector LP) {
        Matrix lookAtMat = Transformation.translate(0, 0, -LP.getVectorSize());
        Matrix inverseLookAtMat = Transformation.translate(0, 0, LP.getVectorSize());
        // D - destination, S - source, C - window center
        Vector D, C, S;
        D = new Vector(x, y, 0);
        S = new Vector(clickedPoint[0], clickedPoint[1], 0);
        C = new Vector(viewWidth, viewHeight, 0);
        // calculate |D-C|/|S-C|
        double ratio = (D.sub(C).getVectorSize()) / (S.sub(C).getVectorSize());
        Matrix scale = Transformation.scale(ratio, ratio, ratio);
        return lookAtMat.mult(scale).mult(inverseLookAtMat);
    }

    public static Matrix calculateTranslateTransform(double x, double y, double[] clickedPoint, double[] window,
                                                     double viewHeight, double viewWidth) {
        double dx, dy;
        dx = x - clickedPoint[0];
        dy = y - clickedPoint[1];
        double window_height = window[3] - window[2];
        double window_width = window[1] - window[0];
        double dwh = window_height / viewHeight, dww = window_width / viewWidth;
        Matrix trans = Transformation.translate(dx * dww, dy * (dwh), 0);
        return trans;
    }
}
