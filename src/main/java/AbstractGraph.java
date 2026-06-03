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
public class AbstractGraph {

    // Attributes
    protected Set<String> vertices;
    protected Set<Edge> edgeSet;
    protected boolean directed;
    protected boolean weighted;

    // Constructors
    public AbstractGraph(Set<String> vertices, Set<Edge> edgeSet, boolean directed, boolean weighted) {
        this.vertices = vertices;
        this.edgeSet = edgeSet;
        this.directed = directed;
        this.weighted = weighted;
    }

    public AbstractGraph(Set<String> vertices, Set<Edge> edgeSet, boolean directed) {
        this.vertices = vertices;
        this.edgeSet = edgeSet;
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

    /**
     * Check if an edge's endpoints are in vertex list.
     */
    public void endpointValidation(){
        for (Edge edge: edgeSet) {
            if (!vertices.contains(edge.getSource())) {
                throw new IllegalStateException(
                        edge.getSource() + " was not found."
                );
            }
            if (!vertices.contains(edge.getDestination())) {
                throw new IllegalStateException(
                        edge.getDestination() + " was not found."
                );
            }
        }
    }

    /* Code inspired by:
    Koffman, E.B., & Wolfgang, P.A.T. (2015). Data Structures: Abstraction and Design Using Java: Chapter 10 Graphs.
    (3rd ed.). Wiley. http://ebookcentral.proquest.com/lib/adelaideuni/detail.action?docID=5106355
    */
}
