/**
 * File: Vertex.java
 * Description: This class represents a single vertex in a graph.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public class Vertex {

    // Attributes
    private final int id;
    private String name;

    // Constructor
    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Accessor methods

    /** Return the ID of a vertex. */
    public int getID() {
        return id;
    }

    /** Return the name of a vertex. */
    public String getName() {
        return name;
    }

    // Modifier method
    /** Update the name of a vertex. */
    public void setName(String name) {
        this.name = name;
    }

    // Other methods
    @Override
    public boolean equals(Object obj){
        // Check if objects are the same item
        if(this == obj){
            return true;
        }

        // Check if obj is null
        if(obj == null){
            return false;
        }

        // Check if objects are different classes
        if(!(getClass() == obj.getClass())){
            return false;
        }

        // Cast object
        Vertex other = (Vertex) obj;

        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public String toString() {
        return name + " (" + id + ")";
    }
}
