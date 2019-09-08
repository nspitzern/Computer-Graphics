package Utils;

public class Line {
    // members
    private Vector start, end;

    public Line(Vector s, Vector e) {
        this.start = s;
        this.end = e;
    }

    public Vector getStart() {
        return this.start;
    }

    public Vector getEnd() {
        return this.end;
    }

    public double getLength() {
        double size = this.start.getDistance(this.end);
        return size;
    }
}
