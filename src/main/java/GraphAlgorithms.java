import java.util.*;

/**
 * File: GraphAlgorithms.java
 * Description: This class is intended to be a collection of algorithms that can be applied to various graph types.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public class GraphAlgorithms {
    /**
     * This algorithm applies topological sort using Kahn's Breadth-First Search algorithm to arrange vertices of a
     * directed acyclic graph in linear order ensuring ordering does not violate vertex ordering of directed edges.
     * @return SortResult containing BFS levels and a prerequisites map per course.
     * @throws IllegalStateException if a cycle is detected.
     */
    public static List<String> khansTopological(AbstractGraph graph) {
        // Build a hashmap to store each vertex's in-degree
        Map<String, Integer> inDegree = new HashMap<>();

        // Initialize with 0 for each vertex
        for (String vertex : graph.getVertices()) {
            inDegree.put(vertex, 0);
        }

        // Count incoming edges for each vertex
        for (String vertex : graph.getVertices()) {
            Iterator<Edge> edgeIterator = graph.edgeIterator(vertex);

            // Loop through all edges
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();

                // Increment destinations in-degree
                inDegree.merge(edge.getDestination(), 1, Integer::sum);
            }
        }

        // Create a queue and add vertices with 0 in-degree
        Queue<String> noIncoming = new ArrayDeque<>();
        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) noIncoming.add(entry.getKey());
        }

        // Create a list to store topological sort result
        List<String> topoResult = new ArrayList<>();

        // Process vertices in queue
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
