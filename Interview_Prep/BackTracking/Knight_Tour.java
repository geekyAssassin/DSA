package BackTracking;

import java.util.*;

public class Knight_Tour {

    public static boolean isSafe(int x, int y, int m, int n, int[][] arr) {
        return (x >= 0 && y >= 0 && y < n && x < m && arr[x][y] == -1);
    }

    public static void solve(int rows, int columns, int[][] arr) {
        int[] x = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] y = { 1, 2, 2, 1, -1, -2, -2, -1 };
        arr[0][0] = 0;
        if (solveKnightTourUtils(x, y, 1, arr, rows, columns, 0, 0)) {
            print(arr, rows, columns);
        }
        else {
            System.out.println("false");
            print(arr, rows, columns);
        }
    }

    private static void print(int[][] arr, int rows, int columns) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                System.out.print(arr[x][y] + " ");
            }
            System.out.println("");
        }
    }

    private static boolean solveKnightTourUtils(int[] x, int[] y, int move, int[][] arr, int rows, int columns,
            int current_x, int current_y) {
        int k,next_x, next_y;
        if (move == rows * columns)
            return true;
        for (k = 0; k < 8; k++) {
            next_x = current_x + x[k];
            next_y = current_y + y[k];
            if (isSafe(next_x, next_y, rows, columns, arr)) {
                arr[next_x][next_y] = move;
                if (solveKnightTourUtils(x, y, move + 1, arr, rows, columns, next_x, next_y))
                    return true;
                else
                    arr[next_x][next_y] = -1;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = 8;
        int n = 8;
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = -1;
            }
        }
        solve(m, n, arr);
    }
}
