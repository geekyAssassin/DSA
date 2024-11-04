

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Easy_Graph_Queries {

    static PrintWriter out;
    static int[] componentId;
    static int[] componentSize;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int currentComponentId = 0;
    static int size = 0;

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String s = br.readLine();
                    if (s == null)
                        return null;
                    st = new StringTokenizer(s);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            String s = next();
            if (s == null)
                return -1;
            return Integer.parseInt(s);
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    solve();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Main", 1 << 26).start();
    }

    static void solve() {
        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        graph = new ArrayList[n + 1];
        componentId = new int[n + 1];
        componentSize = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                currentComponentId++;
                size = 0;
                dfs(i);
                componentSize[currentComponentId] = size;
            }
        }

        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();
            if (type == 1) {
                int x = sc.nextInt();
                int compId = componentId[x];
                out.println(componentSize[compId]);
            } else {
                int x = sc.nextInt();
                int y = sc.nextInt();
                if (componentId[x] == componentId[y])
                    out.println("YES");
                else
                    out.println("NO");
            }
        }
        out.close();
    }

    private static void dfs(int node) {
        visited[node] = true;
        componentId[node] = currentComponentId;
        size++;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}