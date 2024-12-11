package Graph_Theory.Shortest_Path.Floyd_Warshall.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Shortest_Routes2 {
    static PrintWriter out;
    static long[][] dist;
    final static long INF = Long.MAX_VALUE;

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

    private static void solve() {
        out = new PrintWriter(System.out);
        FastReader sc = new FastReader();

        int n, m, q;
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();

        dist = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
        }

        for (int i = 1; i <= m; i++) {
            int source = sc.nextInt();
            int target = sc.nextInt();
            long cost = sc.nextLong();

            if (dist[source][target] > cost) {
                dist[source][target] = cost;
                dist[target][source] = cost;
            }
        }

        calculateShortestPath(n);
        ArrayList<Long> result = new ArrayList<>();

        for (int i = 1; i <= q; i++) {
            int source = sc.nextInt();
            int target = sc.nextInt();
            long res = dist[source][target];
            if (res == Long.MAX_VALUE) {
                result.add(-1L);
            } else
                result.add(res);
        }

        for (long element : result) {
            out.println(element);
        }

        out.close();
    }

    private static void calculateShortestPath(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
