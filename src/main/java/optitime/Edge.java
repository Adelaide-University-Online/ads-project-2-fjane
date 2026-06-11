package optitime;

import java.util.Objects;

/**
 * File: optitime.Edge.java
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

    /**
     * Compares an object with this.instance.
     * @param obj   The reference object to be compared
     * @return true if the comparison optitime.Edge object has the same sourceId and destinationId
     */
    @Override
    public boolean equals(Object obj) {
        // Check self comparison
        if (this == obj) return true;

        // Check if obj is null
        if(obj == null){
            return false;
        }

        // Check if objects are different classes
        if (!(obj instanceof Edge)) return false;

        // Cast object
        Edge other = (Edge) obj;

        // Compare source and destination vertices of the edges
        return sourceId == other.sourceId && destinationId == other.destinationId;
    }

    /** Returns an edge's hashcode value based on endpoints. */
    @Override
    public int hashCode() {
        return Objects.hash(sourceId, destinationId);
    }

    /** Returns a String representation of this optitime.Edge object including optitime.Vertex endpoints and edge weight. */
    @Override
    public String toString() {
        return "optitime.Edge: " + sourceId + " -> " + destinationId + " (" + weight + ")";
    }
}
