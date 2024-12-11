package Graph_Theory.Shortest_Path.Dijkstra.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Flight_Discounts {
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static long[][] dist;
    static PrintWriter out;

    static class Edge {
        int node;
        long distance;

        Edge(int node, long distance) {
            this.node = node;
            this.distance = distance;
        }

    }

    static class State implements Comparable<State> {
        int node;
        long cost;
        int couponUsed;

        State(int node, long cost, int couponUsed) {
            this.node = node;
            this.cost = cost;
            this.couponUsed = couponUsed;
        }

        @Override
        public int compareTo(State state) {
            return Long.compare(this.cost, state.cost);
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
    }

    public static void solve() {
        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        dist = new long[n + 1][2];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextInt();

            graph[u].add(new Edge(v, w));
        }

        shortest_Path(1, n);
        out.println(Math.min(dist[n][0], dist[n][1]));
        out.close();

    }

    private static void shortest_Path(int start, int n) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(start, 0, 0));
        dist[start][0] = 0;

        while(!pq.isEmpty()) {
            State currentState = pq.poll();
            int currentNode = currentState.node;
            int currentCouponUsed = currentState.couponUsed;
            long currentCost = currentState.cost;

            if (currentCost > dist[currentNode][currentCouponUsed]) {
                continue;
            }

            for(Edge next_edge: graph[currentNode]) {
                int next_node = next_edge.node;
                long next_distance = next_edge.distance;

                if(dist[currentNode][currentCouponUsed] + next_distance < dist[next_node][currentCouponUsed]) {
                    dist[next_node][currentCouponUsed] = dist[currentNode][currentCouponUsed] + next_distance;
                    pq.offer(new State(next_node, dist[next_node][currentCouponUsed], currentCouponUsed));
                }

                if(currentCouponUsed == 0) {
                    long discountedCost = dist[currentNode][0] + next_distance/2;
                    if(discountedCost < dist[next_node][1]) {
                        dist[next_node][1] = discountedCost;
                        pq.offer(new State(next_node, discountedCost, 1));
                    }
                    
                }
            }
        }
    }

}
