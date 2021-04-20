
/*
CPCS 324
Phase 3 - Group#6 - Task 1
Amjad Safar Alshahrani - 1806147
Ashwaq Althubaiti - 1808549
Haneen Talib - 1825917
 */
package edmonds.karp_algorithm;

import static java.util.Collections.sort;
import java.util.LinkedList;

public class EdmondsKarp_algorithm {

    //Num of Vertex
    static final int V = 6;
    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
      
       // Create a visited array  
        boolean visited[] = new boolean[V];
        //Mark all vertices as not visited
        for (int i = 0; i < V; ++i) {
            visited[i] = false;
        }
       
         //Create a queue, 
         
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        //enqueue source vertex and mark source vertex as visited
        visited[s] = true;
        parent[s] = -1;
        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();
            
            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
   
         // If we reached sink in BFS starting from source, then return true, else false
         
        return (visited[t] == true);
    }

   
    //*********************************************fordFulkerson**************************************************
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;
        /* 
         * Create a residual graph and fill the residual graph
         * with given capacities in the original graph as
         * residual capacities in residual graph
         */
        /*
         * Residual graph where rGraph[i][j] indicates
         * residual capacity of edge from i to j (if there
         * is an edge. If rGraph[i][j] is 0, then there is
         * not)
         */
        int rGraph[][] = new int[V][V];
        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }
        // This array is filled by BFS and to store path
        int parent[] = new int[V];
        int max_flow = 0;  // There is no flow initially
        /* 
         * Augment the flow while tere is path from source
         * to sink
         */
        while (bfs(rGraph, s, t, parent)) {
            /* 
             *Find minimum residual capacity of the edhes
             * along the path filled by BFS. Or we can say
             * find the maximum flow through the path found.
             */
            
            int pathFlow = Integer.MAX_VALUE;
            System.out.print("Augmenting path: ");
            LinkedList<Integer> queueIter = new LinkedList<Integer>();
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
                queueIter.add(u);
               
            }
            sort(queueIter);
            int n = queueIter.size();
            for (int i = 0; i < n; i++) {
                 System.out.print(queueIter.remove()+" => " );
            }
            System.out.print(t);
            /* 
             * update residual capacities of the edges and
             * reverse edges along the path
             */
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }
            // Add path flow to overall flow
            max_flow += pathFlow;
            System.out.print("  flow "+pathFlow +"\n");
            System.out.println("Update flow: "+max_flow);
        }
        // Returns tne maximum flow from s to t in the given graph
        return max_flow;
    }
    
    
    //********************************minCut********************************************
     void dfs(int[][] rGraph, int s,
                                boolean[] visited) {
        visited[s] = true;
        for (int i = 0; i < rGraph.length; i++) {
                if (rGraph[s][i] > 0 && !visited[i]) {
                    dfs(rGraph, i, visited);
                }
        }
    }
     // Prints the minimum s-t cut
     void minCut(int[][] graph, int s, int t) {
        int u,v;
          
        // Create a residual graph and fill the residual 
        // graph with given capacities in the original 
        // graph as residual capacities in residual graph
        // rGraph[i][j] indicates residual capacity of edge i-j
        int[][] rGraph = new int[graph.length][graph.length]; 
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                rGraph[i][j] = graph[i][j];
            }
        }
  
        // This array is filled by BFS and to store path
        int[] parent = new int[graph.length]; 
          
        // Augment the flow while tere is path from source to sink     
        while (bfs(rGraph, s, t, parent)) {
              
            // Find minimum residual capacity of the edhes 
            // along the path filled by BFS. Or we can say 
            // find the maximum flow through the path found.
            int pathFlow = Integer.MAX_VALUE;         
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }
              
            // update residual capacities of the edges and 
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] = rGraph[u][v] - pathFlow;
                rGraph[v][u] = rGraph[v][u] + pathFlow;
            }
        }
          
        // Flow is maximum now, find vertices reachable from s     
        boolean[] isVisited = new boolean[graph.length];     
        dfs(rGraph, s, isVisited);
          
        // Print all edges that are from a reachable vertex to
        // non-reachable vertex in the original graph     
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) {
                    System.out.println(i + " - " + j);
                }
            }
        }
    }

    //**********************Main********************************************
    public static void main(String[] args) throws java.lang.Exception {
       // Create graph 
        int graph[][] = new int[][] {
            { 0, 2, 7, 0, 0, 0 }, { 0, 0, 0, 3, 4, 0 },
            { 0, 0, 0, 4, 2, 0 }, { 0, 0, 0, 0, 0, 1},
            { 0, 0, 0, 0, 0, 5 }, { 0, 0, 0, 0, 0, 0 }
        };
        int sourse = 0;
        int sink =5;
        EdmondsKarp_algorithm E = new EdmondsKarp_algorithm();
        System.out.println("The maximum possible flow is " + E.fordFulkerson(graph, sourse, sink));
        E.minCut(graph, 0, 5);
    }
}
