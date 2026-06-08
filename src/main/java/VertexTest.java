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

    @Test
    void testDiffNameCase() {
        Vertex vertexNameCase = new Vertex(0, "VERTEX1");
        assertNotEquals(vertex1, vertexNameCase,
                "Vertex names are case-sensitive.");
    }

    @Test
    void testDiffNameSpace() {
        Vertex vertexNameSpace = new Vertex(0, "Vertex 1");
        assertNotEquals(vertex1, vertexNameSpace,
                "Vertex names are space sensitive.");
    }
}