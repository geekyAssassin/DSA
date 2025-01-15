package Dynamic_Programming.Interval_DP.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Matrix_Multiplication {
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

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        out.println(matrixMutliplicationRec(arr, 0, n - 1));
        out.close();
    }

    private static int matrixMutliplicationRec(int[] arr, int i, int j) {
        if (i + 1 == j)
            return 0;

        int res = Integer.MAX_VALUE;
        /*
         * Place the first bracket at every possible position and find the minimum cost
         * of the multiplication. The cost of multiplying two matrices is equal to the
         * product of the number of rows in the first matrix, the number of columns in
         * the
         * first matrix and the number of columns in the second matrix.
         */

        for (int k = i + 1; k < j; k++) {
            int cost = matrixMutliplicationRec(arr, i, k) + matrixMutliplicationRec(arr, k, j)
                    + arr[i] * arr[k] * arr[j];
            res = Math.min(res, cost);
        }
        return res;
    }
}
