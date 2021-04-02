/*
Graph as a Matrix Class
*/
package cpcs324_Project;

public class GraphMatrix {
  // class attributes  
    
  private int matrix[][];
  // number of vertices
  private int vertices;
  

  // class constructor with num as the number of vertices
  public GraphMatrix(int num) {
    this.vertices = num;
    matrix = new int[num][num];
  }

  

 // adding a new edge to the matrix with w as weight
  public void addEdge(int i, int j,int w) {
    matrix[i][j] = w;
  }
  
  // print the matrix
    public void print(String[] cities) {
      // loop that print the cities distance matrix with formating
      for(int i=0;i<12;i++){
         System.out.printf("%-10S", cities[i]);     
        for(int j=0;j<12;j++){
        System.out.printf("%-10d", matrix[i][j]);  
        }
          System.out.println("");
      }
    }  
    
    
  /*==============================================================================
                             Implementation of Dijkstra algorithm
    From here to the end of the class all codes with its comments were taken from a website
    website page: https://algorithms.tutorialhorizon.com/djkstras-shortest-path-algorithm-adjacency-matrix-java-code/
    my minor changes to the code will be proceeded by ## 
    my own comments on the code will be proceeded by @@
    ==============================================================================
    */
    
  //get the vertex with minimum distance which is not included in SPT
  // @@ mst represent the Tree vertices Vt and key represent remining vertices V-Vt  
        int getMinimumVertex(boolean [] mst, int [] key){
            //@@ initializing both minKey and vertex with out of bound values
            int minKey = Integer.MAX_VALUE;
            int vertex = -1;
            //@@ looking for the min value in remining vertices
            for (int i = 0; i <vertices ; i++) {
            //@@ mst[i]==false is the condition to check vertex is in V-Vt not in Vt 
                if(mst[i]==false && minKey>key[i]){
                    minKey = key[i];
                    vertex = i;
                }
            }
            return vertex;
        }
        //## I added String[] city to method parameters to print the cities name in the output instead of the vertex index 
        public void dijkstra_GetMinDistances(int sourceVertex,String[] city){
            //@@ spt is Vt where if the index of the vertex is in Vt then the value is true
            boolean[] spt = new boolean[vertices];
            //@@ distance is the array holding minimum distance value of each vertex (the output values)
            int [] distance = new int[vertices];
            int INFINITY = Integer.MAX_VALUE;

            //Initialize all the distance to infinity
            for (int i = 0; i <vertices ; i++) {
                distance[i] = INFINITY;
            }

            //start from the vertex 0
            distance[sourceVertex] = 0;

            
            //create SPT
            for (int i = 0; i <vertices ; i++) {

                //get the vertex with the minimum distance
                int vertex_U = getMinimumVertex(spt, distance);

                //include this vertex in SPT
                spt[vertex_U] = true;

                //iterate through all the adjacent vertices of above vertex and update the keys
                for (int vertex_V = 0; vertex_V <vertices ; vertex_V++) {
                    //check of the edge between vertex_U and vertex_V
                    if(matrix[vertex_U][vertex_V]>0){
                        //check if this vertex 'vertex_V' already in spt and
                        // if distance[vertex_V]!=Infinity

                        if(spt[vertex_V]==false && matrix[vertex_U][vertex_V]!=INFINITY){
                            //check if distance needs an update or not
                            //means check total weight from source to vertex_V is less than
                            //the current distance value, if yes then update the distance

                            int newKey =  matrix[vertex_U][vertex_V] + distance[vertex_U];
                            if(newKey<distance[vertex_V])
                                distance[vertex_V] = newKey;
                        }
                    }
                }
                
                //## this part below is add to print each iteration of the algorithim
                System.out.print("\n\n\n Iteration "+(i+1)+" :  ");
                //## printing cities name 
                for(String c : city){
                    System.out.printf("%-10S", c);
                }
                //## printing spt values, true means the vertex is added to the tree
                System.out.print("\n Vertex Tree  :  ");
                for(boolean b: spt){
                    System.out.printf("%-10s", b);
                }
                //## printing the minimum path of each iteration
                System.out.print("\n Minimum path  :  ");
                for(int n: distance){
                    System.out.printf("%-10d", n);
                }
            }
            //print shortest path tree
            //@@ here I added city parameter to print cities names
            printDijkstra(sourceVertex, distance,city);
        }
        //@@ I changed the printing code here to print the city name instead of index number
        public void printDijkstra(int sourceVertex, int [] key, String[] city){
            System.out.println("\n\nDijkstra Algorithm: (Adjacency Matrix)");
            for (int i = 0; i <vertices ; i++) {
                System.out.println("Source Vertex: Jeddah to vertex "+city[i]+" distance: "+ key[i]);
            }
        }
  
/*
    ==============================================================================
        end of the website code
    ==============================================================================
        */
     
      
    
  }

