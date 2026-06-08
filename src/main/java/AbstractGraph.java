import java.util.*;

/**
 * File: AbstractGraph.java
 * Description: This is an abstract base class for graphs that implements the Graph interface. A graph is a non-linear
 * data structure that consists of vertices and edges.
 * A graph can be:
 * undirected - edges represent a two-way connection between vertices.
 * directed - edges represent a one-way connection between vertices. Source and destination vertices must be defined.
 * weighted - edges have assigned values to represent significance such as distance, cost or time.
 * unweighted - edges do not have assigned values and all connections are considered equal.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public abstract class AbstractGraph implements Graph{

    // Attributes
    protected Map<Integer, Vertex> vertices;
    protected Map<Integer, List<Edge>> edges;
    protected Map<String, Integer> nameToId; // Reverse look up map for vertices
    protected boolean directed;
    protected boolean weighted;
    protected Map<Integer, int[]> degrees; // Vertex: [0] in-degree, [1] out-degree

    // Constructors
    public AbstractGraph(Map<Integer, Vertex> vertices, Map<Integer, List<Edge>> edges, boolean directed, boolean weighted) {
        this.vertices = vertices;
        this.edges = edges;
        this.directed = directed;
        this.weighted = weighted;
    }

    public AbstractGraph(Map<Integer, Vertex> vertices, Map<Integer, List<Edge>> edges, boolean directed) {
        this.vertices = vertices;
        this.edges = edges;
        this.directed = directed;
        this.weighted = false;
    }

    public AbstractGraph() {
        this.degrees = new HashMap<>();
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

    // Modifier methods

    /**
     * Creates a new Vertex, adds it to the graph's vertices map and the vertices reverse lookup map.
     * @param id an integer id
     * @param name a String name
     */
    public void addVertex(int id, String name) {
        // Adds a new Vertex to the vertices map
        vertices.put(id, new Vertex(id, name));

        // Adds the new Vertex to the vertices reverse look up map
        nameToId.put(name, id);
    }

    /**
     * Creates a new Edge, adds it to the graph's edges map. Verifies that the proposed Edge's endpoints exist in the
     * graph's vertices map first.
     * @param sourceId an integer id of a Vertex
     * @param destinationId an integer id of a Vertex
     */
    public void addEdge(int sourceId, int destinationId) {
        // Checks if the source Vertex exists in the vertice map
        if(!vertices.containsKey(sourceId)) {
            throw new NoSuchElementException("Edge source vertex was not found in vertex list.");
        }

        // Checks if the destination Vertex exists in the vertice map
        if(!vertices.containsKey(destinationId)) {
            throw new NoSuchElementException("Edge destination vertex was not found in vertex list.");
        }

        // Adds new Edge to the edges map
        edges.computeIfAbsent(sourceId, _ -> new ArrayList<>()).add(new Edge(sourceId, destinationId));

        // Adds new Edge to the edges map in the opposite direction for undirected graphs
        if(!directed) {
            edges.computeIfAbsent(destinationId, _ -> new ArrayList<>()).add(new Edge(destinationId, sourceId));
        }
    }

    /* Class template inspired by:
    Koffman, E.B., & Wolfgang, P.A.T. (2015). Data Structures: Abstraction and Design Using Java: Chapter 10 Graphs.
    (3rd ed.). Wiley. http://ebookcentral.proquest.com/lib/adelaideuni/detail.action?docID=5106355
    */
}
