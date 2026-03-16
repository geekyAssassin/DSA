package BackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sudoku_Solver {
    Map<Integer, List<Integer>> rowMap = new HashMap<>();
    Map<Integer, List<Integer>> colMap = new HashMap<>();
    Map<Integer, List<Integer>> subRigidMatrix = new HashMap<>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    List<Integer> list = rowMap.getOrDefault(i, new ArrayList<>());
                    list.add(board[i][j] - '0');
                    rowMap.put(i, list);
                    list = colMap.getOrDefault(j, new ArrayList<>());
                    list.add(board[i][j] - '0');
                    colMap.put(j, list);
                    int index = (i / 3) * 3 + (j / 3);
                    list = subRigidMatrix.getOrDefault(index, new ArrayList<>());
                    list.add(board[i][j] - '0');
                    subRigidMatrix.put(index, list);
                }
            }
        }
        solveSudokuUtil(0, 0, board);
    }

    private boolean solveSudokuUtil(int i, int j, char[][] board) {
        if (isSolved(board))
            return true;
        for (int row = i; row < 9; row++) {
            for (int col = (row == i ? j : 0); col < 9; col++) {
                if (board[row][col] == '.') {
                    for (int k = 1; k <= 9; k++) {
                        int index = (row / 3) * 3 + (col / 3);
                        if (rowMap.getOrDefault(row, new ArrayList<>()).contains(k)
                                || colMap.getOrDefault(col, new ArrayList<>()).contains(k)
                                || subRigidMatrix.getOrDefault(index, new ArrayList<>()).contains(k))
                            continue;
                        List<Integer> list = rowMap.getOrDefault(row, new ArrayList<>());
                        list.add(k);
                        rowMap.put(row, list);
                        list = colMap.getOrDefault(col, new ArrayList<>());
                        list.add(k);
                        colMap.put(col, list);
                        list = subRigidMatrix.getOrDefault(index, new ArrayList<>());
                        list.add(k);
                        subRigidMatrix.put(index, list);
                        board[row][col] = (char) ('0' + k);
                        if (solveSudokuUtil(row, col + 1, board))
                            return true;
                        board[row][col] = '.';
                        list = rowMap.getOrDefault(row, new ArrayList<>());
                        list.remove(Integer.valueOf(k));
                        rowMap.put(row, list);
                        list = colMap.getOrDefault(col, new ArrayList<>());
                        list.remove(Integer.valueOf(k));
                        colMap.put(col, list);
                        list = subRigidMatrix.getOrDefault(index, new ArrayList<>());
                        list.remove(Integer.valueOf(k));
                        subRigidMatrix.put(index, list);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSolved(char[][] board) {
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                if (board[row][col] == '.')
                    return false;
        return true;
    }

}
