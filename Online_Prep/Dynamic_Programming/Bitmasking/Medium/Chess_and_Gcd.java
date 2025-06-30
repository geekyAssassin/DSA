import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Chess_and_Gcd {
    static PrintWriter out;

    private static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private static long maxScore(long[] a) {
        int m = a.length;
        int pairs = m >> 1;
        int states = 1 << m;

        long[][] g = new long[m][m];
        for (int i = 0; i < m; ++i)
            for (int j = i + 1; j < m; ++j)
                g[i][j] = g[j][i] = gcd(a[i], a[j]);

        long[] dp = new long[states];

        for (int mask = 0; mask < states; ++mask) {
            int k = Integer.bitCount(mask) >> 1;
            if (k >= pairs)
                continue;

            int i;
            for (i = 0; i < m; ++i)
                if ((mask & (1 << i)) == 0)
                    break;

            for (int j = i + 1; j < m; ++j) {
                if ((mask & (1 << j)) != 0)
                    continue;
                int nextMask = mask | (1 << i) | (1 << j);

                long score = (long) (k + 1) * Math.abs(a[i] - a[j]) * g[i][j];
                dp[nextMask] = Math.max(dp[nextMask], dp[mask] + score);
            }
        }
        return dp[states - 1];
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            public void run() {
                solve();
            }
        }, "Main", 1 << 29).start();
    }

    private static void solve() {
    }
}
