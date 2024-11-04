package Graph_Theory.BFS.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Number_of_shorests_path_between_vertices {
    static int n;
    static int[] distance;
    static int[] paths;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Number of Nodes");
        n = sc.nextInt();

        System.out.println("Enter number of edges");
        int m = sc.nextInt();

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        distance = new int[n + 1];
        paths = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph[u].add(v);
            graph[v].add(u);
        }

        // Find the distance and path between vertices
        bfs_traversal(0);

        for (int i = 0; i <= n; i++) {
            System.out.println("Distance Between Vertex 0 and" + i + " is" + distance[i]);
        }

        for (int i = 0; i <= n; i++) {
            System.out.println("Shortest Path Between 0 and" + i + " is" + paths[i]);
        }
    }

    public static void bfs_traversal(int start) {
        Queue<Integer> queue = new LinkedList<>();

        distance[start] = 0;
        paths[start] = 1;
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current_node = queue.poll();
            for (int node : graph[current_node]) {
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                }

                if (distance[node] > distance[current_node] + 1) {
                    distance[node] = distance[current_node] + 1;
                    paths[node] = paths[current_node];
                }

                else if (distance[node] == distance[current_node] + 1) {
                    paths[node] += paths[current_node];
                }
            }
        }

    }
}
