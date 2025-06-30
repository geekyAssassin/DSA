import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Hamiltonian_Cycle {
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
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            graph.computeIfAbsent(u, k -> new java.util.ArrayList<>()).add(v);
        }
        boolean[] visited = new boolean[n];
        List<Integer> path = new ArrayList<>();
        // out.println(hamiltonianCycleWays(0, graph, visited, n, path));
        long[][] dp = new long[n][(1 << n)];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (1 << n); j++) {
                dp[i][j] = -1;
            }
        }
        out.println(hamiltonianCycleWays(0, graph, dp, 1, n));
        out.flush();
        out.close();
    }

    private static long hamiltonianCycleWays(int currentNode, HashMap<Integer, List<Integer>> graph, long[][] dp,
            int mask, int n) {
        if (mask == (1 << n) - 1) {
            if (currentNode == n - 1) {
                return 1;
            }
            return 0;
        }
        if (dp[currentNode][mask] != -1) {
            return dp[currentNode][mask];
        }
        long count = 0;
        for (int neighbor : graph.getOrDefault(currentNode, new ArrayList<>())) {
            if ((mask & (1 << neighbor)) != 0)
                continue;
            count = (count + hamiltonianCycleWays(neighbor, graph, dp, mask | (1 << neighbor), n)) % 1000000007;
        }
        return dp[currentNode][mask] = count;

    }

    private static int hamiltonianCycleWays(int currentNode, HashMap<Integer, List<Integer>> graph, boolean[] visited,
            int n, List<Integer> path) {
        if (path.size() == n - 1 && currentNode == n - 1) {
            return 1;
        }
        visited[currentNode] = true;
        path.add(currentNode);
        int count = 0;
        for (int neighbor : graph.getOrDefault(currentNode, new ArrayList<>())) {
            if (!visited[neighbor]) {
                count += hamiltonianCycleWays(neighbor, graph, visited, n, path);
            }
        }
        visited[currentNode] = false;
        path.remove(path.size() - 1);
        return count;
    }
}
