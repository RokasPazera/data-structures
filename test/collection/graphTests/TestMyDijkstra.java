package collection.graphTests;

import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.solution.MyGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMyDijkstra {

    private MyGraph<String> graph;

    @BeforeEach
    void setup() {
        graph = new MyGraph<>(10);
    }

    @Test
    void givenGraphWithDisconnectedNode_whenRunningDijkstra_confirmNodeNotInResults() throws KeyNotFoundException {
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 10);
        graph.addEdge("C", "D", 3);
        graph.addEdge("E", "F", 7);

        SaxGraph<String> shortestPathsGraph = graph.shortestPathsDijkstra("A");

        assertThrows(KeyNotFoundException.class, () -> shortestPathsGraph.getEdges("E"));
        assertThrows(KeyNotFoundException.class, () -> shortestPathsGraph.getEdges("F"));
    }

    @Test
    void givenGraphWithUnknownStartNode_whenCalculatingShortestPaths_confirmGraphIsEmpty() {
        SaxGraph<String> shortestPathsGraph = graph.shortestPathsDijkstra("X");
        assertTrue(shortestPathsGraph.isEmpty());
    }

    @Test
    public void givenGraphWithoutPaths_whenAddingEdges_confirmDijkstraCalculatesCorrectPaths() {
        MyGraph<String> graph = new MyGraph<>(50);
        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 5);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 8);

        SaxGraph<String> path = graph.shortestPathsDijkstra("A");
        assertEquals(4, path.size());

        SaxGraph.DirectedEdge<String> edge1 = path.getEdges("A").get(0);
        assertEquals("A", edge1.from());
        assertEquals("A", edge1.to());
        assertEquals(0, edge1.weight());

        SaxGraph.DirectedEdge<String> edge2 = path.getEdges("B").get(0);
        assertEquals("B", edge2.from());
        assertEquals("A", edge2.to());
        assertEquals(2, edge2.weight());

        SaxGraph.DirectedEdge<String> edge3 = path.getEdges("D").get(0);
        assertEquals("D", edge3.from());
        assertEquals("B", edge3.to());
        assertEquals(6, edge3.weight());
    }
}
