import java.util.*;

/**
 * File: GraphAlgorithms.java
 * Description: This class is a collection of algorithms that can be applied to directed graphs.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public class GraphAlgorithms {

    /**
     * Applies topological sort using Kahn's Breadth-First Search algorithm to arrange vertices of a directed acyclic
     * graph in linear order ensuring vertex ordering of directed edges is not violated.
     * @return List of sorted vertices
     * @throws IllegalStateException if a cycle is detected
     */
    public static List<Integer> kahnsTopological(MapGraph graph) {

        // Create a new map to hold vertex in-degree information (to prevent mutating graph state)
        Map<Integer, Integer> inDegree = graph.getInDegrees();

        // Create a queue and add vertices with 0 in-degree
        Queue<Integer> noIncoming = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) noIncoming.add(entry.getKey());
        }

        // Create a list to store topological sort result
        List<Integer> topoResult = new ArrayList<>();

        // Process vertices in queue
        while (!noIncoming.isEmpty()) {

            // Remove first vertex in sort queue and add to result list
            int current = noIncoming.poll();
            topoResult.add(current);

            // Iterate through other vertices that are connected to the current vertex
            Iterator<Edge> edgeIterator = graph.edgeIterator(current);

            // Update inDegree for all current vertex's neighbours
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();

                // Get neighbour of current vertex
                int neighbour = edge.getDestination();

                // Decrement in-degree as 'current' vertex (source vertex) has now been processed
                inDegree.put(neighbour, inDegree.get(neighbour) - 1);

                // If neighbour now has in-degree of 0, add it to the queue
                if (inDegree.get(neighbour) == 0) noIncoming.add(neighbour);
            }
        }

        // Cycle detection
        if (topoResult.size() != graph.getVertices().size()) {
            throw new IllegalStateException("Graph contains a cycle — topological sort not possible");
        }

        return topoResult;
    }
    /* Code inspired by:
    Interview Cake. (n.d). Topological Sort. https://www.interviewcake.com/concept/java/topological-sort
    */
}
