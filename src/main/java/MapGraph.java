import java.util.*;

/**
 * File: MapGraph.java
 * Description: This is a graph implementation that uses an adjacency-map to represent String vertices and edges. MapGraph is
 * an extension of the AbstractGraph class.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

public class MapGraph extends AbstractGraph {
    // Attributes
    private Map<String, List<String>> adjacencyMap;

    // Constructor
    public MapGraph(Set<String> vertices, Set<Edge> edgeSet, boolean isDirected, boolean isWeighted) {
        super(vertices, edgeSet, isDirected, isWeighted);
        this.adjacencyMap = new LinkedHashMap<>();

        // Add vertices to adjacency map as keys
        for (String vertex : vertices) {
            adjacencyMap.put(vertex, new LinkedList<String>());
        }

        // Add edges to corresponding vertices in adjacency map
        for (Edge edge : edgeSet) {
            insert(edge);
        }
    }

    /**
     * Adds new edges to the graph if it doesn't already exist.
     * @param edge Edge to be added
     */
    public void insert(Edge edge) {
        String source = edge.getSource();
        String destination = edge.getDestination();

        // Add edge by searching for map key (source vertex)
        adjacencyMap.computeIfAbsent(source, s -> new LinkedList<>()).add(destination);

        // Add edge in both directions if graph is undirected
        if (!directed) {
            adjacencyMap.computeIfAbsent(destination, d -> new LinkedList<>()).add(source);
        }
    }

    /**
     * Returns a String representation of an adjacency map. Vertices are listed one per line with any connected vertices
     * to the right such that a vertex with multiple outgoing edges would be represented in the following format.
     * source vertex: destination vertex, different destination vertex
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Add each map entry to StringBuilder one at a time
        for (Map.Entry<String, List<String>> entry : adjacencyMap.entrySet()) {
            // Source vertex
            sb.append(entry.getKey()).append(": ");

            // Add destination vertices to the right
            String destinations = String.join(", ", entry.getValue());
            sb.append(destinations);

            sb.append("\n");
        }
        return sb.toString();
    }
}
