package Graph_Theory.Cycle_Detection.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Round_Trip {

    static PrintWriter out;
    static ArrayList<Integer>[] graph;
    static int[] parent;
    static boolean[] visited;
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
        new Thread(null, () -> solve(), "Main", 1 << 29).start();
    }

    @SuppressWarnings("unchecked")
    private static void solve() {
        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        parent = new int[n + 1];

        for (int i = 0; i <= n; i++)
            parent[i] = -1;

        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[u].add(v);
            graph[v].add(u);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && dfsTraversal(i, parent[i])) {
                result.add(cycle_start);
                for (int j = cycle_end; j != cycle_start && j!=-1; j = parent[j]) {
                    result.add(j);
                }
                result.add(cycle_start);
                Collections.reverse(result);
                out.println(result.size());
                for (int node : result)
                    out.print(node + " ");
                break;
            }
        }

        if(result.size()==0)
            out.println("IMPOSSIBLE");

        out.close();

    }

    private static boolean dfsTraversal(int start, int par) {
        visited[start] = true;
        for (int node : graph[start]) {
            if (node == par)
                continue;
            if (visited[node]) {
                cycle_start = node;
                cycle_end = start;
                return true;
            }

            parent[node] = start;
            if (dfsTraversal(node, parent[node]))
                return true;
        }
        return false;
    }

}
