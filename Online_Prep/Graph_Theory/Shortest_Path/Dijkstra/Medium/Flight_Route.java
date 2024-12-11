package Graph_Theory.Shortest_Path.Dijkstra.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Flight_Route {
    static ArrayList<Edge>[] graph;
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

        State(int node, long cost) {
            this.node = node;
            this.cost = cost;
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
        int k = sc.nextInt();

        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();

            graph[u].add(new Edge(v, w));
        }

        ArrayList<Long> result = shortest_Path(1, n, k);
        for (Long route : result) {
            out.print(route+ " ");
        }
        out.close();

    }

    private static ArrayList<Long> shortest_Path(int start, int n, int k) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(start, 0));
        ArrayList<Long> result = new ArrayList<>();
        int[] count = new int[n+1];
        while (!pq.isEmpty()) {
            State currentState = pq.poll();
            int current_node = currentState.node;
            long current_distance = currentState.cost;

            count[current_node]++;

            if(count[current_node] > k)
                continue;
            
            if(current_node == n) {
                result.add(current_distance);
                if(result.size() == k)
                    break;
            }

            for(Edge nextEdge : graph[current_node]) {
                int nextNode = nextEdge.node;
                long next_distance = current_distance + nextEdge.distance;

                pq.offer(new State(nextNode, next_distance));
            }
            

        }
        return result;
    }

}
