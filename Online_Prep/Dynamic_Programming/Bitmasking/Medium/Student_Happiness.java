import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Student_Happiness {
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
        }, "Main", 1 << 28).start();
    }

    public static void solve() {
        out = new PrintWriter(System.out);
        FastReader sc = new FastReader();
        int t, n, m;
        t = sc.nextInt();
        while (t-- > 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            int[][] arr = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            int size = 1 << n;
            long[][] dp = new long[m + 1][size];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j < size; j++) {
                    dp[i][j] = 0;
                }
            }
            out.println(getMaxHappiness(arr, dp, n, m, size));
        }
        out.close();
    }

    private static long getMaxHappiness(int[][] arr, long[][] dp, int n, int m, int size) {
        for (int student = m - 1; student >= 0; student--) {
            for (int mask = 0; mask < size; mask++) {
                long best = dp[student + 1][mask];
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) == 0) {
                        best = Math.max(best, arr[student][i] + dp[student + 1][mask | (1 << i)]);
                    }
                }
                dp[student][mask] = best;
            }
        }
        return dp[0][0];
    }
}
