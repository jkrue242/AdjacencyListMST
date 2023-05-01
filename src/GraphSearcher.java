import java.util.*;

/**
 * This class handles BFS and DFS of an adjacency list
 */
public class GraphSearcher {

    /**
     * Constructor
     * @param graph adjacency list
     */
    public GraphSearcher(HashMap<String, ArrayList<String>> graph) {
        this.graph = graph;
        visitedDFS = new HashMap<>();
        visitedBFS = new HashMap<>();
    }

    /**
     * Runs DFS on adjacency list to find the longest set of vertices
     * @return set of connected vertices
     */
    public HashMap<String, ArrayList<String>> dfs()
    {
        // init visited nodes
        for (String word : graph.keySet())
        {
            visitedDFS.put(word, false);
        }

        // start time
        double startTime = System.currentTimeMillis();

        int largestChainSize = 0;
        HashMap<String, ArrayList<String>> longestChain = new HashMap<>();

        for (String word : graph.keySet())
        {
            HashMap<String, ArrayList<String>> chain = new HashMap<>();
            if (!visitedDFS.get(word))
            {
                chain = dfsHelper(word);
            }

            if (chain.size() > largestChainSize)
            {
                largestChainSize = chain.size();
                longestChain = chain;
            }
        }

        // end time
        double endTime = System.currentTimeMillis();
        dfsTime = (endTime - startTime);

        System.out.println("\n=============================================\nDFS Results:\nThe longest chain of vertices is: "+longestChain.keySet()+"\nWith a length of: "+longestChain.keySet().size());
        System.out.println("DFS took "+dfsTime+ " milliseconds\n=============================================");

        return longestChain;
    }

    /**
     * DFS Algorithm
     * @param src source node
     * @return returns the chain of connected vertices
     */
    private HashMap<String, ArrayList<String>> dfsHelper(String src)
    {

        // holds words we need to visit
        Stack<String> stack = new Stack<>();
        HashMap<String, ArrayList<String>> chain = new HashMap<>();

        // Push the current source node
        stack.push(src);

        // loop as long as there are words that haven't been visited
        while(!stack.empty())
        {
            // visit next word, set as visited
            String newWord = stack.pop();
            if (!visitedDFS.get(newWord))
            {
                chain.put(newWord, graph.get(newWord));
                visitedDFS.put(newWord, true);
            }

            // add adjacent words to stack if they haven't been visited and don't already exist in stack
            for (String adjWord : graph.get(newWord))
            {
                if (!visitedDFS.get(adjWord) && !stack.contains(adjWord))
                {
                    stack.add(adjWord);
                }
            }
        }
        return chain;
    }

    /**
     * Runs BFS on adjacency list to find the longest set of connected vertices
     * @return new adjacency list
     */
    public HashMap<String, ArrayList<String>> bfs()
    {
        // init visited nodes
        for (String word : graph.keySet())
        {
            visitedBFS.put(word, false);
        }

        // start time
        double startTime = System.currentTimeMillis();
        int largestChainSize = 0;
        HashMap<String, ArrayList<String>> longestChain = new HashMap<>();

        // run bfs on each word in the graph
        for (String word : graph.keySet())
        {
            HashMap<String, ArrayList<String>> chain = new HashMap<>();

            // if we haven't visited that word yet
            if (!visitedBFS.get(word))
            {
                // run bfs, get chain
                chain = bfsHelper(word);
            }

            // see if we have a new longest chain
            if (chain.size() > largestChainSize)
            {
                largestChainSize = chain.size();
                longestChain = chain;
            }
        }

        // end time
        double endTime = System.currentTimeMillis();
        bfsTime = (endTime - startTime);

        System.out.println("\n=============================================\nBFS Results:\nThe longest chain of vertices is: "+longestChain.keySet()+"\nWith a length of: "+longestChain.keySet().size());
        System.out.println("DFS took "+bfsTime+ " milliseconds\n=============================================");

        return longestChain;
    }

    /**
     * Runs bfs on the adjacency list
     * @param src root word
     * @return chain of connected vertices
     */
    private HashMap<String, ArrayList<String>> bfsHelper(String src)
    {
        // create a queue to store the nodes we need to visit
        Queue<String> queue = new LinkedList<>();
        queue.add(src);

        // store chain
        HashMap<String, ArrayList<String>> chain = new HashMap<>();

        // loop while queue still has items
        while(!queue.isEmpty())
        {
            // take first word from queue, add to chain
            String visitWord = queue.poll();
            visitedBFS.put(visitWord, true);
            chain.put(visitWord, graph.get(visitWord));

            // add all adjacent words to queue if they haven't been visited already
            for (String adjWord : graph.get(visitWord))
            {
                // add to queue if it hasn't been visited and doesn't already exist in queue
                if (!visitedBFS.get(adjWord) && !queue.contains(adjWord))
                {
                    queue.add(adjWord);
                }
            }
        }
        return chain;
    }

    /**
     * Prints ratio between BFS and DFS run time
     */
    public void printBfsVsDfsStatistics()
    {
        // check if outside 10% threshold
        System.out.println("\n=============================================\nPerformance:");
        if (bfsTime < 0.9*dfsTime)
        {
            System.out.println("BFS performed "+(Math.floor( (dfsTime/bfsTime) * 100) / 100)+ " times faster than DFS.");
        }
        else if (dfsTime < 0.9*bfsTime)
        {
            System.out.println("DFS performed "+(Math.floor( (bfsTime/dfsTime) * 100) / 100)+ " times faster than BFS.");
        }
        else
        {
            System.out.println("BFS and DFS performed similarly.");
        }
        System.out.println("=============================================");
    }

    private final HashMap<String, ArrayList<String>> graph;
    private HashMap<String, Boolean> visitedDFS;
    private HashMap<String, Boolean> visitedBFS;
    private double bfsTime;
    private double dfsTime;
}
