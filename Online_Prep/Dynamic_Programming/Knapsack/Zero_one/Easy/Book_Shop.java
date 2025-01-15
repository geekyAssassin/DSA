package Dynamic_Programming.Knapsack.Zero_one.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Book_Shop {
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
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] books = new int[n];
        int[] pages = new int[n];

        for (int i = 0; i < n; i++) {
            books[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        int[][] dp = new int[n + 1][target + 1];

        out.println(computeMaximumProfit(books, pages, n, target, dp));
        out.close();
    }

    private static int computeMaximumProfit(int[] books, int[] pages, int n, int target, int[][] dp) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (j < books[i - 1])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(pages[i - 1] + dp[i - 1][j - books[i - 1]], dp[i - 1][j]);
            }
        }
        return dp[n][target];
    }

}
