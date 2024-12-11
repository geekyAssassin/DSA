package Graph_Theory.Shortest_Path.Floyd_Warshall.Easy;

import java.util.Scanner;

public class Greg_and_graph {

    private static long[] calculateShortestPath(int[] toDelete, int[][] dist, int n) {
        boolean[] visited = new boolean[n+1];
        long[] coveredDistances = new long[n+1];
        
        for(int q = n ;q >=1; q--) {
            int k = toDelete[q];
            visited[k] = true;

            coveredDistances[q] = 0;

            for(int i=1;i<=n;i++) {
                for(int j=1;j<=n;j++) {
                    dist[i][j] = Math.min(dist[i][j] , dist[i][k] + dist[k][j]);
                    if(visited[i] && visited[j])
                        coveredDistances[q]+= dist[i][j];
                }
            }
        }
        return coveredDistances;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] dist = new int[n+1][n+1];

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                dist[i][j] = sc.nextInt();
            }
        }

        int[] toDelete = new int[n+1];
        for(int i=1;i<=n;i++){
            toDelete[i] = sc.nextInt();
        }

        long[] result = calculateShortestPath(toDelete, dist, n);
        for(int i=1;i<=n;i++)
            System.out.print(result[i]+ " ");
    }

}
