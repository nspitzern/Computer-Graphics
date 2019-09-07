/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package WorldObjects;

import Utils.Vector;
import javax.media.opengl.GL2;

public interface WorldObject {
    void draw(GL2 gl);
    Vector getLocation();
}
