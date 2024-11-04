package Graph_Theory.Cycle_Detection.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Cycle_In_Graph {

    static PrintWriter out;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] dist;
    static int[] parent;
    static int cycle_start, cycle_end;
    static int n, m, k, length=0;

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
        out = new PrintWriter(System.out);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        graph = new ArrayList[n + 1];
        dist = new int[n+1];
        parent = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            parent[i] = -1;
        }

        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[u].add(v);
            graph[v].add(u);
        }

        dfsTraversal(1, parent[1], 1);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = cycle_end; i != cycle_start && i != -1; i = parent[i])
            result.add(i);
        result.add(cycle_start);
        Collections.reverse(result);

        out.println(result.size());
        for (int node : result)
            out.print(node + " ");
        out.close();
    }

    private static boolean dfsTraversal(int start, int par, int distance) {
        dist[start] = distance;
        visited[start] = true;
        for(int node: graph[start]) {
            if(node == par)
                continue;
            if(visited[node] && Math.abs(dist[node] - dist[start]) >=k) {
                cycle_start = node;
                cycle_end = start;
                return true;
            } else if(!visited[node]) {
                parent[node] = start;
                if(dfsTraversal(node, parent[node], distance+1))
                    return true;
            }
        }
        return false;

    }
}
