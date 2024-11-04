

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ring_Roads {

    static PrintWriter out;
    static long cost;

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

    public class Pair {
        int v;
        int w;

        public Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);

        int n = sc.nextInt();
        long sum = 0;
        Ring_Roads ringRoads = new Ring_Roads();

        ArrayList<Pair>[] graph = new ArrayList[n + 1];
        for(int i=0;i<=n;i++)
            graph[i] = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph[u].add(ringRoads.new Pair(v, 0));
            graph[v].add(ringRoads.new Pair(u, w));
            sum += w;
        }

        boolean[] visited = new boolean[n + 1];
        dfsTraversal(graph, visited, 1, 0);
        out.println(Math.min(cost, sum - cost));
        out.close();
    }

    private static void dfsTraversal(ArrayList<Pair>[] graph, boolean[] visited, int start, int next) {
        visited[start] = true;

        for (Pair p : graph[start]) {
            int nextEle = p.v;
            int c = p.w;
            if (!visited[nextEle]) {
                cost += c;
                dfsTraversal(graph, visited, nextEle, start);
            } else if (nextEle == 1 && next != nextEle)
                cost += c;
        }
    }

}
