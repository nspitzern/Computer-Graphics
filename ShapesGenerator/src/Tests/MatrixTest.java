package Tests;

import Utils.Matrix;
import Utils.MatrixFactory;
import Utils.Vertex;

public class MatrixTest {

    private static final String RESULTSTRING = "Result:";
    private static final String EXPECTEDRESULTSTRING = "Expected result:";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {

        // Test 1
        Matrix matrix1 = MatrixFactory.createIdentityMatrix(4);;
        Matrix matrix2 = MatrixFactory.createIdentityMatrix(4);;
        Vertex currVertex;
        Vertex resVertex;
        double[][] realArr1 = {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
        Matrix realRes = new Matrix(realArr1);

        Matrix res1 = matrix1.mult(matrix2);
        if(!res1.equals(realRes)) {
            System.out.println(ANSI_RED + "Test 1 failed.");
            System.out.println(RESULTSTRING);
            res1.print();
            System.out.println(EXPECTEDRESULTSTRING);
            realRes.print();
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 1.");
        }
        // End of Test 1

        // Test 6
        //multiple regular matrixes
        double[][] arrmatrix61 = {{2,9,8,-1},{9,-4,6,3},{7,-3,-1,0},{5,3,7,3}};
        double[][] arrmatrix62 = {{3,2,4,4},{5,3,2,3},{2,1,4,2},{3,6,5,1}};
        double[][] realArr6 = {{64,33,53,50},{28,30,67,39},{4,4,18,17},{53,44,69,46}};
        matrix1 = new Matrix(arrmatrix61);
        matrix2 = new Matrix(arrmatrix62);
        realRes = new Matrix(realArr6);

        Matrix res6 = matrix1.mult(matrix2);
        if(!res6.equals(realRes)) {
            System.out.println(ANSI_RED + "Test 6 failed.");
            System.out.println(RESULTSTRING);
            res6.print();
            System.out.println(EXPECTEDRESULTSTRING);
            realRes.print();
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 6.");
        }
        // End of Test 6

        // Test 7
        //multiple matrix and Vertex from right side
        double[][] arrmatrix7 = {{2,9,8,-1},{9,-4,6,3},{7,-3,-1,8},{5,3,7,2}};
        //Vertex
        double[] Vertex7 = {3,2,4};
        double[] realArr7 = {55,46,19,51};
        currVertex = new Vertex(Vertex7);
        matrix2 = new Matrix(arrmatrix7);
        resVertex = new Vertex(realArr7);

        //////////////////////////////////////////////////////////////////////
        Vertex res7 = matrix2.rightMult(currVertex);
        if(!res7.equals(resVertex)) {
            System.out.println(ANSI_RED + "Test 7 failed.");
            System.out.println(RESULTSTRING);
            res7.print();
            System.out.println(EXPECTEDRESULTSTRING);
            resVertex.print();
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 7.");
        }
        // End of Test 7

        // Test 8
        //multiple matrix and Vertex from right side
        double[][] arrmatrix8 = {{2,9,8,-1},{9,-4,6,3},{7,-3,-1,8},{5,3,7,2}};
        //Vertex
        double[] Vertex8 = {5,-2,7};
        double[] realArr8 = {47,98,42,70};
        currVertex = new Vertex(Vertex8);
        matrix2 = new Matrix(arrmatrix8);
        resVertex = new Vertex(realArr8);

        //////////////////////////////////////////////////////////////////////
        Vertex res8 = matrix2.rightMult(currVertex);
        if(!res8.equals(resVertex)) {
            System.out.println(ANSI_RED + "Test 8 failed.");
            System.out.println(RESULTSTRING);
            res8.print();
            System.out.println(EXPECTEDRESULTSTRING);
            resVertex.print();
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 8.");
        }
        // End of Test 8

        // Test 9
        //multiple matrix and Vertex that is from left side
        double[][] arrmatrix9 = {{2,9,8,-1},{9,-4,6,3},{7,-3,-1,8},{5,3,7,2}};
        //Vertex
        double[] Vertex9 = {3,2,4};
        double[] realArr9 = {57,10,39,37};
        currVertex = new Vertex(Vertex9);
        matrix2 = new Matrix(arrmatrix9);
        resVertex = new Vertex(realArr9);

        //////////////////////////////////////////////////////////////////////
        Vertex res9 = matrix2.leftMult(currVertex);
        if(!res9.equals(resVertex)) {
            System.out.println(ANSI_RED + "Test 9 failed.");
            System.out.println(RESULTSTRING);
            res9.print();
            System.out.println(EXPECTEDRESULTSTRING);
            resVertex.print();
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 9.");
        }
        // End of Test 9

        // Test 10
        //multiple matrix and Vertex that is at the left side
        double[][] arrmatrix10 = {{2,9,8,-1},{9,-4,6,3},{7,-3,-1,8},{5,3,7,2}};
        //Vertex
        double[] Vertex10 = {5,-2,7,1};
        double[] realArr10 = {46,35,28,47};
        currVertex = new Vertex(Vertex10);
        matrix2 = new Matrix(arrmatrix10);
        resVertex = new Vertex(realArr10);

        //////////////////////////////////////////////////////////////////////
        Vertex res10 = matrix2.leftMult(currVertex);
        if(!res10.equals(resVertex)) {
            System.out.println(ANSI_RED + "Test 10 failed.");
            System.out.println(RESULTSTRING);
            res10.print();
            System.out.println(EXPECTEDRESULTSTRING);
            resVertex.print();
        } else {
            System.out.println(ANSI_GREEN + "Passed Test 10.");
        }
        // End of Test 10
    }
}