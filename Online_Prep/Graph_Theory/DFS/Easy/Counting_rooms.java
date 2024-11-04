
import java.util.Scanner;
 
public class Counting_rooms {
    static int[] neighborX = { 0, 0, 1, -1 };
    static int[] neighborY = { 1, -1, 0, 0 };
    static int totalRooms = 0;
 
    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    solve();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Main", 1 << 26).start();
    }

    private static void solve() {
        Scanner sc = new Scanner(System.in);
 
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline character
 
        char[][] map = new char[n][m];
        boolean[][] visited = new boolean[n][m];
 
        // Read the map from input
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }
 
        // Process the map to count the number of rooms
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '.' && !visited[i][j]) {
                    dfsTraversal(map, visited, i, j, n, m);
                    totalRooms++; // Increment count of rooms each time a new DFS starts
                }
            }
        }
 
        System.out.println(totalRooms);
    }
 
    private static void dfsTraversal(char[][] map, boolean[][] visited, int x, int y, int rows, int columns) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int new_x = x + neighborX[i];
            int new_y = y + neighborY[i];
            if (isValid(new_x, new_y, rows, columns, map)) {
                if (!visited[new_x][new_y]) {
                    dfsTraversal(map, visited, new_x, new_y, rows, columns);
                }
            }
        }
    }
 
    private static boolean isValid(int y, int x, int rows, int columns, char[][] map) {
        if (y < 0)
            return false;
        if (x < 0)
            return false;
        if (y >= rows)
            return false;
        if (x >= columns)
            return false;
        if (map[y][x] == '#')
            return false;
        return true;
    }
}