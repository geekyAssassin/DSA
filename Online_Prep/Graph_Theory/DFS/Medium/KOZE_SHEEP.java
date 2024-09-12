package Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class KOZE_SHEEP {
    static PrintWriter out;
    static int totalWolves, currentWolves = 0;
    static int totalSheeps, currentSheeps = 0;

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

        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] fence = new char[n][m];
        int[][] visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                fence[i][j] = line.charAt(j);
                if (fence[i][j] == 'k')
                    totalSheeps++;
                if (fence[i][j] == 'v')
                    totalWolves++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (fence[i][j] == 'k' && visited[i][j] == 0) {
                    currentSheeps = 0;
                    currentWolves = 0;
                    dfsTraversal(i, j, n, m, fence, visited);
                    if (currentSheeps > currentWolves)
                        totalWolves -= currentWolves;
                    else
                        totalSheeps -= currentSheeps;
                }
            }
        }
        out.println(totalSheeps + " " + totalWolves);
        out.close();
    }

    private static void dfsTraversal(int i, int j, int n, int m, char[][] fence, int[][] visited) {
            visited[i][j] = 1;
        if (fence[i][j] == 'k')
            currentSheeps++;
        if (fence[i][j] == 'v')
            currentWolves++;
        if (isValid(i + 1, j, n, m) && fence[i + 1][j] != '#' && visited[i + 1][j] == 0)
            dfsTraversal(i + 1, j, n, m, fence, visited);
        if (isValid(i - 1, j, n, m) && fence[i - 1][j] != '#' && visited[i - 1][j] == 0)
            dfsTraversal(i - 1, j, n, m, fence, visited);
        if (isValid(i, j + 1, n, m) && fence[i][j + 1] != '#' && visited[i][j + 1] == 0)
            dfsTraversal(i, j + 1, n, m, fence, visited);
        if (isValid(i, j - 1, n, m) && fence[i][j - 1] != '#' && visited[i][j - 1] == 0)
            dfsTraversal(i, j - 1, n, m, fence, visited);
    }

    private static boolean isValid(int i, int j, int n, int m) {
        return ((i >= 0 && i < n) && (j >= 0 && j < m));
    }
}
