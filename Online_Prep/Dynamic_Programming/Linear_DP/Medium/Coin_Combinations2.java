import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Coin_Combinations2 {
    static PrintWriter out;
    static int mod = 1000000007;
    static int[] dp;
    static int[] coins;
    static int n, m;

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
        n = sc.nextInt();
        m = sc.nextInt();
        coins = new int[n];
        dp = new int[m + 1];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= m; j++) {
                dp[j] = (dp[j] + dp[j - coins[i]]) % mod;
            }
        }
        out.println(dp[m]);
        out.flush();
        out.close();
    }
}
