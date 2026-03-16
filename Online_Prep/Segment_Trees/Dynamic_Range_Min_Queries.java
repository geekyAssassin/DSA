import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Dynamic_Range_Min_Queries {

    static class SegmentTree {
        int n;
        int[] tree;
        int[] arr;

        public SegmentTree(int n, int[] arr) {
            this.n = n;
            this.arr = Arrays.copyOf(arr, n);
            // 4*n as for non powers of 2 it is always 4*n-1
            this.tree = new int[4 * n];
            Arrays.fill(tree, Integer.MAX_VALUE);
            build(1, 0, n - 1);
        }

        private void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            int mid = ((2 * end) - (end - start)) / 2;
            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);
            tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        }

        public void update(int node, int index, int start, int end, int value) {
            if (start == end) {
                arr[index] = value;
                tree[node] = value;
                return;
            }
            int mid = ((2 * end) - (end - start)) / 2;
            if (index <= mid) {
                update(2 * node, index, start, mid, value);
            } else {
                update(2 * node + 1, index, mid + 1, end, value);
            }
            tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
        }

        public int query(int node, int l, int r, int start, int end) {
            if (r < start || end < l)
                return Integer.MAX_VALUE;
            if (l <= start && end <= r)
                return tree[node];
            int mid = start + (end - start) / 2;
            int left = query(2 * node, l, r, start, mid);
            int right = query(2 * node + 1, l, r, mid + 1, end);
            return Math.min(left, right);

        }

    }

    static PrintWriter out;

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
        new Thread(null, new Runnable() {
            public void run() {
                solve();
            }
        }, "Main", 1 << 29).start();
    }

    public static void solve() {
        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);
        int n = sc.nextInt();
        int queries = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        SegmentTree tree = new SegmentTree(n, arr);
        while (queries-- > 0) {
            int q = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (q == 2) {
                a--;
                b--;
                out.println(tree.query(1, a, b, 0, n - 1));
            } else {
                a--;
                tree.update(1, a, 0, n - 1, b);
            }
        }
        out.close();
    }
}