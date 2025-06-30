package Spanning_Trees.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrimsAlgorithm {
    static class Graph {
        int V;
        HashMap<Integer, ArrayList<Node>> graph;

        public Graph(int V) {
            this.V = V;
            graph = new HashMap<>();
        }

        public void addEdge(int src, int dest, int weight) {
            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(new Node(dest, weight));
            graph.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Node(src, weight));
        }

        static class Node {
            int dest;
            int weight;

            public Node(int dest, int weight) {
                this.dest = dest;
                this.weight = weight;
            }
        }

        void primMST() {
            int[] parent = new int[V];
            int[] key = new int[V];

            boolean[] inMST = new boolean[V];

            for (int i = 0; i < V; i++) {
                parent[i] = -1;
                key[i] = Integer.MAX_VALUE;
                inMST[i] = false;
            }

            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
            key[0] = 0;
            pq.add(new Node(0, key[0]));

            while (!pq.isEmpty()) {
                Node currentNode = pq.poll();
                int currentVertex = currentNode.dest;
                inMST[currentVertex] = true;

                for (Node neighbour : graph.getOrDefault(currentNode, new ArrayList<>())) {
                    int neighbourVertex = neighbour.dest;
                    int weight = neighbour.weight;

                    if (!inMST[neighbourVertex] && weight < key[neighbourVertex]) {
                        key[neighbourVertex] = weight;
                        parent[neighbourVertex] = currentVertex;
                        pq.add(new Node(neighbourVertex, key[neighbourVertex]));
                    }
                }
            }

            printMST(parent);
        }

        private void printMST(int[] parent) {
            for (int i = 1; i < V; i++) {
                out.println(parent[i] + " - " + i + " : " + i);
            }
        }
    }

    static PrintWriter out;

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            public void run() {
                solve();
            }
        }, "Main", 1 << 29).start();
    }

    public static void solve() {

    }
}
