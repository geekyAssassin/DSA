package Easy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class kefa_and_park {
    static PrintWriter out;
    static int total = 0;

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

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Integer> graph[] = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        long[] cats = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            cats[i] = sc.nextLong();
        }

        for (int i = 0; i < n - 1; i++) {
            int j = sc.nextInt();
            int k = sc.nextInt();

            graph[j].add(k);
            graph[k].add(j);
        }
        boolean[] visited = new boolean[n + 1];
        dfsTraversal(visited, cats, graph, 1, m, 0);
        out.println(total);
        out.close();
    }

    private static void dfsTraversal(boolean[] visited, long[] cats, ArrayList<Integer>[] graph,
            int start, int maxCats, int sum) {
        if (visited[start])
            return;

        visited[start] = true;
        if (cats[start] == 1)
            sum++;
        else
            sum = 0;
        if (sum > maxCats)
            return;

        if (start != 1 && graph[start].size() == 1)
            total++;

        for (int src : graph[start]) {
            dfsTraversal(visited, cats, graph, src, maxCats, sum);
        }
    }
}
