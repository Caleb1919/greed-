package algorithms.Greedy;

import algorithms.Algorithm;
import java.util.Arrays;

public class KruskalMST implements Algorithm {
    private int[][] graph;
    private int[] parent;

    public KruskalMST(int[][] graph) {
        this.graph = graph;
        this.parent = new int[graph.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    @Override
    public void execute() {
        int vertices = graph.length;
        int edges = 0;
        int[][] mst = new int[vertices - 1][3];
        
        Edge[] edgeList = createEdgeList();
        Arrays.sort(edgeList);

        for (Edge edge : edgeList) {
            if (edges == vertices - 1) {
                break;
            }

            int u = find(edge.src);
            int v = find(edge.dest);

            if (u != v) {
                mst[edges++] = new int[]{edge.src, edge.dest, edge.weight};
                union(u, v);
            }
        }

        displayMST(mst);
    }

    private Edge[] createEdgeList() {
        int edges = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = i; j < graph[i].length; j++) {
                if (graph[i][j] != 0) {
                    edges++;
                }
            }
        }

        Edge[] edgeList = new Edge[edges];
        int index = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = i; j < graph[i].length; j++) {
                if (graph[i][j] != 0) {
                    edgeList[index++] = new Edge(i, j, graph[i][j]);
                }
            }
        }

        return edgeList;
    }

    private int find(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            return parent[i] = find(parent[i]);
        }
    }

    private void union(int u, int v) {
        parent[find(u)] = find(v);
    }

    private void displayMST(int[][] mst) {
        System.out.println("Edge \tWeight");
        for (int i = 0; i < mst.length; i++) {
            System.out.println(mst[i][0] + " - " + mst[i][1] + "\t" + mst[i][2]);
        }
    }

    @Override
    public void displayResult() {
        execute();
    }

    private class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }
}
