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
    protected boolean directed;
    protected boolean weighted;

    // Constructors
    public AbstractGraph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
    }

    public AbstractGraph(boolean directed) {
        this.directed = directed;
        this.weighted = false;
    }

    public AbstractGraph() {
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

    // Other methods

    /**
     * Creates graph's vertices and edges by parsing data from a text file.
     * The first line of the file must be a list of vertex labels separated by a comma.
     * Following lines outline a vertex and its dependent separated by a comma.
     * Any line in the text file that contains multiple elements is an indication of an edge where destination vertices
     * are listed to the left of their dependent vertex.
     * @param scan Scanner to read in text file
     */
    public void loadVertexEdgesFromFile(Scanner scan)  {

        // Read in first line. Splice and save each vertex label to a list
        String[] labels = scan.nextLine().trim().split(",\\s*");

        // Add each vertex to graph
        for(int i = 0; i < labels.length; i++) {
            addVertex(i, labels[i]);
        }

        // Loop through the remaining lines of the text file
        while (scan.hasNextLine()) {

            // Process one line at a time
            String line = scan.nextLine().trim();
            if (line.isEmpty()) continue;

            // Splice line by comma and white spaces
            String[] parts = line.split(",\\s*");

            // For each vertex pair, add a new edge to the edge map.
            for (int i = 0; i < parts.length-1; i++) {
                // Use the vertices reverse lookup map to get the Vertex's ID
                int destinationId = getVertixId(parts[i]);
                int sourceId = getVertixId(parts[i + 1]);

                addEdge(sourceId, destinationId);
            }
        }
    }

    /* Class template inspired by:
    Koffman, E.B., & Wolfgang, P.A.T. (2015). Data Structures: Abstraction and Design Using Java: Chapter 10 Graphs.
    (3rd ed.). Wiley. http://ebookcentral.proquest.com/lib/adelaideuni/detail.action?docID=5106355
    */
}
