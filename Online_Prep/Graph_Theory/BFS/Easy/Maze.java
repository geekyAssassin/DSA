package Graph_Theory.BFS.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Maze {
    static PrintWriter out;
    static char[][] path;
    static boolean[][] visited;
    static int n, m, k;

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

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        path = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                path[i][j] = line.charAt(j);
            }
        }

        bfsTraversal(0, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.print(path[i][j] + " ");
            }
            out.println();
        }
        out.close();

    }

    private static void bfsTraversal(int i, int j) {
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        queue.add(new ArrayList<>(Arrays.asList(i, j)));

        visited[i][j] = true;

        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };

        while (!queue.isEmpty()) {
            ArrayList<Integer> current_pair = queue.poll();

            for (int a = 0; a < 3; a++) {
                int current_x = current_pair.get(0) + dx[a];
                int current_y = current_pair.get(1) + dy[a];
                if (isSafe(current_x, current_y)) {
                    queue.add(new ArrayList<>(Arrays.asList(current_x, current_y)));
                }
            }

        }
    }

    private static boolean isSafe(int i, int j) {
        if (i>=0 && i < n && j>=0 && j < m && k > 0 && path[i][j] == '.' && !visited[i][j]) {
            path[i][j] = 'X';
            k--;
            return true;
        }
        return false;
    }
}
