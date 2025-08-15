// package Dynamic_Programming.Knapsack.Zero_one.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Treasure_hunter {
    static PrintWriter out;
    static Map<Integer, Integer> dp = new HashMap<>();

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
        Map<Integer, Integer> gems = new HashMap<>();
        int n = sc.nextInt();
        int d = sc.nextInt();
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            int a = sc.nextInt();
            max = a;
            gems.put(a, gems.getOrDefault(a, 0) + 1);
        }
        out.println(getGemsCount(d, d, max, gems));
        out.close();

    }

    private static int getGemsCount(int current, int d, int max, Map<Integer, Integer> gems) {
        if (current > max || d <= 0) {
            return 0;
        }
        if (dp.get(current) != null)
            return dp.get(current);
        int collected = gems.getOrDefault(current, 0);
        int maxGems = collected;
        for (int nextJump = d - 1; nextJump <= d + 1; nextJump++) {
            maxGems = Math.max(maxGems, collected + getGemsCount(current + nextJump, nextJump, max, gems));
        }
        dp.put(current, maxGems);
        return maxGems;
    }
}
