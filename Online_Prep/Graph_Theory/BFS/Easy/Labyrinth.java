package Graph_Theory.BFS.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Labyrinth {

    static PrintWriter out;
    static int n, m;
    static char[][] board;
    static boolean[][] visited;
    static int[][][] path;
    static char[][] result;

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null || line.isEmpty()) {
                        return null;
                    }
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        new Thread(null, () -> solve(), "Main", 1 << 29).start();
    }

    public static void solve() {
        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);

        n = sc.nextInt();
        m = sc.nextInt();

        int startX = -1, startY = -1;
        int endX = -1, endY = -1;

        board = new char[n][m];
        visited = new boolean[n][m];
        path = new int[n][m][2];
        result = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            board[i] = line.toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    startX = i;
                    startY = j;
                } else if (board[i][j] == 'B') {
                    endX = i;
                    endY = j;
                }
            }
        }

        boolean found = bfsTraversal(startX, startY, endX, endY);

        if (found) {
            out.println("YES");
            List<Character> finalPath = new ArrayList<>();
            int x = endX;
            int y = endY;

            while (!(x == startX && y == startY)) {
                finalPath.add(result[x][y]);
                int tempX = path[x][y][0];
                int tempY = path[x][y][1];
                x = tempX;
                y = tempY;
            }

            Collections.reverse(finalPath);
            out.println(finalPath.size());
            for (char c : finalPath) {
                out.print(c);
            }
            out.println();
        } else {
            out.println("NO");
        }

        out.close();
    }

    private static boolean bfsTraversal(int startX, int startY, int endX, int endY) {
        Queue<int[]> queue = new LinkedList<>();

        int[] dx = { -1, 1, 0, 0 }; // Up, Down, Left, Right
        int[] dy = { 0, 0, -1, 1 };
        char[] directions = { 'U', 'D', 'L', 'R' };

        visited[startX][startY] = true;
        queue.add(new int[] { startX, startY });

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currX = current[0];
            int currY = current[1];

            if (currX == endX && currY == endY) {
                return true; // Found the end position
            }

            for (int i = 0; i < 4; i++) {
                int newX = currX + dx[i];
                int newY = currY + dy[i];

                if (isSafe(newX, newY)) {
                    visited[newX][newY] = true;
                    queue.add(new int[] { newX, newY });
                    path[newX][newY][0] = currX;
                    path[newX][newY][1] = currY;
                    result[newX][newY] = directions[i];
                }
            }
        }

        return false; // End position not reachable
    }

    private static boolean isSafe(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m &&
                !visited[x][y] && board[x][y] != '#';
    }
}