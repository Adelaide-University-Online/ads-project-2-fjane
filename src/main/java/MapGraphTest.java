import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MapGraphTest {

    // Attributes
    private MapGraph mapDirectedWeighted;
    private MapGraph mapUndirectedNotWeighted;

    @BeforeEach
    void setUp() {
        mapDirectedWeighted = new MapGraph(true, true);
        mapUndirectedNotWeighted = new MapGraph(false, false);

        mapDirectedWeighted.addVertex(0, "A");
        mapDirectedWeighted.addVertex(1, "B");
        mapDirectedWeighted.addEdge(0,1);

        mapUndirectedNotWeighted.addVertex(2, "C");
        mapUndirectedNotWeighted.addVertex(3, "D");
        mapUndirectedNotWeighted.addEdge(2,3);
    }

    // Graph directionality tests

    @Test
    void testDirected() {
        assertTrue(mapDirectedWeighted.isDirected());
    }

    @Test
    void testUndirected() {
        assertFalse(mapUndirectedNotWeighted.isDirected());
    }

    // Graph weighted tests

    @Test
    void testWeighted() {
        assertTrue(mapDirectedWeighted.isWeighted());
    }

    @Test
    void testUnweighted() {
        assertFalse(mapUndirectedNotWeighted.isWeighted());
    }

    // Vertex tests

    @Test
    void testVertexCount() {
        assertEquals(2, mapDirectedWeighted.getNumVertices());
    }

    @Test
    void testDuplicateVerticesID() {
        assertThrows(IllegalArgumentException.class, () ->
                        mapDirectedWeighted.addVertex(0, "AA"),
                "Exception should be thrown when attempting to add a new vertex that has an existing id.");
    }

    @Test
    void testDuplicateVerticesName() {
        assertThrows(IllegalArgumentException.class, () ->
                        mapDirectedWeighted.addVertex(10, "B"),
                "Exception should be thrown when attempting to add a new vertex that has an existing name.");
    }

    // Edge tests

    @Test
    void testEdgeCount() {
        assertEquals(1, mapDirectedWeighted.getNumEdges());
    }

    @Test
    void testDuplicateEdges() {
        assertFalse(mapDirectedWeighted.addEdge(0,1),
                "Duplicate edges are not allowed.");
    }

    @Test
    void testEdgeValidation(){
        assertTrue(mapDirectedWeighted.isEdge(0, 1));
    }

    // Undirected graph edge and vertices test

    @Test
    void testUndirectedEdges() {
        assertEquals(2, mapUndirectedNotWeighted.getNumEdges(),
                "Edge count should be double the number of edges added.");
    }

    @Test
    void testUndirectedVertices() {
        assertEquals(2, mapUndirectedNotWeighted.getNumVertices(),
                "Number of vertices should remain the same.");
    }

    @Test
    void testUndirectedEdgeValidation(){
        assertTrue(mapUndirectedNotWeighted.isEdge(3, 2),
                "Reverse edge should automatically be created.");
    }
}