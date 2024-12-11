package Graph_Theory.Shortest_Path.Floyd_Warshall.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Roads_In_Berland {
    static PrintWriter out;
    static long[][] dist;

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

        int n, q;
        n = sc.nextInt();

        dist = new long[n + 1][n + 1];
        long sum = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                dist[i][j] = sc.nextLong();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++)
                sum += dist[i][j];
        }

        q = sc.nextInt();
        ArrayList<Long> result = new ArrayList<>();

        for (int i = 1; i <= q; i++) {
            int source = sc.nextInt();
            int target = sc.nextInt();
            long cost = sc.nextLong();

            if (dist[source][target] <= cost) {
                result.add(sum);
                continue;
            }

            dist[source][target] = cost;
            dist[target][source] = cost;

            calculateShortestPath(n, source);
            calculateShortestPath(n, target);

            sum = 0;
            for (int k = 1; k <= n; k++) {
                for (int j = k + 1; j <= n; j++)
                    sum += dist[k][j];
            }
            result.add(sum);

        }

        for (long element : result) {
            out.println(element);
        }

        out.close();
    }

    private static void calculateShortestPath(int n, int k) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
}
