import java.util.Arrays;
import java.util.Scanner;

public class Segment_Tree_Lazy_Propagation {
    public static void build(int[] A, int node, int start, int end, int[] tree) {
        if (start == end) {
            tree[node] = A[start];
        } else {
            int mid = (start + end) / 2;
            build(A, 2 * node, start, mid, tree);
            build(A, 2 * node + 1, mid + 1, end, tree);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    public static void updateRange(int[] A, int node, int start, int end, int l, int r, int[] tree, int val,
            int[] lazy) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * val; // take the update of all the child nodes as well
            if (start != end) { // Not a leaf node
                lazy[2 * node] += val;
                lazy[2 * node + 1] += val;
            }
            lazy[node] = 0; // Resetting the node
            return;
        }
        if (start > end || start > r || end < l)
            return;
        if (start >= l && end <= r) {
            tree[node] += (end - start + 1) * val;
            if (start != end) {
                lazy[2 * node] += val;
                lazy[2 * node + 1] += val;
            }
            return;
        }
        int mid = (start + end) / 2;
        updateRange(A, 2 * node, start, mid, l, r, tree, val, lazy);
        updateRange(A, 2 * node + 1, mid + 1, end, l, r, tree, val, lazy);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    int queryRange(int[] A, int node, int l, int r, int start, int end, int[] tree, int[] lazy) {
        if (start > end || start > r || end < l)
            return 0;
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node] += lazy[node];
                lazy[2 * node + 1] = lazy[node];
            }
            lazy[node] = 0;
        }
        if (start >= l && end <= r)
            return tree[node];
        int mid = (start + end) / 2;
        int p1 = queryRange(A, 2 * node, l, r, start, mid, tree, lazy);
        int p2 = queryRange(A, 2 * node + 1, l, r, mid + 1, end, tree, lazy);
        return p1 + p2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int [] A = {1,3,5,7,9,11};
        int[] tree = new int[24];
        int[] lazy = new int[24];
        Arrays.fill(tree, 0);
        Arrays.fill(lazy,0);

        build(A, 1, 0, 5, tree);
        for (int i : tree) {
            System.out.print(i + " ");
        }
        System.out.println();
        updateRange(A, 1, 0, 5, 3, 5, tree, 10, lazy);
        for (int i : tree) {
            System.out.print(i + " ");
        }
    }
}
