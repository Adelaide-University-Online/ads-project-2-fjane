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
}