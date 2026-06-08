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
    private final Vertex source;
    private final Vertex destination;
    private double weight;

    // Constructors
    public Edge(Vertex source, Vertex destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
        this.weight = 1.0;
    }

    // Accessors

    /** Return the source vertex of an edge. */
    public Vertex getSource() {
        return source;
    }

    /** Return the destination vertex of an edge. */
    public Vertex getDestination() {
        return destination;
    }

    /** Return the weight of an edge. */
    public double getWeight() {
        return weight;
    }

    // Modifiers

    /** Set the weight of an edge. */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Other methods

    /**
     * Compares an object with this.instance.
     * @param obj   The reference object to be compared
     * @return true if the comparison Edge object has the same source and destination vertices, and edge weight
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
        return source.equals(other.source) && destination.equals(other.destination) && weight == other.weight;
    }

    /**
     * Generates an edge's hashcode value based on source and destination vertices, and edge weight.
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
        return 31 * source.hashCode() * destination.hashCode() * Double.hashCode(weight);
    }

    /**
     * Returns a String representation of this Edge object.
     * @return formatted String containing source and destination vertices, and edge weight
     */
    @Override
    public String toString() {
        return "Edge: " + source + " -> " + destination + " (" + weight + ")";
    }
}
