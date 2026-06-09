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
    protected Map<Integer, Vertex> vertices;
    protected Map<Integer, List<Edge>> edges; // Adjacency list
    protected Map<String, Integer> nameToId; // Reverse look up map for vertices
    protected Map<Integer, Integer> inDegree; //
    protected Map<Integer, Integer> outDegree; //
    protected boolean directed;
    protected boolean weighted;

    // Constructor
    public MapGraph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
    }

}
