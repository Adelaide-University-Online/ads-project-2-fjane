package optitime;

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

    // Kahn's BFS tests

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

    // Longest Path test

    @Test
    void longestPathValidation() {
        mapDirected.addVertex(5, "F");
        List<Integer> topo = GraphAlgorithms.kahnsBFS(mapDirected);
        Map<Integer, Integer> longest = GraphAlgorithms.longestPath(mapDirected, topo);

        assertEquals(0, longest.get(0)); // A
        assertEquals(1, longest.get(1)); // B
        assertEquals(2, longest.get(2)); // C
        assertEquals(3, longest.get(3)); // D
        assertEquals(4, longest.get(4)); // E
        assertEquals(0, longest.get(0)); // F Disconnected vertex
    }

    // Greedy First Fit test

    @Test
    void greedyBinResultSize() {
        mapDirected.addVertex(5, "F"); // Disconnected vertex

        List<Integer> topo = GraphAlgorithms.kahnsBFS(mapDirected);
        Map<Integer, Integer> longest = GraphAlgorithms.longestPath(mapDirected, topo);
        Map<Integer, List<Integer>> result = GraphAlgorithms.greedyBinPack(topo, mapDirected, longest, 2);

        int totalVertices = 0;

        for (List<Integer> bin : result.values()) {
            totalVertices += bin.size();
        }
        assertEquals(6, totalVertices, "Greedy bin pack should return all vertices.");
    }
}