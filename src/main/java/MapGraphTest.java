import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MapGraphTest {

    protected Set<String> vertexSet;
    protected Set<Edge> edgeSet;
    protected AbstractGraph mapDirectedWeighted;
    protected AbstractGraph mapUndirectedNotWeighted;

    @BeforeEach
    void setUp() {
        vertexSet = new LinkedHashSet<>();
        vertexSet.add("A");
        vertexSet.add("B");
        vertexSet.add("C");
        vertexSet.add("C");

        edgeSet = new HashSet<>();
        edgeSet.add(new Edge("A", "B"));
        edgeSet.add(new Edge("B", "C"));
        edgeSet.add(new Edge("A", "C"));
        edgeSet.add(new Edge("A", "C"));

        mapDirectedWeighted = new MapGraph(vertexSet, edgeSet, true, true);
        mapUndirectedNotWeighted = new MapGraph(vertexSet, edgeSet, false, false);
    }

    @Test
    void testDuplicateVertices() {
        assertEquals(3, mapDirectedWeighted.numVertices(), "Set prevents adding duplicate vertices.");
    }

    @Test
    void testDuplicateEdges() {
        assertEquals(3, mapDirectedWeighted.numEdges(), "Set prevents adding duplicate edges.");
    }

    @Test
    void testUndirectedVertices() {
        assertEquals(3, mapUndirectedNotWeighted.numVertices(), "Number of vertices should remain the same.");
    }

    @Test
    void testUndirectedEdges() {
        assertEquals(6, mapUndirectedNotWeighted.numEdges(),
                "Edge count should double as a new edge in the reverse direction is created.");
    }

    @Test
    void isDirected() {
        assertTrue(mapDirectedWeighted.isDirected());
    }

    @Test
    void isWeighted() {
        assertTrue(mapDirectedWeighted.isWeighted());
    }

    @Test
    void isNotDirected() {
        assertFalse(mapUndirectedNotWeighted.isDirected());
    }

    @Test
    void isNotWeighted() {
        assertFalse(mapUndirectedNotWeighted.isWeighted());
    }

    @Test
    void testEndpointValidation() {
        edgeSet.add(new Edge("C", "E"));

        assertThrows(NoSuchElementException.class, () ->
                AbstractGraph.endpointValidation(vertexSet, edgeSet),
                "Exception should be thrown when attempting to add a new edge with a vertex that doesn't exist in the vertex set.");
    }
}