package Dynamic_Programming.Linear_DP.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Minimizing_coins {

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

        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);

        int n, target;

        n = sc.nextInt();
        target = sc.nextInt();

        int[] coins = new int[n];
        int[] dp = new int[target + 1];

        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        for (int i = 0; i <= target; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        int result = calculateMinimumCoins(coins, dp, n, target);
        if (result == Integer.MAX_VALUE)
            out.println("-1");
        else
            out.println(result);

        out.close();

    }

    private static int calculateMinimumCoins(int[] coins, int[] dp, int n, int target) {
        dp[0] = 0;
        for (int i = 1; i <= target; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);

            }
        }
        return dp[target];
    }
}
