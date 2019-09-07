package WorldObjects;

import Utils.Vector;
import javax.media.opengl.GL2;

public interface WorldObject {
    void draw(GL2 gl);
    Vector getLocation();
}
