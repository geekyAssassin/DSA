package Graph_Theory.BFS.Medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Police_Stations {

    static PrintWriter out;
    static int n,k,d;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static Set<Integer> police_stations = new HashSet<>();
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
        out = new PrintWriter(System.out);
        FastReader sc = new FastReader();

        n = sc.nextInt();
        k = sc.nextInt();
        d = sc.nextInt();

        while(k-- > 0) {
            police_stations.add(sc.nextInt());
        }

        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i=0;i<=n;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=n;i++) {
            if(!visited[i]) {
                out.println(i);
            }
        }
    }
}
