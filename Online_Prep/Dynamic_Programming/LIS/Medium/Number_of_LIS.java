import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Number_of_LIS {
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
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] dp = new int[n];
        int[] count = new int[n];
        int maxLength = 0;

        out.println(getNumberOfLIS(nums, dp, count, maxLength, n));
    }

    private static int getNumberOfLIS(int[] nums, int[] dp, int[] count, int maxLength, int n) {
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1; // Each element is a subsequence of length 1
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) { // Check if we found a longer subsequence
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; // Reset count to count of j
                    } else if (dp[j] + 1 == dp[i]) { // If we found another subsequence of the same length
                        count[i] += count[j]; // Add the count of j to i
                    }
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (maxLength < dp[i])
                maxLength = dp[i];
        }

        int ways = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLength) {
                ways += count[i]; // Sum counts of all LIS of maximum length
            }
        }
        return ways;
    }

    // Test Cases to practice:
    // [5,0,2,9,5,2,2,6,3,7]
    // [6,5,6,5,5,2,5,1,9,4]
    // [55,-53,98,14,100,-4,10,98,-99,-35,14,55,71,-67,-25,62,-80,-60,-25,-84]
    // [-59,-40,87,95,4,19,35,81,-50,-76,49,-85,-51,-1,-14,51,59,-31,85,-86]
}
