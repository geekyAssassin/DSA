import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class First_Min_Queries {
    static PrintWriter out;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        out = new PrintWriter(System.out);
        int t = 1;
        // t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int q = sc.nextInt();
            int[] arr = sc.readArray(n);            
            int[] queries = sc.readArray(q);
            solve(n,q,arr,queries);
        }
        out.close();
    }

    private static void solve(int n, int q, int[] arr, int[] queries) {
        SegmentTree segmentTree = new SegmentTree(n, arr);
        segmentTree.buildTree(1, 1, n);
        for(int query : queries) {
            int idx = segmentTree.queryTree(1, 1, n, query);
            if(idx!=0) {
                segmentTree.updateTree(1, 1, n, idx, query);
            }
            out.print(idx + " ");
        }
        out.println();
    }

    static class Element {
        int maxValue;
        Element(int maxValue) {
            this.maxValue = maxValue;
        }
    }

    static class SegmentTree {
        Element[] segmentTree;
        int[] arr;
        SegmentTree(int n, int[] arr) {
            segmentTree = new Element[4*n];
            this.arr = arr;
        }

        Element merge(Element A, Element B) {
            return new Element(Math.max(A.maxValue, B.maxValue));
        }

        void buildTree(int idx, int start, int end) {
            if(start == end) {
                segmentTree[idx] = new Element(arr[start-1]);
                return;
            }
            int mid = (start+end)/2;
            buildTree(2* idx, start, mid);
            buildTree(2* idx +1, mid+1, end);
            segmentTree[idx] = merge(segmentTree[2*idx], segmentTree[2*idx+1]);
        }

        void updateTree(int idx, int start, int end, int pos, int val) {
            if(pos < start || pos > end)
                return;
            if(start == end) {
                arr[start-1]-=val;
                segmentTree[idx] = new Element(arr[start-1]);
                return;
            }
            int mid = (start+end)/2;
            updateTree(2* idx, start, mid, pos, val);
            updateTree(2* idx +1, mid+1, end, pos, val);
            segmentTree[idx] = merge(segmentTree[2*idx], segmentTree[2*idx+1]);
        }

        int queryTree(int idx, int start, int end, int val) {
            if(segmentTree[idx].maxValue < val)
                return 0;
            if(start == end)
                return start;
             int mid = (start+end)/2;
             if(segmentTree[idx*2].maxValue >= val)
                return queryTree(2*idx, start, mid, val);
            else {
                return queryTree(2*idx+1, mid+1, end, val);
            }
        }
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
    }

}
