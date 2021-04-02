/*

*/
package cpcs324_Project;

public class CPCS324_Project {

    public static void main(String[] args) {
    // Cities names array   
    String[] cities= new String[]
    {"Jeddah","Makkah","Madinah","Riyadh","Dammam","Taif","Abha","Tabuk","Qasim","Hail","Jizan","Najran"}; 
    
    // Cities distance matrix from: http://www.thesaudi.net/saudi-arabia/saudi_city_distances.htm
    int[][] citiesDistance = new int[][]
            {{0,79,420,949,1343,167,625,1024,863,777,710,905},
            {79,0,358,870,1265,88,627,1037,876,790,685,912},
            {420,358,0,848,1343,446,985,679,518,432,1043,1270},
            {949,870,848,0,395,782,1064,1304,330,640,1272,950},
            {1343,1265,1343,395,0,1177,1495,1729,725,1035,1667,1345},
            {167,88,446,782,1177,0,561,1204,936,957,763,864},
            {625,627,985,1064,1459,561,0,1649,1488,1402,202,280},
            {1024,1037,679,1304,1729,1204,1649,0,974,664,1722,1929},
            {863,876,518,330,725,936,1488,974,0,310,1561,1280},
            {777,790,432,640,1035,957,1402,664,974,0,1475,1590},
            {710,685,1043,1272,1667,763,202,1722,1561,1475,0,482},
            {905,912,1270,950,1345,864,280,1929,1280,1590,482,0}};
    
   
     // declarition of the cities' distances Graph as a matrix
     GraphMatrix matrix= new GraphMatrix(12);   
     
    // loop for assignment of all distances to the graph matrix 
    for(int i=0;i<12;i++){
        for(int j=0;j<12;j++){
            matrix.addEdge(i, j,citiesDistance[i][j]);
        }
    }
    
   
    // print the inital matrix 
        System.out.println("inital matrix: ");
        System.out.print("        JEDDAH    MAKKAH    MADINAH    RIYADH    DAMMAM    TAIF      ABHA      TABUK     QASIM     HAIL      JIZAN     NAJRAN ");
        System.out.println("");
    matrix.print(cities);
    
    // print the single-source shortest path from Jeddah( value 0 )
    matrix.dijkstra_GetMinDistances(0,cities);
    
    
    
    }
    
    
}
