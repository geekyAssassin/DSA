package Graph_Theory.BFS.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Message_Route {

    static PrintWriter out;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] dist;
    static int[] paths;
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
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        dist = new int[n+1];
        paths = new int[n+1];

        for(int i=0;i<=n;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=1;i<=m;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[u].add(v);
            graph[v].add(u);
        }

        bfsTraversal(1);
        if(visited[n] == false) {
            out.println("IMPOSSIBLE");
        } else {
            ArrayList<Integer> result = new ArrayList<>();
            for(int v = n; v!=-1; v = paths[v])
                result.add(v);
            Collections.reverse(result);
            out.println(result.size());
            for(int x: result)
                out.print(x+ " ");
        }

        out.close();
    }

    private static void bfsTraversal(int start) {
        paths[start] = -1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int current_node = queue.poll();
            for(int x : graph[current_node]) {
                if(!visited[x]) {
                    visited[x] = true;
                    queue.add(x);
                    paths[x] = current_node;
                }
            }
        }
    }
}
