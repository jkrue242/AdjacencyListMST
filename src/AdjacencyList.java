import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class handles the adjacency list creation
 */
public class AdjacencyList {

    /**
     * Default constructor
     */
    public AdjacencyList()
    {
        adjacencyList = new HashMap<>();
    }

    /**
     * Constructor
     * @param data list of strings
     */
    public AdjacencyList(List<String> data)
    {
        createAdjacencyList(data);
    }

    /**
     * Creates an adjacency list from given data
     * @param data list of strings (data)
     * @return adjacency list in the form of a Hash Map
     */
    public HashMap<String, ArrayList<String>> createAdjacencyList(List<String> data)
    {
        adjacencyList = new HashMap<>();

        // loop through each word
        for (String word : data)
        {
            ArrayList<String> adjacentWords = new ArrayList<>();

            // compare current word against other words
            for (String other_word : data)
            {
                // make sure we don't compare against itself
                if (!other_word.equals(word))
                {
                    // if they differ by 1, add to list
                    if (compareStrings(word, other_word))
                    {
                        adjacentWords.add(other_word);
                    }
                }
            }

            // put current word into map
            adjacencyList.put(word, adjacentWords);
        }
        return adjacencyList;
    }

    /**
     * Prints statistics about the adjacency list to the screen
     */
    public void showStatistics()
    {
        // these are the statistics we care about
        int numWithoutEdges = 0;
        ArrayList<String> wordsWithMostEdges = new ArrayList<>();
        double averageEdges = 0.0;

        // loop over each word in adjacency list
        int mostEdges = 0;
        int totalEdges = 0;

        for (String word : adjacencyList.keySet())
        {
            int currentEdgeCount = adjacencyList.get(word).size();

            // check if no edges
            if (currentEdgeCount == 0)
            {
                numWithoutEdges += 1;
            }

            // check if most edges
            if (currentEdgeCount > mostEdges)
            {
                wordsWithMostEdges.clear();
                wordsWithMostEdges.add(word);
                mostEdges = currentEdgeCount;
            }
            else if (currentEdgeCount == mostEdges)
            {
                wordsWithMostEdges.add(word);
            }

            totalEdges += currentEdgeCount;
        }

        // get average edge count
        averageEdges = (double) totalEdges / (double) adjacencyList.keySet().size();

        // print statistics
        System.out.println("=============================================\nAdjacency List Statistics:\n");
        System.out.println("Number of words without edges: "+numWithoutEdges);
        System.out.println("Words with most edges: "+wordsWithMostEdges);
        System.out.println("Average number of edges: "+averageEdges);
        System.out.println("=============================================");
    }

    /**
     * This function compares 2 strings, returns true if they differ by 1 letter, false otherwise
     * @param string1 first string
     * @param string2 second string
     * @return true if differs by 1, false otherwise
     */
    public boolean compareStrings(String string1, String string2)
    {
        int numDifferences = 0;
        for (int i = 0; i < string1.length(); i++)
        {
            if (string1.charAt(i) != string2.charAt(i))
            {
                numDifferences++;
            }
        }

        // length of remaining string should be the number of differences
        return (numDifferences == 1);
    }

    public HashMap<String, ArrayList<String>> adjacencyList;
}
