import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Removing_Digits {

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
        out = new PrintWriter(System.out);
        FastReader sc = new FastReader();

        long n = sc.nextLong();
        out.println(minSteps(n));
        out.close();

    }

    static long minSteps(long n) {
        long steps = 0;
        while (n > 0) {
            long maxDigit = maxDigit(n);
            n -= maxDigit;
            steps++;
        }
        return steps;
    }

    static long maxDigit(long n) {
        long max = 0;
        while (n > 0) {
            long digit = n % 10;
            if (digit > max) {
                max = digit;
            }
            n /= 10;
        }
        return max;
    }

    static long minStepsUsingDP(long n) {
        long[] dp = new long[(int) n+1];
        dp[0] = 0;

        for( int i=1;i<=n;i++) {
            for(char ch: String.valueOf((i)).toCharArray()) {
                int digit = ch - '0';
                dp[i] = Math.min(dp[i], dp[i-digit] + 1);
            }
        }

        return dp[(int) n];

    }
}
