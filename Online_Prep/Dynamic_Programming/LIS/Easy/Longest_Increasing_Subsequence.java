package Dynamic_Programming.LIS.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Longest_Increasing_Subsequence {
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

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        out.println(getLISOptimized(arr, n));
        out.close();
    }

    /**
     * Find the length of Longest Increasing Subsequence using dynamic programming
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     * @param arr Input array
     * @param n Length of array
     * @return Length of longest increasing subsequence
     */
    private static int getLIS(int[] arr, int n) {
        // dp[i] stores length of LIS ending at index i
        int[] dp = new int[n];
        // parent[i] stores previous element's index in the LIS ending at i
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        // Initialize all LIS lengths as 1
        Arrays.fill(dp, 1);
        int result = dp[0];

        // For each position, look at all previous positions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If current element is greater than previous element
                // and including it gives a longer subsequence
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
        }

        // Find the position with maximum LIS length
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (result < dp[i]) {
                result = dp[i];
                pos = i;
            }
        }

        // Reconstruct the LIS using parent array
        List<Integer> lis = new ArrayList<>();
        while (pos != -1) {
            lis.add(arr[pos]);
            pos = parent[pos];
        }
        Collections.reverse(lis);
        return result;
    }

    /**
     * Alternative O(n^2) solution to find length of Longest Increasing Subsequence
     * This approach maintains a sorted array of LIS elements at each length
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     * @param arr Input array
     * @param n Length of array
     * @return Length of longest increasing subsequence
     */
    private static int getLISPrefix(int[] arr, int n) {
        int INF = Integer.MAX_VALUE;
        // dp[i] stores smallest last element of an increasing subsequence of length i
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = -INF;  // Base case: empty subsequence

        // For each element in array
        for (int i = 0; i < n; i++) {
            // Try to place current element as last element of subsequences of different lengths
            for (int l = 1; l <= n; l++) {
                // If current element can extend subsequence of length l-1
                if (dp[l - 1] < arr[i] && arr[i] < dp[l]) {
                    dp[l] = arr[i];
                }
            }
        }

        // Find the maximum length subsequence by scanning from right
        int ans = 0;
        for (int i = n; i >= 0; i--) {
            if (dp[i] != INF) {
                ans = i;
                break;
            }
        }

        return ans;
    }

    // Lets see the optimized way to solve this problem
    private static int getLISOptimized(int[] arr, int n) {
        int INF = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = -INF;

        for (int i = 0; i < n; i++) {
            int idx = Arrays.binarySearch(dp, arr[i]);
            if (idx < 0) {
                idx = -idx - 1;
            }
            dp[idx] = arr[i];
        }

        int ans = 0;
        for (int i = n; i >= 0; i--) {
            if (dp[i] != INF) {
                ans = i;
                break;
            }
        }

        return ans;
    }

    // Lets get the path of the LIS from the optimized solution
    private static List<Integer> getLISPath(int[] arr, int n) {
        int INF = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dp, INF);
        Arrays.fill(parent, -1);
        dp[0] = -INF;

        for (int i = 0; i < n; i++) {
            // Search for arr[i] in dp array. If found, returns index. If not found, returns (-(insertion point) - 1)
            int idx = Arrays.binarySearch(dp, arr[i]);
            // If element not found (idx < 0), calculate the insertion point where it should go
            if (idx < 0) {
                idx = -idx - 1; // Convert the negative index to proper insertion position
            }
            dp[idx] = arr[i];
            parent[idx] = idx > 0 ? idx - 1 : -1;
        }

        int ans = 0;
        for (int i = n; i >= 0; i--) {
            if (dp[i] != INF) {
                ans = i;
                break;
            }
        }

        List<Integer> lis = new ArrayList<>();
        int pos = ans;
        while (parent[pos] != -1) {
            lis.add(dp[pos]);
            pos = parent[pos];
        }
        Collections.reverse(lis);
        return lis;
    }
}
