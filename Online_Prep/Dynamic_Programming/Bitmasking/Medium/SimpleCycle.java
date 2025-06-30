import java.io.*;
import java.util.*;

public class SimpleCycle {
    static boolean[][] isEdge;
    static int n;
    static int start;
    static long[][] dp;

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
    }

    public static void main(String[] args) {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        int m = sc.nextInt();
        isEdge = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            isEdge[u][v] = true;
            isEdge[v][u] = true;
        }

        long totalCycles = 0;
        dp = new long[n][1 << n];

        for (start = 0; start < n; start++) {
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            totalCycles += dfs(start, 1 << start, 1);
        }

        System.out.println(totalCycles / 2);
    }

    static long dfs(int pos, int vis, int sz) {
        if (dp[pos][vis] != -1) {
            return dp[pos][vis];
        }
        long ans = 0;
        if (sz > 2 && isEdge[pos][start]) {
            ans++;
        }
        for (int i = start; i < n; i++) {
            if ((vis & (1 << i)) != 0)
                continue;
            if (isEdge[pos][i]) {
                ans += dfs(i, vis | (1 << i), sz + 1);
            }
        }
        dp[pos][vis] = ans;
        return ans;
    }
}
