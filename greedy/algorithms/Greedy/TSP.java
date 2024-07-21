package algorithms.Greedy;

import algorithms.Algorithm;
import java.util.Arrays;

public class TSP implements Algorithm {
    private int[][] graph;
    private int V;

    public TSP(int[][] graph) {
        this.graph = graph;
        this.V = graph.length;
    }

    @Override
    public void execute() {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);

        int[] path = new int[V + 1];
        path[0] = 0;
        visited[0] = true;

        for (int i = 1; i < V; i++) {
            path[i] = nearestNeighbor(path[i - 1], visited);
            visited[path[i]] = true;
        }
        path[V] = path[0];

        printPath(path);
    }

    private int nearestNeighbor(int node, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int nearest = -1;

        for (int i = 0; i < V; i++) {
            if (!visited[i] && graph[node][i] < minDistance) {
                minDistance = graph[node][i];
                nearest = i;
            }
        }

        return nearest;
    }

    private void printPath(int[] path) {
        System.out.print("Path: ");
        for (int i = 0; i < path.length; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
    }

    @Override
    public void displayResult() {
        // Already displayed in printPath method.
    }
}
