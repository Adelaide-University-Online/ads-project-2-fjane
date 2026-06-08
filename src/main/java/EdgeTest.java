import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    protected Vertex vertex1;
    protected Vertex vertex2;
    protected Vertex vertex3;

    protected Edge edge1;
    protected Edge edge1Copy;
    protected Edge edgeDiffSource;
    protected Edge edgeDiffDest;
    protected Edge edge2;

    @BeforeEach
    void setUp() {
        vertex1 = new Vertex(0, "AAAA1234");
        vertex2 = new Vertex(0, "BBBB4567");
        vertex3 = new Vertex(1, "CCCC8910");

        edge1 = new Edge(vertex1, vertex2, 2.0);
        edge1Copy = new Edge(vertex1, vertex2, 2.0);
        edgeDiffSource = new Edge(vertex3, vertex2, 2.0);
        edgeDiffDest = new Edge(vertex1, vertex3, 2.0);
        edge2 = new Edge(vertex1, vertex2);
    }

    // Accessor method tests

    @Test
    void testGetSource() {
        assertEquals(vertex1, edge1.getSource());
    }

    @Test
    void testGetDestination() {
        assertEquals(vertex2, edge1.getDestination());
    }

    @Test
    void testGetWeight() {
        assertEquals(2.0, edge1.getWeight());
    }

    // Test default weight and weight modifier methods

    @Test
    void testNoWeightConstructor() {
        assertEquals(1.0, edge2.getWeight(),
                "Default weight when an edge is constructed without a weight argument is 1.0.");
    }

    @Test
    void testSetWeight() {
        edge1.setWeight(21.1);
        assertEquals(21.1, edge1.getWeight(), "Weight should change to double passed into setWeight().");
    }

    // Equality tests

    @Test
    void testSameAttributesEqual() {
        assertEquals(edge1, edge1Copy,
                "Edges with the same source and destination vertices should be considered equal.");
    }

    @Test
    void testDiffSourceNotEqual() {
        assertNotEquals(edgeDiffSource, edge1,
                "Edges with different source vertices but the same destination vertex are not equal.");
    }

    @Test
    void testDiffDestinationNotEqual() {
        assertNotEquals(edgeDiffDest, edge1,
                "Edges with different destination vertices but the same source vertex are not equal.");
    }

    // Hash code tests

    @Test
    void testSameAttributesHashCode() {
        assertEquals(edge1.hashCode(), edge1Copy.hashCode(),
                "Edges with the same source and destination vertices should produce the same hash code.");
    }

    @Test
    void testDiffSourceHashCode() {
        assertNotEquals(edgeDiffSource.hashCode(), edge1.hashCode(),
                "Edges with different source vertices but the same destination vertex should produce different hash codes.");
    }

    @Test
    void testDiffDestinationHashCode() {
        assertNotEquals(edgeDiffDest.hashCode(), edge1.hashCode(),
                "Edges with different destination vertices but the same source vertex should produce different hash codes.");
    }
}