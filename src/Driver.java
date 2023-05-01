import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is the driver class for the module
 */
public class Driver {

    /**
     * Driver code22
     * @param args command line arguments
     */
    public static void main(String args[]) throws IOException {

        String path = "oral_exam2/S35_GraphAlgos_Hard/resources/words.dat";

        // read data
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        List<String> word_list = new ArrayList<String>();
        String str;

        // put data into array list
        while ((str = bufferedReader.readLine()) != null) {
            word_list.add(str);
        }

        // close file
        bufferedReader.close();

        // create adjacency list
        AdjacencyList al = new AdjacencyList(word_list);
        al.showStatistics();

        // perform dfs
        GraphSearcher gs = new GraphSearcher(al.adjacencyList);
        HashMap<String, ArrayList<String>> vertexSet = gs.dfs();

        // perform bfs
        HashMap<String, ArrayList<String>> vertexSet2 = gs.bfs();

        // see performance
        gs.printBfsVsDfsStatistics();

        // create mst and print weight
        MST mst = new MST(vertexSet);
        System.out.println("\n=============================================");
        System.out.println("Weight of MST created by Prim's Algorithm:");
        System.out.println(mst.primsAlg());
        System.out.println("=============================================");
    }
}
