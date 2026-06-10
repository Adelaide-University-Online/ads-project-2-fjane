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
    public int getNumVertices() {
        return vertices.size();
    }

    /** Return the number of edges. */
    public int getNumEdges() {
        return numEdges;
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
     * Returns a Vertex's ID (or null) by searching the vertices reverse loop map name.
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

    // Modifier methods

    /**
     * Creates a new Vertex, adds it to the graph's vertices map and the vertices reverse lookup map.
     * @param id an integer ID
     * @param name a String name
     * @throws IllegalArgumentException if vertex ID or name already exists
     */
    public void addVertex(int id, String name) throws IllegalArgumentException{
        // Check for duplicate Vertex ID
        if (vertices.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Vertex ID " + id + " already exists.");
        }

        // Check for duplicate Vertex name
        if (nameToId.containsKey(name)) {
            throw new IllegalArgumentException(
                    "Vertex name " + name + " already exists.");
        }

        // Add new Vertex to the vertices and vertices reverse look up maps
        vertices.put(id, new Vertex(id, name));
        nameToId.put(name, id);

        // Initialize vertex entry in edges, in-degree and out-degree maps
        edges.put(id, new ArrayList<>());
        inDegree.put(id,0);
        outDegree.put(id,0);
    }

    /**
     * Creates a new Edge, adds it to the graph's edges map if it doesn't already exist.
     * Verifies that the proposed Edge's endpoints exist in the graph's vertices map first.
     * @param sourceId an integer id of a Vertex
     * @param destinationId an integer id of a Vertex
     */
    public boolean addEdge(int sourceId, int destinationId) {
        // Checks if source vertex exists in the vertices map
        if(!isVertex(sourceId)) {
            throw new IllegalArgumentException("Edge source endpoint does not exist.");
        }

        // Checks if destination vertex exists in the vertices map
        if(!isVertex(destinationId)) {
            throw new IllegalArgumentException("Edge destination endpoint does not exist.");
        }

        // Check if Edge already exists in edges map, if so return to avoid duplication
        if(isEdge(sourceId, destinationId)) {
            return false;
        }

        // Adds new Edge to edges map
        edges.get(sourceId).add(new Edge(sourceId, destinationId));

        // Increment in and out-degree maps
        inDegree.put(destinationId, inDegree.get(destinationId) + 1);
        outDegree.put(sourceId, outDegree.get(sourceId) + 1);

        // Increment edge count
        numEdges += 1;

        // Adds new Edge to the adjacency map in the opposite direction for undirected graphs
        if(!directed) {
            edges.get(destinationId).add(new Edge(destinationId, sourceId));
            inDegree.put(sourceId, inDegree.get(sourceId) + 1);
            outDegree.put(destinationId, outDegree.get(destinationId) + 1);
            numEdges += 1;
        }

        return true;
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

    /** Returns a String representation of an adjacency list. Each line contains a Vertex, and it's outgoing Edges. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Add each edges (adjacency list) entry to StringBuilder
        for (Map.Entry<Integer, List<Edge>> entry : edges.entrySet()) {

            // Use source vertex's ID to look up and append name
            int sourceId = entry.getKey();
            sb.append(vertices.get(sourceId).getName()).append(": [");

            // Iterate through list of outgoing Edges
            Iterator<Edge> edgeIterator = entry.getValue().iterator();
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();

                // Append destination Vertex name
                sb.append(vertices.get(edge.getDestination()).getName());

                if (edgeIterator.hasNext()) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }

        return sb.toString();
    }
}
