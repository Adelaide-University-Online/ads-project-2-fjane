import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * File: Graph.java
 * Description: This interface includes operations used to construct, manipulate, and traverse a graph data structure.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public interface Graph {

    /** Return the number of vertices. */
    int getNumVertices();

    /** Return the number of edges. */
    int getNumEdges();

    /** Return all the vertices in a graph. */
    Map<Integer, Vertex> getVertices();

    /** Return all the edges in a graph. */
    Map<Integer, List<Edge>> getEdges();

    /** Return all vertices with number of incoming edges. */
    Map<Integer, Integer> getInDegrees();

    /** Return all vertices with number of outgoing edges. */
    Map<Integer, Integer> getOutDegrees();

    /** Return a vertex ID by searching for vertex name. */
    int getVertexIdByName(String name);

    /** Return true if the graph is directed.  */
    boolean isDirected();

    /** Return true if the graph is weighted. */
    boolean isWeighted();

    /** Add a new Vertex to the graph. */
    void addVertex(int id, String name);

    /** Attempt to add new edge to graph and return boolean indicator of success. */
    boolean addEdge(int sourceId, int destinationId);

    /** Checks if a Vertex exists in a graph. */
    boolean isVertex(int id);

    /** Checks if an Edge exists in a graph. */
    boolean isEdge(int sourceId, int destinationId);

    /** Return an iterator to the outgoing edges of a vertex. */
    Iterator<Edge> edgeIterator(String source);

    /* Code inspired by:
    Koffman, E.B., & Wolfgang, P.A.T. (2015). Data Structures: Abstraction and Design Using Java: Chapter 10 Graphs.
    (3rd ed.). Wiley. http://ebookcentral.proquest.com/lib/adelaideuni/detail.action?docID=5106355
    */
}
