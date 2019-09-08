package Scene;

import Utils.Vector;
import Utils.Vertex;
import Utils.Line;
import java.util.List;

public class Clipping {

    public static Line clipLine(Vector p0, Vector p1, List<Vertex> borderVertices) {
        double maxT = Double.NEGATIVE_INFINITY, minT = Double.POSITIVE_INFINITY;
        Vector PE = null;
        Vector PL = null;
        Vector line = p1.sub(p0);
        for (int i = 0; i < borderVertices.size(); i++) {
            Vector v0 = borderVertices.get(i).getVector();
            Vector v1 = borderVertices.get((i + 1) % borderVertices.size()).getVector();
            Vector V = v0.sub(v1);
            Vector Ni = new Vector(-V.getY(), V.getX(), 0);
            double t = calculateT(Ni, v0, v1, line, p0);
            if (Ni.dotProduct(p1.sub(p0)) == 0 || Ni.dotProduct(p1.sub(p0)) == 0.0 || (t < 0 || t > 1)) {
                continue;
            } else if (Ni.dotProduct(line) > 0 && t < minT) {
                minT = t;
                PL = calculatePoint(p0, line, minT);
            } else if (Ni.dotProduct(line) < 0 && t > maxT) {
                maxT = t;
                PE = calculatePoint(p0, line, maxT);
            }
        }
            if (PE == null && inBorders(borderVertices.get(0), borderVertices.get(2), p0.getVertx())) {
                PE = p0;
            }
            if (PL == null && inBorders(borderVertices.get(0), borderVertices.get(2), p1.getVertx())) {
                PL = p1;
            }
            // Leave Point before Entry Point
            if (PE != null && PL != null && p0.getDistance(PL) < p0.getDistance(PE)) {
                return null;
            }
            if(PE == null && PL == null) {
                return null;
            }
        return new Line(PE, PL);
    }

    private static double calculateT(Vector Ni, Vector v0, Vector v1, Vector line, Vector p0) {
        Vector Qi = new Vector(v0.getX() + (v1.getX() - v0.getX()) / 2, v0.getY() + (v1.getY() - v0.getY()) / 2, 0);
        double t = (Ni.dotProduct(p0.sub(Qi))) / (-Ni.dotProduct(line));
        return t;
    }

    private static Vector calculatePoint(Vector p0, Vector line, double t) {
        return p0.add(line.multByScalar(t));
    }

    private static boolean inBorders(Vertex border1, Vertex border2, Vertex p) {
        return p.getX() >= border1.getX() && p.getX() <= border2.getX()
                && p.getY() >= border1.getY() && p.getY() <= border2.getY();
    }
}
