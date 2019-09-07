/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Utils;

public class Vector {
    // members
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getVectorSize() {
        return Math.sqrt(dotProduct(this));
    }

    public Vector crossProduct(Vector v) {
        double x, y, z;
        x = this.y * v.z - this.z * v.y;
        y = this.z * v.x - this.x * v.z;
        z = this.x * v.y - this.y * v.x;
        return new Vector(x, y, z);
    }

    public double dotProduct(Vector other) {
        double sum = 0;
        sum += (this.x * other.x + this.y * other.y + this.z * other.z);
        return sum;
    }

    /*****
     * the function normalizes the vector
     * @return a normalized vector
     */
    public Vector normalizeVector() {
        double x, y, z;
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
    public Vector multByScalar(double s) {
        double x, y, z;
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
        double x, y, z;
        x = this.x + v.x;
        y = this.y + v.y;
        z = this.z + v.z;
        return new Vector(x, y, z);
    }

    public Vector sub(Vector v) {
        double x, y, z;
        x = this.x - v.x;
        y = this.y - v.y;
        z = this.z - v.z;
        return new Vector(x, y, z);
    }

    public Vertex getVertx() {
        Vertex v = new Vertex(this.x, this.y, this.z);
        return v;
    }

    public double getDistance(Vector other) {
        double dist = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) +Math.pow(this.z - other.z, 2);
        return Math.sqrt(dist);
    }
}
