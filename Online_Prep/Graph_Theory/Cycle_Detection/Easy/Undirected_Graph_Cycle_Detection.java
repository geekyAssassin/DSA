package Graph_Theory.Cycle_Detection.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Undirected_Graph_Cycle_Detection {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] parent;
    static int cycle_start, cycle_end;

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
        FastReader sc = new FastReader();

        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        parent = new int[n+1];

        for(int i=0;i<=n;i++)
            parent[i] = -1;

        for(int i=0;i<=n;i++)
            graph[i] = new ArrayList<>();
        
        for(int i=0;i<=m;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[u].add(v);
            graph[v].add(u);
        }

        for(int i=0;i<=n;i++) {
            if(!visited[i] && dfsTraversal(i, parent[i]))
                break;
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int v = cycle_end; v!=cycle_start; v=parent[v])
            result.add(v);

        // print the result
    }

    private static boolean dfsTraversal(int start, int par) {
        visited[start] = true;
        for(int node: graph[start]) {
            if(node == par)
                continue;
            if(visited[node]) {
                cycle_start = start;
                cycle_end = node;
                return true;
            }

            parent[node]= start;
            if(dfsTraversal(node, start))
                return true;
        }

        return false;
    }

    
}
