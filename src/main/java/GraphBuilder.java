import java.util.Scanner;

/**
 * File: GraphBuilder.java
 * Description: This class includes helper methods to build graphs including loading data from text files and getting
 * user input.
 * Author: Florence Sayavongsa
 * Student ID: 3056629
 * Email ID: florence.sayavongsa@student.adelaide.edu.au
 * AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/
public class GraphBuilder {

    /**
     * Creates graph's vertices and edges by parsing data from a text file.
     * The first line of the file must be a list of vertex labels separated by a comma.
     * Following lines outline a vertex and its dependent separated by a comma.
     * Any line in the text file that contains multiple elements is an indication of an edge where destination vertices
     * are listed to the left of their dependent vertex.
     * @param scan Scanner to read in text file
     */
    public static MapGraph createMapFromFile(Scanner scan, boolean directed, boolean weighted)  {

        MapGraph graph = new MapGraph(directed, weighted);

        // Read in first line. Splice and save each vertex label to a list
        String[] labels = scan.nextLine().trim().split(",\\s*");

        // Add each vertex to graph
        for(int i = 0; i < labels.length; i++) {
            graph.addVertex(i, labels[i]);
        }

        // Loop through the remaining lines of the text file
        while (scan.hasNextLine()) {

            // Process one line at a time
            String line = scan.nextLine().trim();
            if (line.isEmpty()) continue;

            // Splice line by comma and white spaces
            String[] parts = line.split(",\\s*");

            // For each vertex pair, add a new edge to the edge map.
            for (int i = 0; i < parts.length-1; i++) {
                // Use the vertices reverse lookup map to get the Vertex's ID
                int destinationId = graph.getVertexId(parts[i]);
                int sourceId = graph.getVertexId(parts[i + 1]);

                graph.addEdge(sourceId, destinationId);
            }
        }

        return graph;
    }

}
