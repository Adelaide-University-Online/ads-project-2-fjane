package optitime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {

    protected Vertex vertex1;
    protected Vertex vertexCopy;
    protected Vertex vertexDiffID;

    @BeforeEach
    void setUp() {
        vertex1 = new Vertex(0, "Vertex1");
        vertexCopy = new Vertex(0, "Vertex1");
        vertexDiffID = new Vertex(1, "Vertex1");
    }

    // Accessor tests
    @Test
    void testGetID() {
        assertEquals(0, vertex1.getID());
    }

    @Test
    void testGetName() {
        assertEquals("Vertex1", vertex1.getName());
    }

    // Equality tests

    @Test
    void testCopyEquals() {
        assertEquals(vertex1, vertexCopy,
                "Vertices with the same ID and name should be considered equal.");
    }

    @Test
    void testDiffID() {
        assertNotEquals(vertex1, vertexDiffID,
                "Vertices with different IDs and the same name should not be considered equal.");
    }

    // Hash code tests

    @Test
    void testCopyHashCode() {
        assertEquals(vertex1.hashCode(), vertexCopy.hashCode(),
                "Vertices with the same ID and name should produce the same hash code.");
    }

    @Test
    void testDiffIdHashCode() {
        assertNotEquals(vertex1.hashCode(), vertexDiffID.hashCode(),
                "Vertices with different IDs and the same name should produce different hash codes.");
    }

    @Test
    void testToString() {
        String expected = "Vertex1 (0)";
        assertEquals(expected, vertex1.toString());
    }
}