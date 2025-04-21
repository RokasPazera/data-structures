package collection.graphTests;

import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.solution.MyGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyAStar {

    private MyGraph<String> graph;

    @BeforeEach
    void setup() {
        graph = new MyGraph<>(10);
    }

    @Test
    void givenGraphWithValidPath_WhenUsingAStar_ConfirmShortestPathFound() {
        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 5);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "D", 3);

        SaxGraph.Estimator<String> estimator = (node1, node2) -> {
            if (node1.equals("A") && node2.equals("D")) return 3;
            if (node1.equals("B") && node2.equals("D")) return 1;
            return 0;
        };

        SaxList<SaxGraph.DirectedEdge<String>> path = graph.shortestPathAStar("A", "D", estimator);

        assertEquals(3, path.size());
        assertEquals("A", path.get(0).from());
        assertEquals("A", path.get(0).to());
        assertEquals("D", path.get(2).to());
        assertEquals(1.0, path.get(2).weight());
    }

    @Test
    void givenGraphWithNoPath_WhenUsingAStar_ConfirmNoPathReturned() {
        graph.addEdge("A", "B", 2);
        graph.addEdge("B", "C", 3);
        graph.addEdge("D", "E", 1);

        SaxGraph.Estimator<String> estimator = (node1, node2) -> 1;

        SaxList<SaxGraph.DirectedEdge<String>> path = graph.shortestPathAStar("A", "E", estimator);

        assertTrue(path.isEmpty());
    }

    @Test
    void givenGraphWithEqualWeightEdges_WhenUsingAStar_ConfirmShortestPathWithMinimumEdges() {
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "D", 1);

        SaxGraph.Estimator<String> estimator = (node1, node2) -> 0;

        SaxList<SaxGraph.DirectedEdge<String>> path = graph.shortestPathAStar("A", "D", estimator);

        assertEquals(3, path.size());
        assertEquals("A", path.get(0).from());
        assertEquals("A", path.get(0).to());
        assertEquals("A", path.get(1).from());
        assertEquals("C", path.get(1).to());
        assertEquals("C", path.get(2).from());
        assertEquals("D", path.get(2).to());
    }

    @Test
    void givenGraphWithComplexWeights_WhenUsingAStar_ConfirmCorrectPath() {
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 1);
        graph.addEdge("C", "D", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("D", "E", 1);

        SaxGraph.Estimator<String> estimator = (node1, node2) -> {
            if (node1.equals("A") && node2.equals("E")) return 2;
            if (node1.equals("C") && node2.equals("E")) return 1;
            return 0;
        };

        SaxList<SaxGraph.DirectedEdge<String>> path = graph.shortestPathAStar("A", "E", estimator);

        assertEquals(4, path.size());
        assertEquals("A", path.get(0).from());
        assertEquals("A", path.get(0).to());
        assertEquals("A", path.get(1).from());
        assertEquals("C", path.get(1).to());
        assertEquals("C", path.get(2).from());
        assertEquals("D", path.get(2).to());
    }

    @Test
    void givenGraphWithLoop_WhenUsingAStar_ConfirmLoopIsNotPartOfPath() {
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "A", 1);
        graph.addEdge("B", "D", 1);

        SaxGraph.Estimator<String> estimator = (node1, node2) -> 1;

        SaxList<SaxGraph.DirectedEdge<String>> path = graph.shortestPathAStar("A", "D", estimator);

        assertEquals(3, path.size());
        assertEquals("A", path.get(0).from());
        assertEquals("A", path.get(1).from());
        assertEquals("D", path.get(2).to());
    }

    @Test
    void givenNullEdge_WhenGetNodeCalled_ReturnNull() {
        MyGraph<String>.AStarSearchNode node = new MyGraph<String>(10).new AStarSearchNode(null, 0, 0, null);
        String result = node.getCurrentNode();
        assertNull(result);
    }
}
