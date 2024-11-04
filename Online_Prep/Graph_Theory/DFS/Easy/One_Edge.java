

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class One_Edge {

    static ArrayList<Integer>[] graph;
    static PrintWriter out;
    static boolean[] visited;
    static int size = 0;

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String s = br.readLine();
                    if (s == null)
                        return null;
                    st = new StringTokenizer(s);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            String s = next();
            if (s == null)
                return -1;
            return Integer.parseInt(s);
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    solve();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Main", 1 << 26).start();
    }

    private static void solve() {
        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        visited = new boolean[n + 1];
        long sum = 0;
        long square = 0;
        for (int i = 1; i <= n; i++) {
            size = 0;
            dfsTraversal(i);
            sum += size;
            square += size * size;
        }

        out.println((sum * sum - square) / 2);
        out.close();
    }

    private static void dfsTraversal(int i) {
        if (visited[i])
            return;
        visited[i] = true;
        size++;
        for (int src : graph[i]) {
            dfsTraversal(src);
        }
    }
}
