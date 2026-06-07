import java.util.*;
import java.util.stream.Collectors;

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
    private Map<String, Set<Edge>> adjacencyMap;

    // Constructor
    public MapGraph(Set<String> vertices, Set<Edge> edgeSet, boolean directed, boolean weighted) {
        super(vertices, new LinkedHashSet<>(), directed, weighted);
        this.adjacencyMap = new LinkedHashMap<>();

        // Add vertices to adjacency map as keys
        for (String vertex : vertices) {
            adjacencyMap.put(vertex, new LinkedHashSet<>());
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
        adjacencyMap.putIfAbsent(source, new LinkedHashSet<>());
        adjacencyMap.get(source).add(edge);
        this.edgeSet.add(edge);

        // Update degrees map incrementally
        degrees.putIfAbsent(source, new int[]{0, 0});
        degrees.putIfAbsent(destination, new int[]{0, 0});
        degrees.get(source)[1]++;       // source out-degree
        degrees.get(destination)[0]++;  // destination in-degree

        // Add edge in both directions if graph is undirected
        if (!directed) {
            Edge reverseEdge = new Edge(destination, source);
            adjacencyMap.putIfAbsent(destination, new LinkedHashSet<>());
            adjacencyMap.get(destination).add(reverseEdge);
            this.edgeSet.add(reverseEdge);

            // Update degrees map for edges in the opposite direction
            degrees.get(destination)[1]++;
            degrees.get(source)[0]++;
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
        for (Map.Entry<String, Set<Edge>> entry : adjacencyMap.entrySet()) {
            // Source vertex
            sb.append(entry.getKey()).append(": ");

            // Add destination vertices to the right
            String destinations = entry.getValue().stream()
                    .map(Edge::getDestination)
                    .collect(Collectors.joining(", "));
            sb.append(destinations).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns an iterator over all vertices connected to a given vertex.
     * @param source The source vertex
     * @return all given vertex's neighbours
     */
    @Override
    public Iterator<Edge> edgeIterator(String source) {
        // Check to see if vertex exists in map
        if (!adjacencyMap.containsKey(source)) {
            throw new NoSuchElementException("Vertex not found: " + source);
        }

        return adjacencyMap.get(source).iterator();
    }

}
