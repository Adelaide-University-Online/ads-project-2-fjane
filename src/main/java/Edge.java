/**
 * File: Edge.java
 * Description: This class represents an edge between two vertices in a graph. An edge can be weighted and assigned
 * values to represent significance such as distance, cost or time. Otherwise, unweighted edges in a graph share
 * the same level of significance.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public class Edge {

    // Attributes
    private final int sourceId;
    private final int destinationId;
    private double weight;

    // Constructors
    public Edge(int sourceId, int destinationId, double weight) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.weight = weight;
    }

    public Edge(int sourceId, int destinationId) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.weight = 1.0;
    }

    // Accessor methods

    /** Returns the source vertex of an edge. */
    public int getSource() {
        return sourceId;
    }

    /** Returns the destination vertex of an edge. */
    public int getDestination() {
        return destinationId;
    }

    /** Returns the weight of an edge. */
    public double getWeight() {
        return weight;
    }

    // Modifier methods

    /** Sets the weight of an edge. */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Other methods

    /** Returns a String representation of this Edge object including Vertex endpoints and edge weight. */
    @Override
    public String toString() {
        return "Edge: " + sourceId + " -> " + destinationId + " (" + weight + ")";
    }
}
