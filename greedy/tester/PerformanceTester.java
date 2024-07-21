package tester;

import algorithms.Algorithm;

public class PerformanceTester {
    private Algorithm algorithm;

    public PerformanceTester(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void test() {
        long startTime = System.nanoTime();
        algorithm.execute();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        algorithm.displayResult();
        System.out.println("Execution time: " + duration + " nanoseconds");
    }
}
