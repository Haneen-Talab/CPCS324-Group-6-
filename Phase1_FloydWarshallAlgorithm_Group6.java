
/*
CPCS 324
Phase 1 - Group#6
Amjad Safar Alshahrani - 1806147
Ashwaq Althubaiti - 1808549
Haneen Talib - 1825917
 */
package phase1_floydwarshallalgorithm_group.pkg6;

public class Phase1_FloydWarshallAlgorithm_Group6 {

    final static int INF = Integer.MAX_VALUE; // variable of infinite number

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //initialize graph with adjacency matrix
        int matrOfGraph[][] = new int[][] {
            //       A    B    C    D    E    F    G    H    I    J   
            /*A */ {0  , 10 , INF, INF, INF, 5  , INF, INF, INF, INF},
            /*B */ {INF, 0  , 3  , INF, 3  , INF, INF, INF, INF, INF},
            /*C */ {INF, INF, 0  , 4  , INF, INF, INF, 5  , INF, INF},
            /*D */ {INF, INF, INF, 0  , INF, INF, INF, INF, 4  , INF},
            /*E */ {INF, INF, 4  , INF, 0  , INF, 2  , INF, INF, INF},
            /*F */ {INF, 3  , INF, INF, INF, 0  , INF, INF, INF, 2  },
            /*G */ {INF, INF, INF, 7  , INF, INF, 0  , INF, INF, INF},
            /*H */ {INF, INF, INF, 4  , INF, INF, INF, 0  , 3  , INF},
            /*I */ {INF, INF, INF, INF, INF, INF, INF, INF, 0  , INF},
            /*J */ {INF, 6  , INF, INF, INF, INF, 8  , INF, INF, 0  }
        };
    
        //Finding shortest paths ---> apply Floyd Algo.
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    // This line for "Integer.MAX_VALUE" of infinite number so as not to put a negative value in it
                    if(matrOfGraph[i][k] != INF && matrOfGraph[k][j] != INF){ 
                        //Check if vertex k is on the shortest path from i to j, then update the value of dist[i][j]
                         if (matrOfGraph[i][k] + matrOfGraph[k][j] <  matrOfGraph[i][j]) {
                            matrOfGraph[i][j] =matrOfGraph[i][k] + matrOfGraph[k][j];
                        }
                    }
                }
            }
            //Print matrix for every iteration
            System.out.println("");
            System.out.println("Iteration: " + (k+1));
            PrintMatix(matrOfGraph); //infoke PrintMatix() method with matrOfGraph as parameter to print the matrix
            System.out.println("");
        }
        
            //Print Final distances matrix of the shortest-path's length (final result)
            System.out.println("");
            System.out.println("\n\nFinal shortest distances matrix: ");
            PrintMatix(matrOfGraph); //infoke PrintMatix() method with matrOfGraph as parameter to print the matrix
            System.out.println("");  
    
    }

    public static void PrintMatix(int[][] mater) {
        // Methode for print Matix
        
        //Print the name of each column
             System.out.print("\t");
        for (int i = 0; i < mater.length; i++) {
             System.out.print((char) ('A' + i) + "\t");
        }
             System.out.println();
        
        //Print the matrix
        for (int i = 0; i < mater.length; ++i) {
            System.out.print((char) ('A' + i) + "\t");
            for (int j = 0; j < mater.length; ++j) {
                if (mater[i][j] == INF) {
                    System.out.print("∞\t");
                } else {
                    System.out.print(mater[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
}
