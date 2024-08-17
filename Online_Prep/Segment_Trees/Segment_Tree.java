import java.util.*;

public class Segment_Tree{

    public static void build(int[] arr,int node, int start, int end, int[] tree) {
        if(start == end) {// leaf node
            tree[node] = arr[start];
        }
        else {
            int mid = start + (end - start)/2;
            // build left tree
            build(arr,2*node,start,mid,tree);
            // build right tree
            build(arr, 2*node+1, mid+1, end, tree);
            tree[node] = tree[2*node] + tree[2*node+1];
        }
    }

    public static void update(int[] arr, int node, int start, int end, int index, int value, int[] tree) {
        if(start == end) {
            arr[index]+= value;
            tree[node]+= value;
        } else {
            int mid = start + (end-start)/2;
            if(start <=index && index<=mid) {
                update(arr, 2*node, start, mid, index, value, tree);
            } else {
                update(arr, 2*node + 1, mid+1, end, index, value, tree);
            }
            tree[node] = tree[2*node] + tree[2*node+1];
        }
    }

    public static int query(int[] arr, int node ,int start, int end, int l, int r, int[] tree) {
        if(r < start || end < l)
            return 0;
        
        if(l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end-start)/2;
        int p1 = query(arr, 2*node, start, mid, l, r, tree);
        int p2 = query(arr, 2*node+1, mid+1, end, l, r, tree);
        return p1+p2;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int total_queries = sc.nextInt();

        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
        int[] tree = new int[4*n];

        build(arr, 1, 0, n-1, tree);
        for (int i : tree) {
            System.out.print(i + " ");
        }

        int operation,i, j;
        while(total_queries-- > 0) {
            operation = sc.nextInt();
            i = sc.nextInt();
            j = sc.nextInt();

            if(operation == 1) {
                arr[i-1] = j;
                build(arr, 1, 0, n-1, tree);
            }
            else
                System.out.println(query(arr, 1, 0, n-1, i-1, j-1, tree));
        }

    }
}