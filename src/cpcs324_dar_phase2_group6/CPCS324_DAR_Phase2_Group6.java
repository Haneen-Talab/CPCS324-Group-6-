/*
CPCS 324
Phase 2 - Group#6
Amjad Safar Alshahrani - 1806147
Ashwaq Althubaiti - 1808549
Haneen Talib - 1825917
 */
package cpcs324_dar_phase2_group6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import javafx.util.Pair;

/**
 *
 * @author HP
 */
public class CPCS324_DAR_Phase2_Group6 {

    //For create object of edge
    static class Edge {

        int source;
        int destination;
        int weight;

        //The constructer of Edge class
        public Edge(int source, int destination, int weight) {//*******************
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    //For node of heap in prim with Min heap
    static class HeapNode {

        int vertex;
        int key;
    }

    static class ResultSet {

        int parent;
        int weight;
    }

    //Graph class for all Algorithm
    static class Graph {

        int vertices;
        LinkedList<Edge> allEdges = new LinkedList<>(); //Create Linked List for Kruskal Algorithm
        LinkedList<Edge>[] adjacencylist; //Create Linked List for prim's Algorithms
        Edge[] edges; // create array for edge
        // Edge[] itreationOfEdge;//For Iteration
        int itr = 0;

        //The constructer of Graph class
        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices + 1];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i < vertices; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        // Method for add Edge in Linked Lists
        public void addEgde(int source, int destination, int weight) {
            //create Edge  to added in Linked Lists
            Edge edge = new Edge(source, destination, weight);
            allEdges.add(edge); //add to all edges
            adjacencylist[source].addFirst(edge); //for undirected graph
            //create Edge to added in Linked Lists
            edge = new Edge(destination, source, weight);
            adjacencylist[destination].addFirst(edge); //for undirected graph

        }

        //Method to check if the edge is exist or not
        public Boolean existEgde(int source, int destination) {

            for (int i = 0; i < adjacencylist[source].size(); i++) {
                //check if the edge is exist or not
                if (adjacencylist[source].get(i).destination == destination) {
                    return false;
                }
            }

            return true;
        }

        // Method print the graph
        public void printGraph() {
            for (int i = 0; i < vertices; i++) {
                LinkedList<Edge> list = adjacencylist[i];
                for (int j = 0; j < list.size(); j++) {
                    System.out.println("vertex- " + i + " is connected to "
                            + list.get(j).destination + " with weight " + list.get(j).weight);
                }
            }
        }

        // Method of make graph
        public void make_graph(int vertices, int edge, Graph graph) {

            //Foe each edge
            for (int i = 0; i < edge;) {
                int source = (int) (Math.random() * (vertices));
                int destination = (int) (Math.random() * (vertices));
                int weight = ((int) (Math.random() * 10)) + 1;

                //check if the edge is exist or not
                if ((source != destination && graph.existEgde(source, destination)) && graph.existEgde(destination, source)) {
                    //add edge to graph
                    graph.addEgde(source, destination, weight);
                    i++;
                }

            }
        }
//-------------------------------------------------------------------------------------------------
        // Method of kruskal Algoritm

        public void kruskalMST() {

            // startTime to compute the running time
            long startTime = System.currentTimeMillis();

            //create Queue for sort the edge
            PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));
            //PriorityQueue<Edge> pqToIter = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));//For Iteration

            //add all the edges to priority queue, //sort the edges on weights
            for (int i = 0; i < allEdges.size(); i++) {
                pq.add(allEdges.get(i));

            }

            //This for print iteration
//-------------------------------------------------------------------------------------------
            //This for print iteration
            /*for (int i = 0; i < allEdges.size(); i++) {
                pqToIter.add(allEdges.get(i));
            }
             
             itreationOfEdge = new Edge [pqToIter.size()];
              System.out.println("Tree edges");// for iteration
              System.out.println("");
              System.out.println("sorted list of edges\n");
               for (int i = 0; i < pq.size(); i++) {
                itreationOfEdge[i] =pqToIter.remove();
                 System.out.println(itreationOfEdge[i].source+" - "+itreationOfEdge[i].destination +"=>"+itreationOfEdge[i].weight);
            }
                 System.out.println("------------------------------------------");*/
//---------------------------------------------------------------------------------------------------
            //create a edged []
            Edge[] edged = new Edge[pq.size()];
            // Copy each edge
            for (int i = 0; i < edged.length; i++) {
                edged[i] = allEdges.get(i);
            }

            //create a parent []
            int[] parent = new int[vertices];

            //makeset
            makeSet(parent);

            ArrayList<Edge> mst = new ArrayList<>();

            //process vertices - 1 edges
            int ecounter = 0;

