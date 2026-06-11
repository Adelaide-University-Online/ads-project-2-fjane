package optitime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    protected Edge edge1;
    protected Edge edge1Copy;
    protected Edge edgeDiffSource;
    protected Edge edgeDiffDest;
    protected Edge edge2;

    @BeforeEach
    void setUp() {
        edge1 = new Edge(0, 1, 2.0);
        edge1Copy = new Edge(0, 1, 2.0);
        edgeDiffSource = new Edge(2, 1, 2.0);
        edgeDiffDest = new Edge(0, 2, 2.0);
        edge2 = new Edge(0, 1);
    }

    // Accessor method tests

    @Test
    void testGetSource() {
        assertEquals(0, edge1.getSource());
    }

    @Test
    void testGetDestination() {
        assertEquals(1, edge1.getDestination());
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