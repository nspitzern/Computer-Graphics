package Coordinates;

import Enums.MovementEnum;
import Enums.SteerEnum;
import Utils.Vector;

public interface Coordinates {

    /***
     * apply movement to the coordinates.
     * @param direction the desired movement axis direction.
     * @param pos the current position.
     * @param step the step rate.
     * @return the new position.
     */
    Vector move(MovementEnum direction, Vector pos, float step);

    /****
     * apply rotation to the coordinates.
     * @param axis the desired rotation axis.
     * @param alpha the rotation angle.
     */
    void rotate(SteerEnum axis, float alpha);

    /***
     * returns the x axis
     * @return x axis
     */
    Vector getxAxis();

    /***
     * returns the y axis
     * @return y axis
     */
    Vector getyAxis();

    /***
     * returns the z axis
     * @return z axis
     */
    Vector getzAxis();
}
