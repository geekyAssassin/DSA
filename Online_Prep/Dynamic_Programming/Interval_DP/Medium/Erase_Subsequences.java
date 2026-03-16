package Dynamic_Programming.Interval_DP.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Erase_Subsequences {
    static PrintWriter out;
    static int[][] subsequence;

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

        String s, t;
        s = sc.nextLine();
        t = sc.nextLine();
        out.println(canForm(s, t));
    }

    // Main function to check if t can be split into two subsequences of s
    public static boolean canForm(String s, String t) {
        int m = t.length();

        // Precompute nxt array
        int[][] nxt = buildNextTable(s);

        // Try all splits of t into a + b
        for (int i = 1; i <= m; i++) {
            String a = t.substring(0, i); // first part
            String b = t.substring(i); // second part
            if (checkDisjoint(s, a, b, nxt)) {
                return true;
            }
        }
        return false;
    }

    // Step 1: Build the next occurrence table
    private static int[][] buildNextTable(String s) {
        int n = s.length();
        int[][] nxt = new int[n + 2][26];

        // Initialize with "not found" value = n+1
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(nxt[i], n + 1);
        }

        // Fill from right to left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                nxt[i][j] = nxt[i + 1][j]; // copy from next
            }
            nxt[i][s.charAt(i) - 'a'] = i + 1; // store next occurrence
        }
        return nxt;
    }

    // Step 2: Check if a+b can be subsequences of s using DP
    private static boolean checkDisjoint(String s, String a, String b, int[][] nxt) {
        int n = s.length();
        int lenA = a.length();
        int lenB = b.length();

        // dp[i][j] = minimum index in s after matching a[0..i-1], b[0..j-1]
        int[][] dp = new int[lenA + 1][lenB + 1];
        for (int[] row : dp)
            Arrays.fill(row, n + 1);

        dp[0][0] = 0; // nothing matched at index 0

        for (int i = 0; i <= lenA; i++) {
            for (int j = 0; j <= lenB; j++) {
                if (i == 0 && j == 0)
                    continue;

                // Extend with b[j-1]
                if (j > 0 && dp[i][j - 1] <= n) {
                    int nextPos = nxt[dp[i][j - 1]][b.charAt(j - 1) - 'a'];
                    dp[i][j] = Math.min(dp[i][j], nextPos);
                }

                // Extend with a[i-1]
                if (i > 0 && dp[i - 1][j] <= n) {
                    int nextPos = nxt[dp[i - 1][j]][a.charAt(i - 1) - 'a'];
                    dp[i][j] = Math.min(dp[i][j], nextPos);
                }
            }
        }
        // If dp[lenA][lenB] is within string, we succeeded
        return dp[lenA][lenB] <= n;
    }
}
