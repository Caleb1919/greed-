package ui;

import algorithms.Algorithm;
import algorithms.DivideAndConquer.*;
import algorithms.Greedy.*;
import tester.PerformanceTester;

import java.awt.Point;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Select Algorithm Category:");
            System.out.println("1. Divide and Conquer");
            System.out.println("2. Greedy");
            System.out.println("0. Exit");

            int category = scanner.nextInt();
            if (category == 0) break;

            System.out.println("Select Algorithm:");
            if (category == 1) {
                System.out.println("1. Strassen's Matrix Multiplication");
                System.out.println("2. QuickHull");
                System.out.println("3. QuickSort");
                System.out.println("4. MergeSort");
                System.out.println("0. Back");
            } else if (category == 2) {
                System.out.println("1. Prim's MST");
                System.out.println("2. Travelling Salesman Problem (Approximate)");
                System.out.println("3. Kruskal's MST");
                System.out.println("4. Dijkstra's Shortest Path");
                System.out.println("5. Huffman Codes");
                System.out.println("0. Back");
            }

            int choice = scanner.nextInt();
            if (choice == 0) continue;

            Algorithm algorithm = null;

            if (category == 1) {
                switch (choice) {
                    case 1:
                        int[][] matrixA = getMatrixInput("Matrix A");
                        int[][] matrixB = getMatrixInput("Matrix B");
                        algorithm = new StrassenMatrixMultiplication(matrixA, matrixB);
                        break;
                    case 2:
                        Point[] points = getPointsInput();
                        algorithm = new QuickHull(points);
                        break;
                    case 3:
                        int[] arrayQS = getIntArrayInput();
                        algorithm = new QuickSort(arrayQS);
                        break;
                    case 4:
                        int[] arrayMS = getIntArrayInput();
                        algorithm = new MergeSort(arrayMS);
                        break;
                }
            } else if (category == 2) {
                switch (choice) {
                    case 1:
                        int[][] graphPrim = getGraphInput();
                        algorithm = new PrimMST(graphPrim);
                        break;
                    case 2:
                        int[][] graphTSP = getGraphInput();
                        algorithm = new TSP(graphTSP);
                        break;
                    case 3:
                        int[][] graphKruskal = getGraphInput();
algorithm    = new KruskalMST(graphKruskal);
                        break;
                    case 4:
                        int[][] graphDijkstra = getGraphInput();
                        System.out.println("Enter source node:");
                        int source = scanner.nextInt();
                        algorithm = new Dijkstra(graphDijkstra, source);
                        break;
                    case 5:
                        System.out.println("Enter characters (comma-separated):");
                        scanner.nextLine(); // consume newline
                        String charStr = scanner.nextLine();
                        char[] chars = charStr.replaceAll("\\s+", "").toCharArray();
                        System.out.println("Enter frequencies (comma-separated):");
                        int[] freq = getIntArrayInput();
                        algorithm = new HuffmanCodes(chars, freq);
                        break;
                }
            }

            if (algorithm != null) {
                PerformanceTester tester = new PerformanceTester(algorithm);
                tester.test();
            }
        }
    }

    private int[][] getMatrixInput(String name) {
        System.out.println("Enter size of " + name + ": ");
        int size = scanner.nextInt();
        int[][] matrix = new int[size][size];
        System.out.println("Enter elements of " + name + ": ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private int[] getIntArrayInput() {
        System.out.println("Enter size of array: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Enter elements of array: ");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    private int[][] getGraphInput() {
        System.out.println("Enter number of nodes in the graph: ");
        int size = scanner.nextInt();
        int[][] graph = new int[size][size];
        System.out.println("Enter adjacency matrix of the graph: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        return graph;
    }

    private Point[] getPointsInput() {
        System.out.println("Enter number of points: ");
        int size = scanner.nextInt();
        Point[] points = new Point[size];
        System.out.println("Enter points (x and y coordinates): ");
        for (int i = 0; i < size; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }
        return points;
    }
}
