package Dynamic_Programming.Knapsack.Zero_one.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Subsets_Count_With_Given_Diff {

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
        int[] arr = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        int diff = sc.nextInt();

        /*
         * S1 + S2 is the total sum array nums
         * S1 – S2 is the given diff
         * Subtracting the two equations we would get,
         * sum – diff = (S1 + S2) – (S1 – S2) = 2*S2 . So, S2 = ( sum – diff ) / 2
         */

        int target = (sum - diff) / 2;
        int[][] memo = new int[n][target + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                memo[i][j] = -1;
            }
        }

        out.println(countSubsets(arr, n - 1, 0, target, memo));
        out.close();
    }

    private static int countSubsets(int[] arr, int index, int currentSum, int targetSum, int[][] memo) {
        if (currentSum == targetSum)
            return 1;

        if (currentSum > targetSum || index < 0)
            return 0;

        if (memo[index][currentSum] != -1)
            return memo[index][currentSum];

        int include = countSubsets(arr, index - 1, currentSum + arr[index], targetSum, memo);
        int exlcude = countSubsets(arr, index - 1, currentSum, targetSum, memo);

        memo[index][currentSum] = include + exlcude;

        return memo[index][currentSum];
    }

}
