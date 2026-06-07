import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * File: Graph.java
 * Description: This interface includes operations used to construct, manipulate, and traverse a graph data structure.
 * This interface is currently set up to be used with String vertices and Edge class objects.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public interface Graph {

    /** Return the number of vertices. */
    int numVertices();

    /** Return the number of edges. */
    int numEdges();

    /** Return all the vertices in a graph. */
    Set<String> getVertices();

    /** Return all the edges in a graph. */
    Set<Edge> getEdgeSet();

    /** Return all vertices with number of incoming edges. */
    Map<String, Integer> getInDegrees();

    /** Return all vertices with number of outgoing edges. */
    Map<String, Integer> getOutDegrees();

    /** Return true if the graph is directed.  */
    boolean isDirected();

    /** Return true if the graph is weighted. */
    boolean isWeighted();

    /** Add a new Edge into the graph. */
    void insert(Edge edge);

    /** Return an iterator to the outgoing edges of a vertex.
     * @param source The source vertex
     */
    Iterator<Edge> edgeIterator(String source);

    /* Code inspired by:
    Koffman, E.B., & Wolfgang, P.A.T. (2015). Data Structures: Abstraction and Design Using Java: Chapter 10 Graphs.
    (3rd ed.). Wiley. http://ebookcentral.proquest.com/lib/adelaideuni/detail.action?docID=5106355
    */
}
