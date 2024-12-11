package Graph_Theory.Shortest_Path.Dijkstra.Medium;
import java.util.*;

public class Main {

    static class Edge {
        int to;
        long weight;
        int index;

        Edge(int to, long weight, int index) {
            this.to = to;
            this.weight = weight;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); 
        int m = sc.nextInt(); 
        int k = sc.nextInt(); 

        // Adjacency list
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        long[] edgeWeights = new long[m + k];
        boolean[] relaxed = new boolean[m + k];

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();

            adj.get(u).add(new Edge(v, w, i));
            adj.get(v).add(new Edge(u, w, i));
            edgeWeights[i] = w;
        }

        for (int i = 0; i < k; i++) {
            int v = sc.nextInt();
            long w = sc.nextLong();

            adj.get(1).add(new Edge(v, w, i + m));
            adj.get(v).add(new Edge(1, w, i + m));
            edgeWeights[i + m] = w;
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 1}); 

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (d > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                long w = edge.weight;
                int index = edge.index;

                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.offer(new long[]{dist[v], v});
                    if (index < m + k) relaxed[index] = false; 
                    relaxed[index] = true; 
                } else if (dist[v] == d + w && index < m) {
                    relaxed[index] = true; 
                }
            }
        }

        int deletableTrainRoutes = 0;
        for (int i = m; i < m + k; i++) {
            if (!relaxed[i]) deletableTrainRoutes++;
        }

        System.out.println(deletableTrainRoutes);
    }
}
