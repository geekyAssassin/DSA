
public class Segment_tree_2D {
    int rows, cols;
    int[][] tree;
    int[][] matrix;

    Segment_tree_2D(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.tree = new int[4 * rows][4 * cols];
        build(matrix, 1, 0, rows - 1);
    }

    private void build(int[][] matrix, int outerNode, int rowStart, int rowEnd) {
        if (rowStart == rowEnd) {
            buildInner(outerNode, 1, 0, cols - 1, matrix[rowStart]);
            return;
        }
        int mid = (rowEnd + rowStart) / 2;
        build(matrix, 2 * outerNode, rowStart, mid);
        build(matrix, 2 * outerNode + 1, mid + 1, rowEnd);

        mergeInner(outerNode, 1, 0, cols - 1);
    }

    private void mergeInner(int outerNode, int innerNode, int colStart, int colEnd) {
        if (colStart == colEnd) {
            tree[outerNode][innerNode] = tree[2 * outerNode][innerNode] + tree[2 * outerNode + 1][innerNode];
            return;
        }
        int mid = (colStart + colEnd) / 2;
        mergeInner(outerNode, 2 * innerNode, colStart, mid);
        mergeInner(outerNode, 2 * innerNode + 1, mid + 1, colEnd);
        tree[outerNode][innerNode] = tree[outerNode][2 * innerNode] + tree[outerNode][2 * innerNode + 1];

    }

    private void buildInner(int outerNode, int innerNode, int colStart, int colEnd, int[] nums) {
        if (colStart == colEnd) {
            tree[outerNode][innerNode] = nums[colStart];
            return;
        }
        int mid = (colStart + colEnd) / 2;
        buildInner(outerNode, 2 * innerNode, colStart, mid, nums);
        buildInner(outerNode, 2 * innerNode + 1, mid + 1, colEnd, nums);
        tree[outerNode][innerNode] = tree[outerNode][2 * innerNode] + tree[outerNode][2 * innerNode + 1];

    }

    public int query(int r1, int c1, int r2, int c2) {
        return query(1, 0, rows - 1, r1, c1, r2, c2);
    }

    private int query(int outerNode, int rowStart, int rowEnd, int r1, int c1, int r2, int c2) {
        if (r2 < rowStart || rowEnd < r1)
            return 0;
        if (r1 <= rowStart && rowEnd <= r2)
            return queryInner(outerNode, 1, 0, cols - 1, c1, c2);
        int mid = (rowStart + rowEnd) / 2;
        return query(2 * outerNode, rowStart, mid, r1, c1, r2, c2)
                + query(2 * outerNode + 1, mid + 1, rowEnd, r1, c1, r2, c2);
    }

    private int queryInner(int outerNode, int innerNode, int colStart, int colEnd, int left, int right) {
        if (right < colStart || colEnd < left)
            return 0;
        if (left <= colStart && colEnd <= right)
            return tree[outerNode][innerNode];
        int mid = (colStart + colEnd) / 2;
        return queryInner(outerNode, 2 * innerNode, colStart, mid, left, right)
                + queryInner(outerNode, 2 * innerNode + 1, mid + 1, colEnd, left, right);
    }

    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        update(1, 0, rows - 1, row, col, diff);
    }

    private void update(int outerNode, int rowStart, int rowEnd, int row, int col, int diff) {
        if (rowStart == rowEnd) {
            updateInner(outerNode, 1, 0, cols - 1, col, diff);
            return;
        }
        int mid = (rowStart + rowEnd) / 2;
        if (row <= mid)
            update(2 * outerNode, rowStart, mid, row, col, diff);
        else
            update(2 * outerNode + 1, mid + 1, rowEnd, row, col, diff);

        updateInner(outerNode, 1, 0, cols - 1, col, diff);
    }

    void updateInner(int outerNode, int innerNode,
            int colStart, int colEnd, int col, int diff) {
        if (colStart == colEnd) {
            tree[outerNode][innerNode] += diff;
            return;
        }
        int mid = (colStart + colEnd) / 2;
        if (col <= mid)
            updateInner(outerNode, 2 * innerNode, colStart, mid, col, diff);
        else
            updateInner(outerNode, 2 * innerNode + 1, mid + 1, colEnd, col, diff);

        tree[outerNode][innerNode] = tree[outerNode][2 * innerNode]
                + tree[outerNode][2 * innerNode + 1];
    }

}
