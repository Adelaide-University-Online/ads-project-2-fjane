/**
 * File: Edge.java
 * Description: This class represents an edge between two vertices in a graph. An edge can be weighted and assigned
 * values to represent significance such as distance, cost or time. Otherwise, unweighted edges in a graph share
 * the same level of significance. Vertices are of String type.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public class Edge {
    // Attributes
    private final String source;
    private final String destination;
    private double weight;

    // Constructors
    public Edge(String source, String destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(String source, String destination) {
        this.source = source;
        this.destination = destination;
        this.weight = 1.0;
    }

    // Accessors

    /** Return the source vertex of an edge. */
    public String getSource() {
        return source;
    }

    /** Return the destination vertex of an edge. */
    public String getDestination() {
        return destination;
    }

    /** Return the weight of an edge. */
    public double getWeight() {
        return weight;
    }

}
