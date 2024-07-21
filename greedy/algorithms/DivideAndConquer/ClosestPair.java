package algorithms.DivideAndConquer;

import algorithms.Algorithm;
import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair implements Algorithm {
    private Point[] points;
    private double minDist;
    private Point[] closestPair;

    public ClosestPair(Point[] points) {
        this.points = points;
    }

    @Override
    public void execute() {
        Arrays.sort(points, Comparator.comparingInt(p -> p.x));
        closestPair = new Point[2];
        minDist = closestPairUtil(points, 0, points.length - 1);
    }

    private double closestPairUtil(Point[] points, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }

        int mid = (left + right) / 2;
        Point midPoint = points[mid];

        double dl = closestPairUtil(points, left, mid);
        double dr = closestPairUtil(points, mid + 1, right);

        double d = Math.min(dl, dr);

        Point[] strip = new Point[right - left + 1];
        int j = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < d) {
                strip[j] = points[i];
                j++;
            }
        }

        return Math.min(d, stripClosest(strip, j, d));
    }

    private double stripClosest(Point[] strip, int size, double d) {
        double min = d;
        Arrays.sort(strip, 0, size, Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                double distance = dist(strip[i], strip[j]);
                if (distance < min) {
                    min = distance;
                    closestPair[0] = strip[i];
                    closestPair[1] = strip[j];
                }
            }
        }
        return min;
    }

    private double bruteForce(Point[] points, int left, int right) {
        double min = Double.MAX_VALUE;
        for (int i = left; i < right; ++i) {
            for (int j = i + 1; j <= right; ++j) {
                double distance = dist(points[i], points[j]);
                if (distance < min) {
                    min = distance;
                    closestPair[0] = points[i];
                    closestPair[1] = points[j];
                }
            }
        }
        return min;
    }

    private double dist(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }

    @Override
    public void displayResult() {
        System.out.println("Closest pair: (" + closestPair[0].x + ", " + closestPair[0].y + "), (" + closestPair[1].x + ", " + closestPair[1].y + ")");
        System.out.println("Distance: " + minDist);
    }
}
