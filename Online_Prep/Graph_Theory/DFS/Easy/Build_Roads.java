import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Build_Roads {
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

    static class FastWriter {
        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("" + object);
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                solve();
            }
        }, "Main", 1<<29).start();
    }

    @SuppressWarnings("unchecked")
    public static void solve() {
        int n, m;
        FastReader sc = new FastReader();
        n = sc.nextInt();
        m = sc.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        while (m-- > 0) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            addEdgeToGivenNode(graph, node1, node2);
        }

        boolean[] visited = new boolean[n+1];
        ArrayList<Integer> roads = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (visited[i] == false) {
                roads.add(i);
                dfsTraversal(i, visited, graph);
            }
        }
        System.out.println(roads.size()-1);
        for(int i=0;i<roads.size()-1;i++) {
            System.out.println(roads.get(i) + " "+ roads.get(i+1));
        }
    }

    private static void dfsTraversal(int start, boolean[] visited, ArrayList<Integer>[] graph) {
        if (visited[start] == true)
            return;

        visited[start] = true;
        for(int src : graph[start]) {
            if(!visited[src])
                dfsTraversal(src, visited, graph);
        }
    }

    private static void addEdgeToGivenNode(ArrayList<Integer>[] graph, int node1, int node2) {
        graph[node1].add(node2);
        graph[node2].add(node1);
    }

}