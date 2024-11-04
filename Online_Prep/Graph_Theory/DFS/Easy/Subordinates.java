
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Subordinates {
    static PrintWriter out;
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

        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++)
            graph[i] = new ArrayList<>();
        for(int i = 2;i<=n;i++) {
            int parent = sc.nextInt();
            graph[parent].add(i);
        }
        int[] employees = new int[n+1];
        dfsTraversal(1, graph, employees);
        for (int i =1 ;i<=n;i++) {
            out.print(employees[i] + " ");
        }
        out.close();
    }

    private static int dfsTraversal(int start , ArrayList<Integer>[] graph, int[] employees) {
        if(graph[start].size()==0)
            return 1;
        else {
            for(int i=0;i<graph[start].size();i++) {
                employees[start]+= dfsTraversal(graph[start].get(i), graph, employees);
            }
        }
        return employees[start]+1;
    }
}
