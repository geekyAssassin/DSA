package CP31.CP_1400;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Jumping_Through_Segments {
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

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] start = new int[n];
            int[] end = new int[n];
            for (int i = 0; i < n; i++) {
                start[i] = sc.nextInt();
                end[i] = sc.nextInt();
            }
            out.println(getMinK(n, start, end));
        }
        out.close();
    }

    private static int getMinK(int n, int[] start, int[] end) {
        int low = 0, high = 1_000_000_000; // binary search for minimal feasible k
        while (low < high) {
            int mid = (low + high) >>> 1; // candidate k (unsigned right shift to avoid overflow)
            if (isReachableWithJump(n, mid, start, end)) {
                high = mid; // k works; try smaller
            } else {
                low = mid + 1; // k too small; go higher
            }
        }
        return low;
    }

    private static boolean isReachableWithJump(int n, int mid, int[] start, int[] end) {
        int currentMinPosition = 0;
        int currentMaxPosition = 0;
        for (int i = 0; i < n; i++) {
            currentMaxPosition += mid;
            currentMinPosition -= mid;

            int reachableStart = Math.max(currentMinPosition, start[i]);
            int reachableEnd = Math.min(currentMaxPosition, end[i]);

            if (reachableStart > reachableEnd) {
                return false;
            }

            currentMinPosition = reachableStart;
            currentMaxPosition = reachableEnd;
        }
        return true;
    }
}