            while (ecounter < vertices - 1 && pq.isEmpty()) {
                Edge edge = pq.remove();

                //check if adding this edge creates a cycle
                int x_set = find(parent, edge.source);
                int y_set = find(parent, edge.destination);

                if (x_set == y_set) {
                    //ignore, will create cycle
                } else {
                    //add it to our final result
                    mst.add(edge);

                    //This for print iteration
                    //----------------------------------------------------------------------------------------------------------                   
                    //    System.out.println("Tree edges");  // for iteration
                    for (int i = 0; i < mst.size(); i++) {

                        //System.out.println(mst.get(i).source + " - " + mst.get(i).destination + " -> " + mst.get(i).weight);
                    }
                    // itr++;
                    /*    System.out.println("\n sorted list of edges"); // for iteration
                    for (int i = itr; i < pq.size(); i++) {
                        System.out.println(itreationOfEdge[i].source + " - " + itreationOfEdge[i].destination + "=>" + itreationOfEdge[i].weight);
                    }

                     System.out.println("------------------------------------------");*/
                    //-----------------------------------------------------------------------------------------------------                   

                    ecounter++;
                    union(parent, x_set, y_set);
                }
            }
            //print MST
            /*  System.out.println("");
             System.out.println("Minimum Spanning Tree: ");
             printGraph(mst);*/

            System.out.println(" Kruskal Algorithm: ");
            //Compute the running time of Kruskal Algorithm
            long lastTime = System.currentTimeMillis();
            long efficientTime = lastTime - startTime;
            //print the running time of Kruskal Algorithm
            System.out.println("Running time of Kruskal Algorithm: " + efficientTime + " ms");

        }

        public void makeSet(int[] parent) {
            //Make set- creating a new element with a parent pointer to itself.
            for (int i = 0; i < vertices; i++) {
                parent[i] = i;
            }
        }

        public int find(int[] parent, int vertex) {
            //chain of parent pointers from x upwards through the tree
            // until an element is reached whose parent is itself
            if (parent[vertex] != vertex) {
                return find(parent, parent[vertex]);
            };

            return vertex;
        }

        public void union(int[] parent, int x, int y) {
            int x_set_parent = find(parent, x);
            int y_set_parent = find(parent, y);
            //make x as parent of y
            parent[y_set_parent] = x_set_parent;
        }

        //method to print the Minimum Spanning Tree of Kruskal algorithm
        public void printGraph(ArrayList<Edge> edgeList) {
            int minimumCost = 0;
            for (int i = 0; i < edgeList.size(); i++) {
                Edge edge = edgeList.get(i);
                System.out.println("Edge-" + i + " source: " + edge.source
                        + " destination: " + edge.destination
                        + " weight: " + edge.weight);
                minimumCost += edge.weight;

            }
            System.out.println("\nMinimum Cost Spanning Tree " + minimumCost);
        }

//-------------------------------------------------------------------------------------------------
        //Method of prim with min heap
        public void primMST() {
            long startTime = System.currentTimeMillis();
            boolean[] inHeap = new boolean[vertices];
            ResultSet[] resultSet = new ResultSet[vertices];
            //keys[] used to store the key to know whether min hea update is required
            int[] key = new int[vertices];
            //create heapNode for all the vertices
            HeapNode[] heapNodes = new HeapNode[vertices];
            for (int i = 0; i < vertices; i++) {
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].key = Integer.MAX_VALUE;
                resultSet[i] = new ResultSet();
                resultSet[i].parent = -1;
                inHeap[i] = true;
                key[i] = Integer.MAX_VALUE;
            }

            //decrease the key for the first index
            heapNodes[0].key = 0;

            //add all the vertices to the MinHeap
            MinHeap minHeap = new MinHeap(vertices);
            //add all the vertices to priority queue
            for (int i = 0; i < vertices; i++) {
                minHeap.insert(heapNodes[i]);

            }

            //while minHeap is not empty
            while (!minHeap.isEmpty()) {
                //extract the min
                HeapNode extractedNode = minHeap.extractMin();

                //extracted vertex
                int extractedVertex = extractedNode.vertex;
                inHeap[extractedVertex] = false;

                //iterate through all the adjacent vertices
                LinkedList<Edge> list = adjacencylist[extractedVertex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    //only if edge destination is present in heap
                    if (inHeap[edge.destination]) {
                        int destination = edge.destination;
                        int newKey = edge.weight;
                        //check if updated key < existing key, if yes, update if
                        if (key[destination] > newKey) {
                            decreaseKey(minHeap, newKey, destination);
                            //update the parent node for destination
                            resultSet[destination].parent = extractedVertex;
                            resultSet[destination].weight = newKey;
                            key[destination] = newKey;

                        }
                    }
                }
            }

