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

}