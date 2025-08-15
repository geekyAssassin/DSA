package Dynamic_Programming.Knapsack.Zero_one.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Two_Sets_2s {

    static long MOD = 1000000007;
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
        int sum = (n * (n + 1)) / 2;
        if (sum % 2 != 0) {
            out.println(0);
        } else {
            out.println(getCountOfSets(n, sum / 2));
        }
        out.close();
    }

    private static long getCountOfSets(int n, int sum) {
        long[] dp = new long[sum + 1];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = sum; j >= i; j--) {
                dp[j] += dp[j - i];
                dp[j] %= MOD;
            }
        }
        return dp[sum];
    }
}
