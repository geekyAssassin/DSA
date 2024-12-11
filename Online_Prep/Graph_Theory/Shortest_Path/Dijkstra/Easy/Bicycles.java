 package Graph_Theory.Shortest_Path.Dijkstra.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Bicycles {
    static class State implements Comparable<State> {
        int city;
        int slowness;
        long time;

        public State(int city, int slowness, long time) {
            this.city = city;
            this.slowness = slowness;
            this.time = time;
        }

        @Override
        public int compareTo(State other) {
            return Long.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            List<List<int[]>> graph = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();

                graph.get(u).add(new int[] { v, w });
                graph.get(v).add(new int[] { u, w });
            }

            int[] slowness = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                slowness[i] = sc.nextInt();
            }

            long result = dijkstra(n, graph, slowness);
            System.out.println(result);
        }

        sc.close();
    }

    static long dijkstra(int n, List<List<int[]>> graph, int[] slowness) {
        long INF = Long.MAX_VALUE / 2;

        long[][] dist = new long[n + 1][1001];
        boolean[][] visited = new boolean[n + 1][1001];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }

        int startCity = 1;
        int startSlowness = slowness[startCity];
        dist[startCity][startSlowness] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(startCity, startSlowness, 0));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int u = current.city;
            int s = current.slowness;
            long time = current.time;

            if (visited[u][s]) {
                continue;
            }
            visited[u][s] = true;

            int citySlowness = slowness[u];
            if (citySlowness < s) {
                if (dist[u][citySlowness] > time) {
                    dist[u][citySlowness] = time;
                    pq.offer(new State(u, citySlowness, time));
                }
            }

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];

                long newTime = time + (long) w * s;

                if (dist[v][s] > newTime) {
                    dist[v][s] = newTime;
                    pq.offer(new State(v, s, newTime));
                }
            }
        }

        long minTime = INF;
        for (int s = 1; s <= 1000; s++) {
            if (dist[n][s] < minTime) {
                minTime = dist[n][s];
            }
        }

        return minTime;
    }
}
