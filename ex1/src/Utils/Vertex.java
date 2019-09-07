/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Utils;

public class Vertex {
    // members
    private double x,y,z, w = 1;
    private int id;

    // Constructor
    public Vertex(double x, double y, double z) {
        this.id = 0;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex() {
        this.id = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vertex(double[] arr) {
        this.id = 0;
        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
        this.w = 1;
        if(arr.length == 4) {
            this.w = arr[3];
        }
    }

    public Vertex(double x, double y, double z, int id) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // getters and setters to all values
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }


    public double getW() {
        return w;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void print() {
        System.out.print(this.x + ",");
        System.out.print(this.y + ",");
        System.out.print(this.z + ",");
        System.out.print(this.w + "\n");
    }

    public boolean equals(Vertex vertex) {
        if (this.x == vertex.x && this.y == vertex.y && this.z == vertex.z &&
                this.w == vertex.w) {
            return true;
        }
        return false;
    }

    public Vector getVector() {
        Vector v = new Vector(this.x, this.y, this.z);
        return v;
    }
}
