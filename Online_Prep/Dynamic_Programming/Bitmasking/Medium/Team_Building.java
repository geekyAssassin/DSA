import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Team_Building {

    /* ---------- fast input ---------- */
    private static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
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

    /* ---------- main ---------- */
    public static void main(String[] args) {
        new Thread(null, Team_Building::solve, "Main", 1 << 26).start(); // 64 MB stack
    }

    private static void solve() {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        /* one test‑case (problem statement has t = 1) */
        int n = sc.nextInt();
        int p = sc.nextInt();
        int k = sc.nextInt();

        /* a[i][0] = audience strength, a[i][j] = player strength for position j‑1 */
        int[][] a = new int[n][p + 1];
        for (int i = 0; i < n; i++)
            a[i][0] = sc.nextInt();
        for (int i = 0; i < n; i++)
            for (int j = 1; j <= p; j++)
                a[i][j] = sc.nextInt();

        Arrays.sort(a, (x, y) -> y[0] - x[0]); // descending by audience strength

        /* baseline audience = first k people */
        long base = 0;
        for (int i = 0; i < k; i++)
            base += a[i][0];

        int FULL = 1 << p; // mask size (≤ 128)

        /*
         * dp[i][mask] : best extra gain after processing person i with "mask" seats
         * empty
         */
        long[][] dp = new long[n][FULL];
        for (long[] row : dp)
            Arrays.fill(row, -1);

        /* ---- initialise row 0 ---- */
        dp[0][FULL - 1] = 0; // keep person‑0 in audience
        for (int pos = 0; pos < p; pos++) {
            int state = (FULL - 1) ^ (1 << pos); // seat "pos" becomes taken
            long addBack = (k < n ? a[k][0] : 0);
            dp[0][state] = a[0][pos + 1] - a[0][0] + addBack;
        }

        /* ---- main DP ---- */
        for (int i = 1; i < n; i++) {
            for (int state = 0; state < FULL; state++) {

                /* option 0: skip person i */
                dp[i][state] = dp[i - 1][state];

                int empty = Integer.bitCount(state); // seats still free
                int used = p - empty; // seats already filled
                int windowEnd = k + used - 1; // last idx in baseline audience

                /* option 1: put person i into an empty seat */
                for (int pos = 0; pos < p; pos++) {
                    if ((state & (1 << pos)) != 0)
                        continue; // seat not empty
                    int prev = state | (1 << pos); // mask before filling this seat

                    long prevVal = dp[i - 1][prev];
                    if (prevVal == -1)
                        continue; // unreachable

                    long swap = 0;
                    if (i < windowEnd) // i currently in baseline audience
                        swap = -a[i][0] + a[windowEnd][0];

                    long cand = prevVal + a[i][pos + 1] + swap;
                    dp[i][state] = Math.max(dp[i][state], cand);
                }
            }
        }

        long best = 0;
        for (int i = 0; i < n; i++)
            best = Math.max(best, base + dp[i][0]);
        out.println(best);
        out.flush();
    }
}
