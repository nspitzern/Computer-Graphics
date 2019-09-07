package Tests;

import Scene.Clipping;
import Utils.Vertex;
import Utils.Line;

import java.util.ArrayList;

public class ClippingTest {
    private static final String RESULTSTRING = "Result:";
    private static final String EXPECTEDRESULTSTRING = "Expected result:";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {

        Vertex topLeft = new Vertex(0,0,0);
        Vertex topRight = new Vertex(1,0,0);
        Vertex bottomRight = new Vertex(1, 1, 0);
        Vertex bottomLet = new Vertex(0, 1, 0);

        Vertex start = new Vertex(-10 , -1, 0);
        Vertex end = new Vertex(30, -1, 0);
        Line toBeClipped = new Line(start.getVector(), end.getVector());

        Vertex resStart = new Vertex(0, 0.5, 0);
        Vertex resEnd = new Vertex(1, 0.5, 0);
        Line res = new Line(resStart.getVector(), resEnd.getVector());

        ArrayList<Vertex> arr = new ArrayList<>();
        arr.add(topLeft);
        arr.add(topRight);
        arr.add(bottomRight);
        arr.add(bottomLet);
        Line afterClipping = Clipping.clipLine(start.getVector(), end.getVector(), arr);

        if(!(afterClipping.getStart().getX() == res.getStart().getX() && afterClipping.getStart().getY() == res.getStart().getY()
                && afterClipping.getEnd().getX() == res.getEnd().getX() && afterClipping.getEnd().getY() == res.getEnd().getY())) {
            System.out.println(ANSI_RED + "Failed Test 1. ");
            System.out.println("Expected result:");
            System.out.println(res);
            System.out.println("Gotten result:");
            System.out.println(afterClipping);
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 1.");
        }

    }
}
