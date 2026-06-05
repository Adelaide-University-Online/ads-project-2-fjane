import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class GraphAlgorithmsTest {

    protected Set<String> vertexSet;
    protected Set<Edge> edgeSet;
    protected AbstractGraph mapDirected;
    protected AbstractGraph mapUndirected;

    @BeforeEach
    void setUp() {
        vertexSet = new LinkedHashSet<>();
        vertexSet.add("A");
        vertexSet.add("B");
        vertexSet.add("C");
        vertexSet.add("D");
        vertexSet.add("E");

        edgeSet = new HashSet<>();
        edgeSet.add(new Edge("A", "B"));
        edgeSet.add(new Edge("B", "C"));
        edgeSet.add(new Edge("C", "D"));
        edgeSet.add(new Edge("D", "E"));
        edgeSet.add(new Edge("A", "D"));
        edgeSet.add(new Edge("A", "E"));

        mapDirected = new MapGraph(vertexSet, edgeSet, true, false);
        mapUndirected = new MapGraph(vertexSet, edgeSet, false, false);
    }

    @Test
    void testKhansReturnSize() {
        List<String> result = GraphAlgorithms.kahnsTopological(mapDirected);
        assertEquals(mapDirected.numVertices(), result.size(),
                "Kahns result should include all vertices if no cycles exist.");
    }

    @Test
    void testCyclicException() {
        assertThrows(IllegalStateException.class, () ->
                GraphAlgorithms.kahnsTopological(mapUndirected),
                "Undirected graphs create cycles and should throw exception.");
    }

    @Test
    void testOneCyclicException() {
        // Add edge that will produce a cycle
        edgeSet.add(new Edge("B", "A"));
        MapGraph mapDirectedCyclic = new MapGraph(vertexSet, edgeSet, true, false);
        assertThrows(IllegalStateException.class, () ->
                GraphAlgorithms.kahnsTopological(mapDirectedCyclic),
                "Kahns topological sort method will not work on cyclic graphs.");
    }

    @Test
    void testKhansReturnSort() {
        List<String> result = GraphAlgorithms.kahnsTopological(mapDirected);
        List<String> sortedVertices = new ArrayList<>(mapDirected.getVertices());
        Collections.sort(sortedVertices);
        assertEquals(sortedVertices, result,
                "Edges in graph were purposely written so vertices would appear in alphabetical order if method is correct.");
    }

    @Test
    void testKhansDisconnectedVertex() {
        vertexSet.add("F");
        MapGraph mapDisconnectedVertex = new MapGraph(vertexSet, edgeSet, true, false);
        List<String> result = GraphAlgorithms.kahnsTopological(mapDisconnectedVertex);
        assertEquals(mapDisconnectedVertex.numVertices(), result.size(),
                "Kahns topological sort should include all vertices - including those that are disconnected.");
    }
}