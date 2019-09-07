/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Coordinates;

import Enums.MovementEnum;
import Enums.SteerEnum;
import Utils.Vector;

public class Cartesian implements Coordinates {

    private Vector xAxis;
    private Vector yAxis;
    private Vector zAxis;

    /****
     * Constructor
     */
    public Cartesian() {
        this.xAxis = new Vector(1.0f, 0.0f, 0.0f);
        this.yAxis = new Vector(0.0f, 1.0f, 0.0f);
        this.zAxis = new Vector(0.0f, 0.0f, 1.0f);
    }

    @Override
    public Vector move(MovementEnum direction, Vector pos, float step) {
        switch (direction) {
            case FORWARD:
                pos.setX(pos.getX() - this.zAxis.getX() * step);
                pos.setY(pos.getY() - this.zAxis.getY() * step);
                pos.setZ(pos.getZ() - this.zAxis.getZ() * step);
                break;
            case BACKWARD:
                pos.setX(pos.getX() + this.zAxis.getX() * step);
                pos.setY(pos.getY() + this.zAxis.getY() * step);
                pos.setZ(pos.getZ() + this.zAxis.getZ() * step);
                break;
            case LEFT:
                pos.setX(pos.getX() - this.xAxis.getX() * step);
                pos.setY(pos.getY() - this.xAxis.getY() * step);
                pos.setZ(pos.getZ() - this.xAxis.getZ() * step);
                break;
            case RIGHT:
                pos.setX(pos.getX() + this.xAxis.getX() * step);
                pos.setY(pos.getY() + this.xAxis.getY() * step);
                pos.setZ(pos.getZ() + this.xAxis.getZ() * step);
                break;
            case UP:
                pos.setX(pos.getX() + this.yAxis.getX() * step);
                pos.setY(pos.getY() + this.yAxis.getY() * step);
                pos.setZ(pos.getZ() + this.yAxis.getZ() * step);
                break;
            case DOWN:
                pos.setX(pos.getX() - this.yAxis.getX() * step);
                pos.setY(pos.getY() - this.yAxis.getY() * step);
                pos.setZ(pos.getZ() - this.yAxis.getZ() * step);
                break;
        }
        return pos;
    }

    @Override
    public void rotate(SteerEnum axis, float alpha) {
        switch(axis) {
            case UP_X:
            case DOWN_X:
                yAxis = yAxis.multByScalar((float)Math.cos(alpha)).sub(zAxis.multByScalar((float)Math.sin(alpha)));
                zAxis = zAxis.multByScalar((float)Math.cos(alpha)).add(yAxis.multByScalar((float)Math.sin(alpha)));
                yAxis = yAxis.normalizeVector();
                zAxis = zAxis.normalizeVector();
                break;
            case LEFT_Y:
            case RIGHT_Y:
                xAxis = xAxis.multByScalar((float)Math.cos(alpha)).sub(zAxis.multByScalar((float)Math.sin(alpha)));
                zAxis = zAxis.multByScalar((float)Math.cos(alpha)).add(xAxis.multByScalar((float)Math.sin(alpha)));
                xAxis = xAxis.normalizeVector();
                zAxis = zAxis.normalizeVector();
                break;
            case LEFT_Z:
            case RIGHT_Z:
                yAxis = yAxis.multByScalar((float)Math.cos(alpha)).sub(xAxis.multByScalar((float)Math.sin(alpha)));
                xAxis = xAxis.multByScalar((float)Math.cos(alpha)).add(yAxis.multByScalar((float)Math.sin(alpha)));
                yAxis = yAxis.normalizeVector();
                xAxis = xAxis.normalizeVector();
                break;
        }
    }

    /***
     * returns the x axis
     * @return x axis
     */
    public Vector getxAxis() {
        return xAxis;
    }

    /***
     * returns the y axis
     * @return y axis
     */
    public Vector getyAxis() {
        return yAxis;
    }

    /***
     * returns the z axis
     * @return z axis
     */
    public Vector getzAxis() {
        return zAxis;
    }
}
