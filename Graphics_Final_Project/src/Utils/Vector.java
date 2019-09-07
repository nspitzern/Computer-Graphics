package Utils;

public class Vector {
    // members
    private float x;
    private float y;
    private float z;

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getVectorSize() {
        return (float)Math.sqrt(dotProduct(this));
    }

    public Vector crossProduct(Vector v) {
        float x, y, z;
        x = this.y * v.z - this.z * v.y;
        y = this.z * v.x - this.x * v.z;
        z = this.x * v.y - this.y * v.x;
        return new Vector(x, y, z);
    }

    public float dotProduct(Vector other) {
        float sum = 0;
        sum += (this.x * other.x + this.y * other.y + this.z * other.z);
        return sum;
    }

    /*****
     * the function normalizes the vector
     * @return a normalized vector
     */
    public Vector normalizeVector() {
        float x, y, z;
        x = this.x / this.getVectorSize();
        y = this.y / this.getVectorSize();
        z = this.z / this.getVectorSize();
        return new Vector(x, y, z);
    }

    /******
     * the functions returns a new vector of multiplication by scalar
     * @param s scalar
     * @return a new vector
     */
    public Vector multByScalar(float s) {
        float x, y, z;
        x = this.x * s;
        y = this.y * s;
        z = this.z * s;
        return new Vector(x, y, z);
    }

    /*****
     * the function returns the sum of 2 vectors as a new vector
     * @param v other vector
     * @return a new vector
     */
    public Vector add(Vector v) {
        float x, y, z;
        x = this.x + v.x;
        y = this.y + v.y;
        z = this.z + v.z;
        return new Vector(x, y, z);
    }

    /****
     * the function substracts one vector from another.
     * @param v the other vector.
     * @return the new vector.
     */
    public Vector sub(Vector v) {
        float x, y, z;
        x = this.x - v.x;
        y = this.y - v.y;
        z = this.z - v.z;
        return new Vector(x, y, z);
    }

    /*****
     * returns the distance between 2 vectors.
     * @param other the other vector.
     * @return the distance.
     */
    public float getDistance(Vector other) {
        double dist = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) +Math.pow(this.z - other.z, 2);
        return (float)Math.sqrt(dist);
    }

    /***
     * returns the angle between 2 vector.
     * @param other teh other vector.
     * @return the angle.
     */
    public float getAngleWithVector(Vector other) {
        float angle = this.dotProduct(other) / (this.getVectorSize() * other.getVectorSize());
        return (float) Math.acos(angle);
    }

    /***
     * sets x.
     * @param x new x value.
     */
    public void setX(float x) {
        this.x = x;
    }

    /***
     * sets y.
     * @param y new y value.
     */
    public void setY(float y) {
        this.y = y;
    }

    /***
     * sets z.
     * @param z new z value.
     */
    public void setZ(float z) {
        this.z = z;
    }
}
