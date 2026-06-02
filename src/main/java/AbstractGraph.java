import java.util.Set;

/**
 * File: AbstractGraph.java
 * Description: This is an abstract base class for graphs that implements the Graph interface.
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

}
