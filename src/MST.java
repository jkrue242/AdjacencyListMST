import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles creation of a MST using Prim's Algorithm
 */
public class MST {

    /**
     * Constructor
     * @param adjacencyList graph in the form of an adjacency list represented by a hash map
     */
    public MST(HashMap<String, ArrayList<String>> adjacencyList)
    {
        this.graph = adjacencyList;
        completed = new HashMap<>();
        for (String word : graph.keySet())
        {
            completed.put(word, false);
        }
        this.vertices = new ArrayList<>();
    }

    /**
     * Runs Prim's Algorithm to create a MST
     * @return the weight of the MST
     */
    public int primsAlg()
    {
        // initialize weight, edges to 0
        int weight = 0;
        int edgeCount = 0;

        // start at first word
        String prev = "";
        for (String word : graph.keySet())
        {
            prev = word;
            break;
        }
        vertices.add(prev);

        // continue until we have each vertex captured
        while (edgeCount < graph.keySet().size()-1)
        {
            // get next shortest vertex
            String next = getShortestEdgeVertex(prev);
            int lowestWeight = getEdgeWeight(prev, next);

            if (next.equals(""))
            {
                lowestWeight = 999999999;
            }

            // for each word that we have searched, check adjacent edges
            for (String word: completed.keySet())
            {
                // if it has been searched
                if (completed.get(word))
                {
                    // loop through each adjacent edge
                    for (String adj : graph.get(word))
                    {
                        // if the current edge is shorter than the current shortest, update
                        int currWeight = getEdgeWeight(word, adj);
                        if (currWeight < lowestWeight && !completed.get(adj))
                        {
                            // set our previous word to the current word, next word to adj,
                            // update lowest weight
                            prev = word;
                            next = adj;
                            lowestWeight = currWeight;
                        }
                    }
                }
            }
            // set the next node to searched
            completed.put(next, true);
            vertices.add(next);
            weight += lowestWeight;
            edgeCount+=1;

        }
        return weight;
    }

    /**
     * Returns the vertex corresponding to the shortest edge
     * @return vertex
     */
    private String getShortestEdgeVertex(String root)
    {
        String target = "";
        for (String word : graph.get(root))
        {
            if (!completed.get(word))
            {
                target = word;
            }
        }

        int shortest = getEdgeWeight(root, target);
        for (String adj : graph.get(root))
        {
            if (!completed.get(adj))
            {
                int curr = getEdgeWeight(root, adj);
                if (curr < shortest)
                {
                    shortest = curr;
                    target = adj;
                }
            }
        }
        return target;
    }

    /**
     * Returns the edge weight between 2 words
     * @param word1 word 1
     * @param word2 word 2
     * @return weight of edge
     */
    private int getEdgeWeight(String word1, String word2)
    {
        int word1val = 0;
        int word2val = 0;
        for (int i = 0; i < word1.length(); i++)
        {
            word1val += Character.getNumericValue(word1.charAt(i));
        }
        for (int i = 0; i < word2.length(); i++)
        {
            word2val += Character.getNumericValue(word2.charAt(i));
        }

        return Math.min(word1val, word2val);
    }

    HashMap<String, Boolean> completed;
    private HashMap<String, ArrayList<String>> graph;
    public ArrayList<String> vertices;
}
