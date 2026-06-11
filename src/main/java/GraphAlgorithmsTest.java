import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class GraphAlgorithmsTest {

    protected MapGraph mapDirected;

    @BeforeEach
    void setUp() {
        mapDirected = new MapGraph(true);

        mapDirected.addVertex(0, "A");
        mapDirected.addVertex(1, "B");
        mapDirected.addVertex(2, "C");
        mapDirected.addVertex(3, "D");
        mapDirected.addVertex(4, "E");

        mapDirected.addEdge(0,1);
        mapDirected.addEdge(1,2);
        mapDirected.addEdge(2,3);
        mapDirected.addEdge(3,4);
        mapDirected.addEdge(0,3);
        mapDirected.addEdge(0,4);

    }

    @Test
    void testKhansReturnSize() {
        List<Integer> result = GraphAlgorithms.kahnsBFS(mapDirected);
        assertEquals(mapDirected.getNumVertices(), result.size(),
                "Kahns result should include all vertices if no cycles exist.");
    }

    @Test
    void testCyclicException() {
        // Add edge that will produce a cycle
        mapDirected.addEdge(1,0);
        assertThrows(IllegalStateException.class, () ->
                        GraphAlgorithms.kahnsBFS(mapDirected),
                "Kahns topological sort method will not work on cyclic graphs.");
    }

    @Test
    void testKhansReturnSort() {
        List<Integer> result = GraphAlgorithms.kahnsBFS(mapDirected);
        List<Integer> sortedVertices = new ArrayList<>(mapDirected.getVertices().keySet());
        Collections.sort(sortedVertices);
        assertEquals(sortedVertices, result,
                "Edges in graph were purposely written so vertices would appear in alphabetical order if method is correct.");
    }

    @Test
    void testKhansDisconnectedVertex() {
        mapDirected.addVertex(5, "F");
        List<Integer> result = GraphAlgorithms.kahnsBFS(mapDirected);
        assertEquals(mapDirected.getNumVertices(), result.size(),
                "Kahns topological sort should include all vertices - including those that are disconnected.");
    }
}