package BackTracking;

import java.util.ArrayList;

public class Rat_in_a_maze {
    static String direction = "DLRU";

    private static boolean isValid(int x, int y, int m, int n, int[][] maze) {
        return (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 1);
    }

    private static void solve(int m, int n, int[][] maze) {
        int[] x = { 1, 0, 0, -1 };
        int[] y = { 0, -1, 1, 0 };
        ArrayList<String> result = new ArrayList<>();
        StringBuilder currPAth = new StringBuilder();
        solveUtils(0, 0, m, n, maze, result, currPAth, x, y);
        for (String string : result) {
            System.out.println(string);
        }
    }

    private static void solveUtils(int current_row, int current_column, int rows, int columns, int[][] maze,
            ArrayList<String> result,
            StringBuilder currPAth, int[] dir_x, int[] dir_y) {
        if (current_row == rows - 1 && current_column == columns - 1) {
            result.add(currPAth.toString());
            return;
        }
        maze[current_row][current_column] = 0;
        for (int k = 0; k < 4; k++) {
            int next_row = current_row + dir_x[k];
            int nexT_column = current_column + dir_y[k];
            if (isValid(next_row, nexT_column, rows, columns, maze)) {
                currPAth.append(direction.charAt(k));
                solveUtils(next_row, nexT_column, rows, columns, maze, result, currPAth, dir_x, dir_y);
                currPAth.deleteCharAt(currPAth.length() - 1);
            }
        }
        maze[current_row][current_column] = 1;
    }

    public static void main(String[] args) {
        int[][] maze = { { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 } };
        int n = 4, m = 4;
        solve(m, n, maze);
    }

}
