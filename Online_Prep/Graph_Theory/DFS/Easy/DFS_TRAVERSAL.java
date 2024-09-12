package Easy;
import java.util.ArrayList;
import java.util.Scanner;

public class DFS_TRAVERSAL {

    public static void dfstraversal(ArrayList<Integer>[] graph, int n) {
        boolean[] visited = new boolean[n];
        dfsTraversalUtil(graph, n, visited, 0);
    }

    private static void dfsTraversalUtil(ArrayList<Integer>[] graph, int n, boolean[] visited, int start) {
        if(visited[start] == true)
            return;
        System.out.print(start + " ");

        visited[start] = true;
        for(int src : graph[start]) {
            if(!visited[src])
                dfsTraversalUtil(graph, n, visited, src);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Nodes");
        int n = sc.nextInt();
        System.out.println("Enter Number of Edges");
        int m = sc.nextInt();
        ArrayList<Integer> [] graph = new ArrayList[n];

        for(int i=0;i<n;i++) {
            graph[i] = new ArrayList<>();
        }
        
        System.out.println("Enter Edge start and end");
        for(int i=1;i<=m;i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            graph[start].add(end);
            graph[end].add(start);
        }

        dfstraversal(graph,n);
    }
}
