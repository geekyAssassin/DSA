package Dynamic_Programming.Knapsack.Zero_one.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Equal_Subset_Sum {

    static int[] arr;
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
        FastReader fr = new FastReader();
        out = new PrintWriter(System.out);
        int n = fr.nextInt();
        arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = fr.nextInt();
            sum += arr[i];
        }
        out.println(canPartition(arr, sum) ? "YES" : "NO");
        out.close();
    }

    public static boolean canPartition(int[] nums, int targetSum) {
        int n = nums.length;
        if (targetSum % 2 != 0)
            return false;
        else {
            int[][] memo = new int[n][(targetSum / 2) + 1];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }
            return subsetSum(nums, n - 1, targetSum / 2, memo);
        }
    }

    private static boolean subsetSum(int[] nums, int index, int sum, int[][] memo) {
        if (sum == 0)
            return true;
        else if (sum < 0 || index < 0)
            return false;
        if (memo[index][sum] != -1) {
            return memo[index][sum] == 1;
        }
        boolean include = subsetSum(nums, index - 1, sum - nums[index], memo);
        boolean exclude = subsetSum(nums, index - 1, sum, memo);
        memo[index][sum] = include || exclude ? 1 : 0;
        return memo[index][sum] == 1;
    }

}
