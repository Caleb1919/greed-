package algorithms.DivideAndConquer;

import algorithms.Algorithm;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class QuickHull implements Algorithm {
    private Point[] points;
    private List<Point> hull;

    public QuickHull(Point[] points) {
        this.points = points;
        this.hull = new ArrayList<>();
    }

    @Override
    public void execute() {
        if (points.length < 3) {
            throw new IllegalArgumentException("There must be at least 3 points");
        }

        int minPoint = -1, maxPoint = -1;
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            if (points[i].x < minX) {
                minX = points[i].x;
                minPoint = i;
            }
            if (points[i].x > maxX) {
                maxX = points[i].x;
                maxPoint = i;
            }
        }

        Point A = points[minPoint];
        Point B = points[maxPoint];
        hull.add(A);
        hull.add(B);
        List<Point> leftSet = new ArrayList<>();
        List<Point> rightSet = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (p.equals(A) || p.equals(B)) continue;
            if (pointLocation(A, B, p) == -1) {
                leftSet.add(p);
            } else {
                rightSet.add(p);
            }
        }

        hullSet(A, B, rightSet);
        hullSet(B, A, leftSet);
    }

    private void hullSet(Point A, Point B, List<Point> set) {
        if (set.isEmpty()) return;
        if (set.size() == 1) {
            Point p = set.get(0);
            hull.add(hull.indexOf(A), p);
            set.remove(p);
            return;
        }

        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++) {
            Point p = set.get(i);
            int distance = distance(A, B, p);
            if (distance > dist) {
                dist = distance;
                furthestPoint = i;
            }
        }

        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(hull.indexOf(A), P);

        List<Point> leftSetAP = new ArrayList<>();
        for (Point m : set) {
            if (pointLocation(A, P, m) == 1) {
                leftSetAP.add(m);
            }
        }

        List<Point> leftSetPB = new ArrayList<>();
        for (Point m : set) {
            if (pointLocation(P, B, m) == 1) {
                leftSetPB.add(m);
            }
        }

        hullSet(A, P, leftSetAP);
        hullSet(P, B, leftSetPB);
    }

    private int distance(Point A, Point B, Point C) {
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        return Math.abs(num);
    }

    private int pointLocation(Point A, Point B, Point P) {
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        if (cp1 > 0) return 1;
        else if (cp1 == 0) return 0;
        else return -1;
    }

    @Override
    public void displayResult() {
        System.out.println("Convex Hull Points: ");
        for (Point p : hull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}

