package BackTracking;

public class N_Queens {
    private static void solve(int[][] board, int m, int n) {
        solveNUtils(board, 0);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    private static boolean solveNUtils(int[][] board, int nthQueen) {
        if (nthQueen >= 4)
            return true;
        for (int row = 0; row < 4; row++) {
            if (isSafe(board, row, nthQueen)) {
                board[row][nthQueen] = 1;
                if(solveNUtils(board, nthQueen+1))
                    return true;
                board[row][nthQueen] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col) {
        for (int column = 0; column < 4; column++) {
            if (board[row][column] == 1)
                return false;
        }
        for (int i = 0; i < 4; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }
        // Upper Diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Lower Diagonal on right side
        for (int i = row, j = col; i < 4 && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int board[][] = { { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 } };
        int n = 4, m = 4;
        solve(board, m, n);
    }

}
