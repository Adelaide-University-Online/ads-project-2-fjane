import java.util.*;

/**
 * File: GraphAlgorithms.java
 * Description: This class is intended to be a collection of algorithms that can be applied to directed graphs.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public class GraphAlgorithms {
    static Map<String, int[]> degrees;

    /**
     * Creates a map of each vertex and the number of incoming and outgoing edges it has.
     * @param graph A graph represented as an adjacency list
     * @return A map of each vertex as a key and its degrees as values
     */
    public static Map<String, int[]> degreesMap (AbstractGraph graph) {
        degrees = new HashMap<>();

        // Initialize with 0 for each vertex
        for (String vertex : graph.getVertices()) {
            degrees.put(vertex, new int[]{0,0});

        }

        // Count incoming and outgoing edges for each vertex
        for (String vertex : graph.getVertices()) {
            Iterator<Edge> edgeIterator = graph.edgeIterator(vertex);

            // Loop through all edges
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();

                // Increment source's out-degree
                degrees.get(vertex)[1]++;

                // Increment destination's in-degree
                degrees.get(edge.getDestination())[0]++;
            }
        }

        return degrees;
    }

    /**
     * Applies topological sort using a modified version of Kahn's Breadth-First Search algorithm to arrange vertices
     * of a directed acyclic graph in linear order ensuring ordering does not violate vertex ordering of directed edges.
     * This modified version uses a priority queue based on vertices with the highest number of outgoing edges and
     * no incoming edges.
     * @return A list of elements that obey edge directionality
     * @throws IllegalStateException if a cycle is detected
     */
    public static List<String> kahnsTopological(AbstractGraph graph) {
        // Build a hashmap to store each vertex's in and out-degree
        degrees = degreesMap(graph);

        // Create a priority queue to store elements with an in-degree of 0, prioritized by highest out-degree
        PriorityQueue<String> noIncoming = new PriorityQueue<>(
                // Descending order comparison of vertices out-degree
                (a, b) -> Integer.compare(degrees.get(b)[1], degrees.get(a)[1])
        );

        // Add elements with in-degree of 0 to priority queue
        for (Map.Entry<String, int[]> entry : degrees.entrySet()) {
            if (entry.getValue()[0] == 0){
                noIncoming.add(entry.getKey());
            }
        }

        // Create a list to store topological sort result
        List<String> topoResult = new ArrayList<>();

        // Process vertices in priority queue
        while (!noIncoming.isEmpty()) {

            // Remove first vertex in sort queue and add to result list
            String current = noIncoming.poll();
            topoResult.add(current);

            // Iterate through other vertices that are connected to the current vertex
            Iterator<Edge> edgeIterator = graph.edgeIterator(current);

            // Update inDegree for all current vertex's neighbours
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();

                // Get neighbour of current vertex
                String neighbour = edge.getDestination();

                // Decrement in-degree
                degrees.get(neighbour)[0]--;

                // If neighbour now has in-degree of 0, add it to the queue
                if (degrees.get(neighbour)[0] == 0) noIncoming.add(neighbour);
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
