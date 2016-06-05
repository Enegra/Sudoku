package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        int[][] matrix = {{0, 0, 0, 1}, {0, 1, 4, 0}, {0, 4, 3, 0}, {3, 0, 0, 0}};
        int[][] matrix2 = {{4, 0, 0, 0}, {0, 1, 2, 0}, {0, 4, 3, 0}, {0, 0, 0, 2}};
        int[][] matrix3 = {{3, 0, 1, 0}, {0, 0, 0, 2}, {4, 0, 0, 0}, {0, 3, 0, 1}};
        int[][] matrix4 = {{0, 1, 2, 0}, {0, 0, 0, 1}, {3, 0, 0, 0}, {0, 3, 3, 0}};
        int[][] matrix5 = {{0, 0, 1, 8, 0, 0, 7, 0, 0}, {6, 3, 0, 0, 4, 0, 0, 1, 8}, {2, 0, 0, 0, 9, 0, 0, 0, 4}, {5, 6, 0, 0, 0, 0, 0, 9, 3}, {0, 1, 2, 0, 0, 0, 6, 4, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {7, 0, 0, 0, 3, 0, 0, 0, 6}, {8, 5, 0, 0, 7, 0, 0, 2, 9}, {0, 0, 3, 0, 0, 5, 4, 0, 0}};
        Sample sample = new Sample(matrix3, 2);
        Sample sample1 = new Sample(matrix5, 3);
        Solver solver = new Solver(sample);
        Solver solver1 = new Solver(sample1);
//        solver.solve();
//        System.out.println(solver.getCallsNumber());
//        solver1.solve();
//        System.out.println(solver1.getCallsNumber());
        System.out.println(Arrays.deepToString(sample.getContent()));
//        System.out.println(Arrays.deepToString(sample1.getContent()));
        solver1.solveFw();
        System.out.println(Arrays.deepToString(sample1.getContent()));



    }

}