            //print MST
            // printMST(resultSet);
            System.out.println("Prim’s algorithm using min-heap: ");
            //Compute the running time of Kruskal Algorithm
            long lastTime = System.currentTimeMillis();
            long efficientTime = lastTime - startTime;
            //Print the running time of Kruskal Algorithm
            System.out.println("Running time of Prim’s algorithm using min-heap: " + efficientTime + " ms");
        }

        public void decreaseKey(MinHeap minHeap, int newKey, int vertex) {

            //get the index which key's needs a decrease;
            int index = minHeap.indexes[vertex];

            //get the node and update its value
            HeapNode node = minHeap.mH[index];
            node.key = newKey;
            minHeap.bubbleUp(index);
        }

        //Method of print the MST of prim with min heap algorithm
        public void printMST(ResultSet[] resultSet) {
            int total_min_weight = 0;
            System.out.println("Minimum Spanning Tree: ");
            for (int i = 1; i < vertices; i++) {
                System.out.println("Edge: " + i + " - " + resultSet[i].parent
                        + " weight: " + resultSet[i].weight);
                total_min_weight += resultSet[i].weight;
            }
            System.out.println("Total minimum key: " + total_min_weight);
        }

        //Min heap class
        static class MinHeap {

            int capacity;
            int currentSize;
            HeapNode[] mH;
            int[] indexes; //will be used to decrease the key
            // The constructer of Min heap class

            public MinHeap(int capacity) {
                this.capacity = capacity;
                mH = new HeapNode[capacity + 1];
                indexes = new int[capacity];
                mH[0] = new HeapNode();
                mH[0].key = Integer.MIN_VALUE;
                mH[0].vertex = -1;
                currentSize = 0;
            }

            public void display() {
                for (int i = 0; i <= currentSize; i++) {
                    System.out.println(" " + mH[i].vertex + "   key   " + mH[i].key);
                }
                System.out.println("________________________");
            }

            public void insert(HeapNode x) {
                currentSize++;
                int idx = currentSize;
                mH[idx] = x;
                indexes[x.vertex] = idx;
                bubbleUp(idx);
            }

            public void bubbleUp(int pos) {
                int parentIdx = pos / 2;
                int currentIdx = pos;
                while (currentIdx > 0 && mH[parentIdx].key > mH[currentIdx].key) {
                    HeapNode currentNode = mH[currentIdx];
                    HeapNode parentNode = mH[parentIdx];

                    //swap the positions
                    indexes[currentNode.vertex] = parentIdx;
                    indexes[parentNode.vertex] = currentIdx;
                    swap(currentIdx, parentIdx);
                    currentIdx = parentIdx;
                    parentIdx = parentIdx / 2;
                }
            }

            public HeapNode extractMin() {
                HeapNode min = mH[1];
                HeapNode lastNode = mH[currentSize];
//            update the indexes[] and move the last node to the top
                indexes[lastNode.vertex] = 1;
                mH[1] = lastNode;
                mH[currentSize] = null;
                sinkDown(1);
                currentSize--;
                return min;
            }

            public void sinkDown(int k) {
                int smallest = k;
                int leftChildIdx = 2 * k;
                int rightChildIdx = 2 * k + 1;
                if (leftChildIdx < heapSize() && mH[smallest].key > mH[leftChildIdx].key) {
                    smallest = leftChildIdx;
                }
                if (rightChildIdx < heapSize() && mH[smallest].key > mH[rightChildIdx].key) {
                    smallest = rightChildIdx;
                }
                if (smallest != k) {

                    HeapNode smallestNode = mH[smallest];
                    HeapNode kNode = mH[k];

                    //swap the positions
                    indexes[smallestNode.vertex] = k;
                    indexes[kNode.vertex] = smallest;
                    swap(k, smallest);
                    sinkDown(smallest);
                }
            }

            public void swap(int a, int b) {
                HeapNode temp = mH[a];
                mH[a] = mH[b];
                mH[b] = temp;
            }

            //check if empty
            public boolean isEmpty() {
                return currentSize == 0;
            }

            public int heapSize() {
                return currentSize;
            }
        }

        //-------------------------------------------------------------------------------------------------       
        //Method of prim with PQ
        public void primMST2Queue() {

            long startTime = System.currentTimeMillis();
            boolean[] mst = new boolean[vertices];
            ResultSet[] resultSet = new ResultSet[vertices];
            int[] key = new int[vertices];  //keys used to store the key to know whether priority queue update is required

            //Initialize all the keys to infinity and
            //initialize resultSet for all the vertices
            for (int i = 0; i < vertices; i++) {
                key[i] = Integer.MAX_VALUE;
                resultSet[i] = new ResultSet();
            }

            //Initialize priority queue
            //override the comparator to do the sorting based keys
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    //sort using key values
                    int key1 = p1.getKey();
                    int key2 = p2.getKey();
                    return key1 - key2;
                }
            });

            //create the pair for for the first index, 0 key 0 index
            key[0] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(key[0], 0);
            //add it to pq
            pq.offer(p0);

            resultSet[0] = new ResultSet();
            resultSet[0].parent = -1;

            //while priority queue is not empty
            while (!pq.isEmpty()) {
                //extract the min
                Pair<Integer, Integer> extractedPair = pq.poll();

                //extracted vertex
                int extractedVertex = extractedPair.getValue();
                mst[extractedVertex] = true;

                //iterate through all the adjacent vertices and update the keys
                LinkedList<Edge> list = adjacencylist[extractedVertex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    //only if edge destination is not present in mst
                    if (mst[edge.destination] == false) {
                        int destination = edge.destination;
                        int newKey = edge.weight;
                        //check if updated key < existing key, if yes, update if
                        if (key[destination] > newKey) {
                            //add it to the priority queue
                            Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                            pq.offer(p);
                            //update the resultSet for destination vertex
                            resultSet[destination].parent = extractedVertex;
                            resultSet[destination].weight = newKey;
                            //update the key[]
                            key[destination] = newKey;
                        }
                    }
                }
            }
            //print mst
            //printMSTQueue(resultSet);
            System.out.println("Prim’s algorithm using priority-queue:");
            //Compute the running time of Kruskal Algorithm
            long lastTime = System.currentTimeMillis();
            long efficientTime = lastTime - startTime;
            //print the running time of Kruskal Algorithm
            System.out.println("Running time of Prim’s algorithm using d priority-queue: " + efficientTime + " ms");
        }

        //Method of print the MST of prim algorithm with PQ
        public void printMSTQueue(ResultSet[] resultSet) {
            int total_min_weight = 0;
            System.out.println("Minimum Spanning Tree: ");
            System.out.println("");
            for (int i = 1; i < vertices; i++) {
                System.out.println("Edge: " + i + " - " + resultSet[i].parent
                        + " key: " + resultSet[i].weight);
                total_min_weight += resultSet[i].weight;
            }
            System.out.println("Total minimum key: " + total_min_weight);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("          Running Time Of Minimum Spanning Tree Algorithms       ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        System.out.println("Availabl cases with n =>(# of verticies) and m =>(# of edges): ");
        System.out.println("case 1 =>  n = 1000   , m =10000 ");
        System.out.println("case 2 =>  n = 1000   , m =15000 ");
        System.out.println("case 3 =>  n = 1000   , m =25000 ");
        System.out.println("case 4 =>  n = 5000   , m =15000 ");
        System.out.println("case 5 =>  n = 5000   , m =25000 ");
        System.out.println("case 6 =>  n = 10000  , m =15000 ");
        System.out.println("case 7 =>  n = 10000  , m =25000 ");
        System.out.println("case 8 =>  n = 20000  , m =200000 ");
        System.out.println("case 9 =>  n = 20000  , m =300000 ");
        System.out.println("case 10 => n = 50000  , m =1000000 ");

        System.out.print(">Choice number of case: ");

        //Read from user the number of case
        int choice = input.nextInt();
        //number of vertices and edges
        int vertices = 0;
        int edges = 0;
        Graph graph;

        //switch for the cases
        switch (choice) {

            case 1:
                vertices = 1000;
                edges = 10000;

                break;
            case 2:
                vertices = 1000;
                edges = 15000;
                break;
            case 3:
                vertices = 1000;
                edges = 25000;
                break;
            case 4:
                vertices = 5000;
                edges = 15000;
                break;
            case 5:
                vertices = 5000;
                edges = 25000;
                break;
            case 6:
                vertices = 10000;
                edges = 15000;
                break;
            case 7:
                vertices = 10000;
                edges = 25000;
                break;
            case 8:
                vertices = 20000;
                edges = 200000;
                break;
            case 9:
                vertices = 20000;
                edges = 300000;
                break;
            case 10:
                vertices = 50000;
                edges = 1000000;
                break;
            default:
                System.out.println("Number of case is not correct");

        }

        System.out.println("");
        //create object of the graph
        graph = new Graph(vertices);
        //Make graph
        graph.make_graph(vertices, edges, graph);
        //invoke primMST2Queue() Method
        graph.primMST2Queue();
        //invoke primMST() Method
        graph.primMST();
        //invoke kruskalMST() Method
        graph.kruskalMST();

    }
}
