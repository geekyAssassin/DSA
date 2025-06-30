package Spanning_Trees.Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalsAlgorithm {
    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }

    static class DisjointSet {
        int[] parent;
        int[] rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find_set(int a) {
            if (parent[a] != a) {
                parent[a] = find_set(parent[a]);
            }
            return parent[a];
        }

        public void union_set(int a, int b) {
            a = find_set(a);
            b = find_set(b);
            if (a != b) {
                if (rank[a] < rank[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                parent[b] = a;
                if (rank[a] == rank[b]) {
                    rank[a]++;
                }
            }
        }
    }

    void kruskalsMST(List<Edge> edges, int V) {
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(V);
        int mstWeight = 0;

        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            if (ds.find_set(edge.src) != ds.find_set(edge.dest)) {
                mst.add(edge);
                mstWeight += edge.weight;
                ds.union_set(edge.src, edge.dest);
                if (mst.size() == V - 1) {
                    break;
                }
            }
        }
        System.out.println("MST Weight: " + mstWeight);
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }
}
