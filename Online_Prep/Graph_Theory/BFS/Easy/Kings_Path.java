package Graph_Theory.BFS.Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Kings_Path {

    static class Pair {
        long first;
        long second;

        public Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }

        // In Java, when you use custom objects as keys in hash-based collections 
        // like HashMap, HashSet, or as elements in HashSet, it's crucial to override 
        // the equals() and hashCode() methods to ensure these collections behave correctly. 

        @Override
        public boolean equals(Object o) {
            if(this == o) // Check if the objects are the same instance
                return true;
            if(o == null || getClass()!= o.getClass())
                return false;
            Pair p = (Pair) o;
            return first == p.first && second == p.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    static PrintWriter out;
    static long x0, x1, y0, y1;
    static Map<Pair, Long> dist = new HashMap<>();
    static Map<Pair, Boolean> allowed = new HashMap<>();
    static Map<Pair, Boolean> visited = new HashMap<>();

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

        x0 = sc.nextLong();
        y0 = sc.nextLong();
        x1 = sc.nextLong();
        y1 = sc.nextLong();

        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            long x = sc.nextLong();
            long y_first = sc.nextLong();
            long y_last = sc.nextLong();

            for (long j = y_first; j <= y_last; j++) {
                Pair p = new Pair(x, j);
                allowed.put(p, true);
            }
        }
        Pair initial = new Pair(x0, y0);
        Pair finalPair = new Pair(x1, y1);
        allowed.put(initial, true);
        allowed.put(finalPair, true);
        bfsTraversal(initial, finalPair);

        if(visited.containsKey(finalPair)) {
            out.println(dist.get(finalPair));
        } else {
            out.println("-1");
        }

        out.close();
    }

    private static void bfsTraversal(Pair initial, Pair finalPair) {

        dist.put(initial, 0L);
        int[] dx = { 0, 0, 1, 1, 1, -1, -1, -1 };
        int[] dy = { 1, -1, 0, 1, -1, 0, 1, -1 };

        Queue<Pair> queue = new LinkedList<>();
        queue.add(initial);

        while (!queue.isEmpty()) {
            Pair current_Pair = queue.poll();

            for (int i = 0; i < 8; i++) {
                long allowed_x = current_Pair.first + dx[i];
                long allowed_y = current_Pair.second + dy[i];

                Pair formed_Pair = new Pair(allowed_x, allowed_y);
                if (allowed.containsKey(formed_Pair) && !visited.containsKey(formed_Pair)) {
                    if (!dist.containsKey(formed_Pair) || dist.get(formed_Pair) > dist.get(current_Pair) + 1) {
                        dist.put(formed_Pair, dist.get(current_Pair) + 1);
                        queue.add(formed_Pair);
                    }
                }
            }

            visited.put(current_Pair, true);
        }
    }

}
