import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Round_Subset {
    static PrintWriter out;
    static int[][][] dp;

    // Fast input reading
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
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        out.println(getMaximumRoundness(arr, n, k));
        out.close();
    }

    private static int getMaximumRoundness(int[] arr, int n, int k) {
        // Calculate the maximum possible number of trailing zeros across all inputs
        int maxFives = 0;
        for (int num : arr) {
            int temp = num;
            int fiveCount = 0;
            while (temp % 5 == 0) {
                fiveCount++;
                temp /= 5;
            }
            maxFives = Math.max(maxFives, fiveCount);
        }

        // dp initialization: dp[i % 2][j][l] => max sum of powers of 2 for selecting j
        // elements
        // with total powers of 5 as l.
        dp = new int[2][k + 1][maxFives * k + 1];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= maxFives * k; j++) {
                dp[0][i][j] = -1;
            }
        }

        dp[0][0][0] = 0; // Base case: zero elements, zero trailing zeros

        for (int i = 1; i <= n; i++) {
            int temp = arr[i - 1];
            int twoCount = 0, fiveCount = 0;
            while (temp % 2 == 0) {
                twoCount++;
                temp /= 2;
            }
            while (temp % 5 == 0) {
                fiveCount++;
                temp /= 5;
            }

            for (int j = 0; j <= k; j++) {
                for (int l = 0; l <= maxFives * k; l++) {
                    dp[i % 2][j][l] = dp[(i - 1) % 2][j][l]; // not picking the current element

                    if (l - fiveCount >= 0 && j != 0) {
                        dp[i % 2][j][l] = Math.max(dp[i % 2][j][l], twoCount + dp[(i - 1) % 2][j - 1][l - fiveCount]);
                    }
                }
            }
        }

        int ans = 0;
        // Final step: Find the maximum possible roundness
        for (int x = 0; x <= maxFives * k; x++) {
            ans = Math.max(ans, Math.min(x, dp[n % 2][k][x]));
        }
        return ans;
    }
}
