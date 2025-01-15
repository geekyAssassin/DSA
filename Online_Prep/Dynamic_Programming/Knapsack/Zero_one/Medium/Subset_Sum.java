package Dynamic_Programming.Knapsack.Zero_one.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Subset_Sum {
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
        int sum = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[][] memo = new int[n][sum + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                memo[i][j] = -1;
            }
        }
        out.println(subsetSum(arr, sum, n - 1, memo));

        out.close();
    }

    private static boolean subsetSum(int[] arr, int sum, int n, int[][] memo) {
        if (sum == 0) {
            return true;
        } else if (sum < 0 || n < 0) {
            return false;
        }

        if (memo[n][sum] != -1) {
            return memo[n][sum] == 1;
        }

        if (arr[n] > sum) {
            memo[n][sum] = subsetSum(arr, sum, n - 1, memo) ? 1 : 0;
        } else {
            boolean include = subsetSum(arr, sum - arr[n], n - 1, memo);
            boolean exclude = subsetSum(arr, sum, n - 1, memo);
            memo[n][sum] = include || exclude ? 1 : 0;
        }

        return memo[n][sum] == 1;
    }

}
