import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Mail_Stamps {

    static PrintWriter out;
    static ArrayList<Integer> result = new ArrayList<>();
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
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
            visited.computeIfAbsent(v, k-> false);
            visited.computeIfAbsent(u, k-> false);
        }

        for(Map.Entry<Integer, ArrayList<Integer>> entry : graph.entrySet()) {
            if(entry.getValue().size() ==1) {
                dfsTraversal(graph, visited , entry.getKey());
            }
        }
        for(int i : result)
            out.print(i + " ");
        out.close();
    }

    private static void dfsTraversal(HashMap<Integer, ArrayList<Integer>> graph, HashMap<Integer, Boolean> visited, int start) {
        if(visited.get(start))
            return;
        
        visited.put(start, true);
        for(int node : graph.get(start)) {
            if(!visited.get(node)) {
                dfsTraversal(graph, visited, node);
            }
        }
        result.add(start);        
    }

}
