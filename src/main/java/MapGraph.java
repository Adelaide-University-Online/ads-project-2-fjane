import java.util.*;

/**
 * File: MapGraph.java
 * Description: This is a representation of a graph that uses an adjacency-map to represent vertices and edges.
 * MapGraph is an extension of the AbstractGraph class.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

public class MapGraph {

    // Attributes
    private Map<Integer, Vertex> vertices;
    private Map<Integer, List<Edge>> edges; // Adjacency list
    private Map<String, Integer> nameToId; // Reverse look up map for vertices
    private Map<Integer, Integer> inDegree;
    private Map<Integer, Integer> outDegree;
    private boolean directed;
    private boolean weighted;
    private int numVertices;
    private int numEdges;

    // Constructor methods

    // Constructor - 2 parameters
    public MapGraph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
        this.nameToId = new HashMap<>();
        this.inDegree = new HashMap<>();
        this.outDegree = new HashMap<>();
    }

    // Overloaded constructor - 1 parameter and unweighted default
    public MapGraph(boolean directed) {
        this.directed = directed;
        this.weighted = false;
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
        this.nameToId = new HashMap<>();
        this.inDegree = new HashMap<>();
        this.outDegree = new HashMap<>();
    }

    // No argument constructor with undirected and unweighted defaults
    public MapGraph() {
        this.directed = false;
        this.weighted = false;
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
        this.nameToId = new HashMap<>();
        this.inDegree = new HashMap<>();
        this.outDegree = new HashMap<>();
    }

    // Accessor methods

    /** Return whether graph is directed. */
    public boolean isDirected() {
        return directed;
    }

    /** Return whether graph is weighted. */
    public boolean isWeighted() {
        return weighted;
    }

    /** Return the number of vertices. */
    public int numVertices() {
        return vertices.size();
    }

    /** Return the number of edges. */
    public int numEdges() {
        return edges.size();
    }

    /** Return all the vertices in a graph. */
    public Map<Integer, Vertex> getVertices(){
        return vertices;
    }

    /** Return all the edges in a graph. */
    public Map<Integer, List<Edge>> getEdges(){
        return edges;
    }

    /** Return all vertices with number of incoming edges. */
    public Map<Integer, Integer> getInDegrees() {
        return inDegree;
    }

    /** Return all vertices with number of outgoing edges. */
    public Map<Integer, Integer> getOutDegrees() {
        return outDegree;
    }

    /**
     * Returns a Vertex's ID (or null) by searching the verticse reverse loop map name.
     * @param name Vertex String name
     */
    public int getVertexId(String name) {
        Integer id = nameToId.get(name);

        // Null check
        if (id == null) {
            throw new IllegalArgumentException("Unknown vertex name: " + name);
        }

        return id;
    }

    // Other methods

    /**
     * Checks if a vertex already exists in vertices map by looking for duplication in either vertex ID or name.
     * @param vertexId ID of vertex to be validated
     */
    private boolean isVertex(int vertexId) {
        // Check if vertexId exists
        return vertices.containsKey(vertexId);
    }

    /**
     * Checks if Edge already exists in edges map.
     * @param sourceId source Vertex's ID
     * @param destinationId destination Vertex's ID
     * @return false if Edge is not present
     */
    public boolean isEdge(int sourceId, int destinationId) {
        // Get Map entry where key = sourceID
        List<Edge> sourceEdges = edges.get(sourceId);

        // False if no entry is found
        if (sourceEdges == null) {
            return false;
        }

        // Iterate through Map entry's values searching for destinationID
        for (Edge edge : sourceEdges) {
            // True if destinationId is found
            if (edge.getDestination() == destinationId) {
                return true;
            }
        }

        return false;
    }
}
