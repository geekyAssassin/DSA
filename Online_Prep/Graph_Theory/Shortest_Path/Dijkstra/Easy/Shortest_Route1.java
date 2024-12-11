package Graph_Theory.Shortest_Path.Dijkstra.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Shortest_Route1 {
    static ArrayList<Pair>[] graph;
    static long[] dist;
    static boolean[] visited;
    static int n, m;
    static PrintWriter out;

    static class Pair {
        int node;
        long distance;

        Pair(int node, long distance) {
            this.node = node;
            this.distance = distance;
        }

    }

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
        ;
    }

    public static void solve() {

        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);

        n = sc.nextInt();
        m = sc.nextInt();

        graph = new ArrayList[n + 1];
        dist = new long[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph[u].add(new Pair(v, w));
        }

        shortestPath(1);
        for (int i = 1; i <= n; i++) {
            out.print(dist[i] + " ");
        }
        out.println();
        out.close();
    }

    private static void shortestPath(int start) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.distance));
        pq.offer(new Pair(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pair currentPair = pq.poll();
            int current_node = currentPair.node;
            if (visited[current_node])
                continue;

            visited[current_node] = true;
            for (Pair p : graph[current_node]) {
                int next_node = p.node;
                long next_distance = p.distance;
                if (!visited[next_node] && dist[current_node] + next_distance < dist[next_node]) {
                    dist[next_node] = dist[current_node] + next_distance;
                    pq.offer(new Pair(next_node, dist[next_node]));
                }

            }
        }
    }
}
