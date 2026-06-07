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
    protected Set<String> vertices;
    protected Set<Edge> edgeSet;
    protected boolean directed;
    protected boolean weighted;
    protected Map<String, int[]> degrees; // Vertex: [0] in-degree, [1] out-degree

    // Constructors
    public AbstractGraph(Set<String> vertices, Set<Edge> edgeSet, boolean directed, boolean weighted) {
        this.vertices = vertices;
        this.edgeSet = edgeSet;
        this.directed = directed;
        this.weighted = weighted;
        initializeDegrees(vertices);
    }

    public AbstractGraph(Set<String> vertices, Set<Edge> edgeSet, boolean directed) {
        this.vertices = vertices;
        this.edgeSet = edgeSet;
        this.directed = directed;
        this.weighted = false;
        initializeDegrees(vertices);
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
        return edgeSet.size();
    }

    /** Return all the vertices in a graph. */
    public Set<String> getVertices(){
        return vertices;
    }

    /** Return all the edges in a graph. */
    public Set<Edge> getEdgeSet(){
        return edgeSet;
    }

    /** Return all vertices with number of incoming edges. */
    public Map<String, Integer> getInDegrees(){
        Map<String, Integer> inDegrees = new HashMap<>();
        for (Map.Entry<String, int[]> entry : degrees.entrySet()) {
            inDegrees.put(entry.getKey(), entry.getValue()[0]);
        }
        return inDegrees;
    }

    /** Return all vertices with number of outgoing edges. */
    public Map<String, Integer> getOutDegrees(){
        Map<String, Integer> outDegrees = new HashMap<>();
        for (Map.Entry<String, int[]> entry : degrees.entrySet()) {
            outDegrees.put(entry.getKey(), entry.getValue()[1]);
        }
        return outDegrees;
    }

    // Modifier methods

    /**
     * Initializes a graph's degree map with 0 in-degree and out-degree for each provided vertex.
     * Called by constructors when vertices are provided.
     * @param vertices Set of String vertices
     */
    private void initializeDegrees(Set<String> vertices) {
        this.degrees = new HashMap<>();
        for (String vertex : vertices) {
            degrees.put(vertex, new int[]{0, 0});
        }
    }

    // Other methods

    /**
     * Check if an edge's endpoints are in vertex list.
     */
    public static void endpointValidation(Set<String> vertexSet, Set<Edge> edgeSet){
        // Loop through each edge
        for (Edge edge: edgeSet) {

            // Check if source vertex is in vertices list
            if (!vertexSet.contains(edge.getSource())) {
                throw new NoSuchElementException("Edge endpoint was not found in vertex list: " + edge.getSource());
            }

            // Check if destination vertex is in vertices list
            if (!vertexSet.contains(edge.getDestination())) {
                throw new NoSuchElementException("Edge endpoint was not found in vertex list." + edge.getDestination());
            }
        }
    }

    /**
     * Creates a collection of outgoing edges by parsing data from a text file.
     * Any line in the text file that contains multiple elements is an indication of an edge where destination vertices
     * are listed to the left of their source vertices.
     * @param scan Scanner to read in text file
     * @return Edges stored in a Set
     */
    public static Set<Edge> loadEdgesFromFile(Scanner scan)  {

        Set<Edge> edgeSet = new HashSet<>();

        // Loop through each line of the file
        while (scan.hasNextLine()) {

            // Process one line at a time
            String line = scan.nextLine().trim();
            if (line.isEmpty()) continue;

            // Splice line by comma and white spaces
            String[] parts = line.split(",\\s*");

            // Create a new Edge(source, destination) for each vertex pair and add to edgeSet
            for (int i = 0; i < parts.length-1; i++) {
                Edge edge = new Edge(parts[i+1], parts[i]);
                edgeSet.add(edge);
            }
        }
        return edgeSet;
    }

    /**
     * Creates a new graph by building vertices and edges from imported data.
     * Text file format: first line is a list of comma separated vertices, each following line defines a vertex and if
     * they are connected to other vertices by an edge.
     * @param scan       Text data file scanner
     * @param isDirected true for a directed graph
     * @param isWeighted true for a weighted graph
     */
    public static AbstractGraph createGraph(Scanner scan, boolean isDirected, boolean isWeighted) {
        // Parse vertices in first line and save as Set
        String[] labels = scan.nextLine().trim().split(",\\s*");
        Set<String> vertexSet = new LinkedHashSet<>(Arrays.asList(labels));

        // Populate edges from file
        Set<Edge> edgeSet = loadEdgesFromFile(scan);

        // Validate edge endpoints
        endpointValidation(vertexSet,edgeSet);

        return new MapGraph(vertexSet, edgeSet, isDirected, isWeighted);
    }

    /* Class template inspired by:
    Koffman, E.B., & Wolfgang, P.A.T. (2015). Data Structures: Abstraction and Design Using Java: Chapter 10 Graphs.
    (3rd ed.). Wiley. http://ebookcentral.proquest.com/lib/adelaideuni/detail.action?docID=5106355
    */
}
