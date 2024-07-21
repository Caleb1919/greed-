package algorithms.DivideAndConquer;

import algorithms.Algorithm;

public class StrassenMatrixMultiplication implements Algorithm {
    private int[][] A;
    private int[][] B;
    private int[][] C;

    public StrassenMatrixMultiplication(int[][] A, int[][] B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public void execute() {
        int n = A.length;
        C = strassen(A, B, n);
    }

    private int[][] strassen(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];

        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int newSize = n / 2;
            int[][] A11 = new int[newSize][newSize];
            int[][] A12 = new int[newSize][newSize];
            int[][] A21 = new int[newSize][newSize];
            int[][] A22 = new int[newSize][newSize];

            int[][] B11 = new int[newSize][newSize];
            int[][] B12 = new int[newSize][newSize];
            int[][] B21 = new int[newSize][newSize];
            int[][] B22 = new int[newSize][newSize];

            split(A, A11, 0, 0);
            split(A, A12, 0, newSize);
            split(A, A21, newSize, 0);
            split(A, A22, newSize, newSize);

            split(B, B11, 0, 0);
            split(B, B12, 0, newSize);
            split(B, B21, newSize, 0);
            split(B, B22, newSize, newSize);

            int[][] M1 = strassen(add(A11, A22), add(B11, B22), newSize);
            int[][] M2 = strassen(add(A21, A22), B11, newSize);
            int[][] M3 = strassen(A11, subtract(B12, B22), newSize);
            int[][] M4 = strassen(A22, subtract(B21, B11), newSize);
            int[][] M5 = strassen(add(A11, A12), B22, newSize);
            int[][] M6 = strassen(subtract(A21, A11), add(B11, B12), newSize);
            int[][] M7 = strassen(subtract(A12, A22), add(B21, B22), newSize);

            int[][] C11 = add(subtract(add(M1, M4), M5), M7);
            int[][] C12 = add(M3, M5);
            int[][] C21 = add(M2, M4);
            int[][] C22 = add(subtract(add(M1, M3), M2), M6);

            join(C11, C, 0, 0);
            join(C12, C, 0, newSize);
            join(C21, C, newSize, 0);
            join(C22, C, newSize, newSize);
        }
        return C;
    }

    private void split(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    private void join(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }

    private int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    @Override
    public void displayResult() {
        System.out.println("Resultant Matrix: ");
        for (int[] row : C) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
