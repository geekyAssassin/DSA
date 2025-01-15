package Dynamic_Programming.Linear_DP.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CoinCombinations_1 {
    static final int MOD = 1000000007;

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
        PrintWriter out = new PrintWriter(System.out);

        int n, target;
        n = sc.nextInt();
        target = sc.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++)
            coins[i] = sc.nextInt();

        out.println(calculateDistinctWays(coins, target));
        out.close();
    }

    private static long calculateDistinctWays(int[] coins, int target) {
        long[] dp = new long[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int x : coins) {
                if (i - x >= 0) {
                    dp[i] = (dp[i] + dp[i - x]) % MOD;
                }
            }
        }
        return dp[target];
    }

}
