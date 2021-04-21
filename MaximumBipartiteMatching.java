/*
CPCS 324
Phase 3 - Group#6
Amjad Safar Alshahrani - 1806147
Ashwaq Althubaiti - 1808549
Haneen Talib - 1825917
 */
package maximumbipartitematching;

public class MaximumBipartiteMatching {

    // A is number of applicants
    static final int A = 6;
    // H is number of hospitals
    static final int H = 6;
    static int matching[] = new int[H];

    // A DFS based recursive function tha
    boolean bpm(boolean bpGraph[][], int u, boolean Vist[], int matching1[]) {

        // vertex u is possible
        // Try every hospital one by one
        for (int v = 0; v < H; v++) {
            // If applicant u is interested. -- in hospital v and v is not visited
            if (bpGraph[u][v] && !Vist[v]) {

                // Mark v as visited
                Vist[v] = true;

                // If hospital 'v' is not assigned to an applicant OR previously
                // assigned applicant for hospital v (which is matching1[v]) has an alternate hospital available.
                // Since v is marked as visited in the
                // above line, matchR[v] in the following
                // recursive call will not get hospital 'v' again
                if (matching1[v] < 0 || bpm(bpGraph, matching1[v], Vist, matching1)) {
                    matching1[v] = u;
                    // returns true if a matching for
                    return true;
                }
            }
        }
        return false;
    }

    //***********************************print*************************************
    void printIteration() {
        String[] applicant ={ "Ahmed", "Mahmoud","Eman" , "Fatimah", "Kamel", "Nojood"};
        String[] hospital = {"King Abdelaziz University", "King Fahd " ," East Jeddah ", " King Fahad Armed Forces"," King Faisal Specialist "," Ministry of National Guard "};

        for (int i = 0; i < H; i++) {

            if (matching[i] > -1) {
                System.out.println("the applicant (" + (applicant[matching[i]]) + ") was appointed to the hospital " + (hospital[i]) + ".");
            } else {
                System.out.println("the hospital  " +  (hospital[i]) + " has no applicant");
            }
        }
    }

    //**********************************maximum number of matching****************************
    // Returns maximum number of matching from M to N
    int maxBPM(boolean bpGraph[][]) {
        // applicants assigned to hospitals.
        // The value of matching[i] is the applicant number assigned to hospital i,

        // Initially all hospitals are available
        for (int i = 0; i < H; ++i) {
            // the value -1 indicates nobody is assigned (available).
            matching[i] = -1;
        }

        // Number of hospitals assigned to applicants
        int result = 0;
        for (int u = 0; u < A; u++) {
            // for next applicant.
            boolean seen[] = new boolean[H];
            for (int i = 0; i < H; ++i) {
                // Mark all hospitals as not seen
                seen[i] = false;
            }

            // Find if the applicant 'u' can get a hospital
            if (bpm(bpGraph, u, seen, matching)) {
                result++;
            }
        }
        //print
        printIteration();
        // return maximum number of matching
        return result;
    }
    //**********Main Code*********************************************

    public static void main(String[] args)
            throws java.lang.Exception {
        // create a bpGraph shown
        boolean bpGraph[][] = new boolean[][]{
            {true, true, false, false, false, false},
            {false, false, false, false, false, true},
            {true, false, false, true, false, false},
            {false, false, true, false, false, false},
            {false, false, false, true, true, false},
            {false, false, false, false, false, true}};
        MaximumBipartiteMatching numOfMax = new MaximumBipartiteMatching();
        int numOfMaxNum = numOfMax.maxBPM(bpGraph);
        System.out.println();
        System.out.println("Maximum number of applicants that can get a position in hospital are " + numOfMaxNum);

    }
}
