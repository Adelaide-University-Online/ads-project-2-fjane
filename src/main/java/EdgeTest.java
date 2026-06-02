import org.junit.jupiter.api.BeforeEach;

class EdgeTest {

    protected Edge edge1;
    protected Edge edge1Copy;
    protected Edge edgeDiffWeight;
    protected Edge edgeDiffSource;
    protected Edge edgeDiffDest;
    protected Edge edge2;

    @BeforeEach
    void setUp() {
        edge1 = new Edge("AAAA1234", "BBBB1234", 2.0);
        edge1Copy = new Edge("AAAA1234", "BBBB1234", 2.0);
        edgeDiffWeight = new Edge("AAAA1234", "BBBB1234", 3.0);
        edgeDiffSource = new Edge("aaaa1234", "BBBB1234", 2.0);
        edgeDiffDest = new Edge("AAAA1234", "B1234", 2.0);
        edge2 = new Edge("AAAA1234", "BBBB1234");
    }
}