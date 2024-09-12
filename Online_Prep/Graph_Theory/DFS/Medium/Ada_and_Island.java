package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Ada_and_Island {
    static int hashCount = 0;
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
        out = new PrintWriter(System.out);
        FastReader sc = new FastReader();

        int tests = sc.nextInt();
        while (tests-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            char[][] map = new char[n][m];
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
            int[][] visited = new int[n][m];
            long p = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '#' && visited[i][j] == 0) {
                        hashCount = 0;
                        dfsTraversal(i, j, map, visited, n, m);
                        p += 1 * hashCount * hashCount;
                    }
                }
            }
            long q = n * m;
            long div = gcd(p, q);
            p = p / div;
            q = q / div;

            if (q == 1)
                out.println(p);
            else
                out.println(p + " / " + q);
        }
        out.close();
    }

    private static long gcd(long a, long b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }
        return a + b;
    }

    private static void dfsTraversal(int row, int column, char[][] map, int[][] visited, int rows,
            int columns) {
        visited[row][column] = 1;
        hashCount++;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i * i + j * j == 1) {
                    if (good(row + i, column + j, rows, columns) && map[row + i][column + j] == '#'
                            && visited[row + i][column + j] == 0) {
                        dfsTraversal(row + i, column + j, map, visited, rows, columns);
                    }
                }
            }
        }
    }

    private static boolean good(int newRow, int newColumn, int n, int m) {
        return newRow >= 0 && newRow < n && newColumn >= 0 && newColumn < m;
    }
}
