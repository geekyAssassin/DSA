package Graph_Theory.Shortest_Path.Dijkstra.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
    static ArrayList<Pair>[] graph;
    static boolean[] visited;
    static long[] dist;
    static PrintWriter out;
    static int[] parent;

    static class Pair {
        int node;
        long distance;

        Pair(int node, long distance) {
            this.node = node;
            this.distance = distance;
        }

    }

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

        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        dist = new long[n+1];
        parent = new int[n+1];

        for(int i=0;i<=n;i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Long.MAX_VALUE;
            parent[i] = -1;
        }

        for(int i=0;i<m;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph[u].add(new Pair(v, w));
            graph[v].add(new Pair(u, w));           
        }

        shortest_Path(1,n);
        ArrayList<Integer> result = new ArrayList<>();
        if (parent[n] == -1)
            out.println(-1);
        else {
            for(int v = n; v!=1; v=parent[v])
                result.add(v);
            result.add(1);
        }

        Collections.reverse(result);
        for(int node: result)
            out.print(node + " ");
        out.close();

    }

    private static void shortest_Path(int start, int n) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(n, Comparator.comparingLong(o->o.distance));
        pq.add(new Pair(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Pair current = pq.poll();
            int current_node = current.node;

            for(Pair p : graph[current_node]) {
                int next_node = p.node;
                long next_distance = p.distance;

                if(dist[current_node] + next_distance < dist[next_node]) {
                    dist[next_node] = dist[current_node] + next_distance;
                    parent[next_node] = current_node;
                    pq.offer(new Pair(next_node, dist[next_node]));
                }

            }
        }
    }

}
