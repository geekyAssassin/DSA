package Graph_Theory.BFS.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_Traversal {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

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

        int nodes = sc.nextInt();

        visited = new boolean[nodes + 1];

        graph = new ArrayList[nodes + 1];

        for (int i = 0; i <= nodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i <= nodes; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[u].add(v);
            graph[v].add(u);
        }

        bfsTraversal(0);
    }

    private static void bfsTraversal(int start) {
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current_node = queue.poll();
            System.out.print(current_node + " ");

            for(int node : graph[current_node]) {
                if(!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }
    }
}
