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
    public static List<Integer> kahnsBFS(MapGraph graph) {

        // Create a new map to hold vertex in-degree information (to prevent mutating graph state)
        Map<Integer, Integer> inDegree = new HashMap<>(graph.getInDegrees());

        // Create a queue and add vertices with 0 in-degree
        Queue<Integer> noIncoming = new PriorityQueue<>();
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

    /**
     * Computes the longest prerequisite path length for every vertex.
     * @param graph A directed acyclic graph
     * @return A map of each vertex and its longest prerequisite chain length
     */
    public static Map<Integer, Integer> longestPath( MapGraph graph) {
        // Get Kahn's BFS result
        List<Integer> kahnsBFSOrder = kahnsBFS(graph);

        // Store the longest prerequisite path to get to each vertex. Initialize every vertex with 0 depth.
        Map<Integer, Integer> longestPath = new HashMap<>();
        for (Integer vertexId : graph.getVertices().keySet()) {
            longestPath.put(vertexId, 0);
        }

        // Iterate through all vertices in topological order
        for (Integer current : kahnsBFSOrder) {
            // Iterate through current vertex's neighbours
            Iterator<Edge> edgeIterator = graph.edgeIterator(current);
            while (edgeIterator.hasNext()) {
                int neighbour = edgeIterator.next().getDestination();

                // Increment path depth, neighbour must come at least one level after current
                longestPath.put(neighbour,
                        // Use the greater of existing value or incremented current
                        Math.max(longestPath.get(neighbour), longestPath.get(current) + 1));
            }
        }

        return longestPath;
    }
    /* Code inspired by:
    Geeks for Geeks. (2025, July 23). Longest Path in a Directed Acyclic Graph.
    https://www.geeksforgeeks.org/dsa/find-longest-path-directed-acyclic-graph/
    */
}
