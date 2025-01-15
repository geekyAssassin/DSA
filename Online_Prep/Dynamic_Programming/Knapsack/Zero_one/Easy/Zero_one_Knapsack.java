package Dynamic_Programming.Knapsack.Zero_one.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Zero_one_Knapsack {

    static PrintWriter out;
    static int[] weight;
    static int[] cost;
    static int W, result = 0;

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
        out = new PrintWriter(System.out);
        FastReader sc = new FastReader();

        int n = sc.nextInt();
        weight = new int[n];
        cost = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            cost[i] = sc.nextInt();
        }
        W = sc.nextInt();
        int[][] dp = new int[n][W + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        result = getMaxProfits(W, n - 1, dp);
        out.println(result);
        List<Integer> items = printKnapSackItems(dp, n - 1, W);
        for (int i = 0; i < items.size(); i++) {
            out.print(items.get(i) + " ");
        }
        out.close();
    }

    public static int getMaxProfits(int W, int index, int[][] memo) {

        if (index < 0)
            return 0;

        if (memo[index][W] != -1)
            return memo[index][W];

        if (weight[index] > W) {
            memo[index][W] = getMaxProfits(W, index - 1, memo);
        } else {
            memo[index][W] = Math.max(cost[index] + getMaxProfits(W - weight[index], index - 1, memo),
                    getMaxProfits(W, index - 1, memo));
        }

        return memo[index][W];
    }

    public static List<Integer> printKnapSackItems(int[][] dp, int n, int W) {
        List<Integer> items = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            if (dp[i][W] != dp[i - 1][W]) {
                items.add(i - 1);
                W -= weight[i];
            }
        }
        return items;

    }

}
