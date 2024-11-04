import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Send_the_fool_away {
    static PrintWriter out;
    static long max_ans;

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
        long w;

        public Pair(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        FastReader sc = new FastReader();
        out = new PrintWriter(System.out);

        Send_the_fool_away obj = new Send_the_fool_away();

        int n = sc.nextInt();

        ArrayList<Pair>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i =0;i<n-1;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();

            graph[u].add(obj.new Pair(v, w));
            graph[v].add(obj.new Pair(u, w));
        }

        boolean[] visited = new boolean[n];
        dfsTraversal(0,0,0, graph, visited);
        out.println(max_ans);
        out.close();
    }

    private static void dfsTraversal(int start, long res, long k, ArrayList<Pair>[] graph, boolean[] visited) {

        res+= k;
        max_ans = Math.max(max_ans, res);
        if(visited[start] == true)
            return;
        visited[start] = true;

        for(Pair x : graph[start]) {
            if(visited[x.v]!=true) {
                dfsTraversal(x.v, res, x.w, graph, visited);
            }
        }
        
    }
}
