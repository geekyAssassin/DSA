package Dynamic_Programming.Linear_DP.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Add_One {
    static PrintWriter out;
    static final int MOD = 1000000007;
    static final int MAX_M = 200005;
    static long[][] dp = new long[MAX_M][10];

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

    private static void preCompute() {
        for (int i = 0; i <= 9; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < MAX_M; i++) {
            for (int j = 0; j <= 8; j++) {
                dp[i][j] = dp[i - 1][j + 1];
            }
            dp[i][9] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }

    }

    public static void solve() {
        preCompute();
        out = new PrintWriter(System.out);
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            String n = sc.next();
            int m = sc.nextInt();
            int totalLength = 0;
            for (int i = 0; i < n.length(); i++) {
                totalLength = (totalLength + ((int) dp[m][n.charAt(i) - '0'])) % MOD;
            }
            out.println(totalLength);
        }
        out.close();

    }
}
