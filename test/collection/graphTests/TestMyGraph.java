package collection.graphTests;

import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.solution.MyGraph;
import nl.saxion.cds.solution.MyHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class TestMyGraph {
    private MyGraph<String> graph;

    @BeforeEach
    void setup() {
        graph = new MyGraph<>(10);
    }

    @Test
    void givenGraph_whenAddingEdge_confirmEdgeIsStoredCorrectly() throws KeyNotFoundException {
        graph.addEdge("A", "B", 5);
        SaxList<SaxGraph.DirectedEdge<String>> edges = graph.getEdges("A");
        assertEquals(1, edges.size());
        assertEquals("B", edges.get(0).to());
        assertEquals(5, edges.get(0).weight());
    }

    @Test
    void givenGraph_whenAddingBidirectionalEdge_confirmBothEdgesExist() {
        graph.addEdgeBidirectional("A", "B", 2);
        assertEquals(1, graph.getEdges("A").size());
        assertEquals("B", graph.getEdges("A").get(0).to());
        assertEquals(2, graph.getEdges("A").get(0).weight());

        assertEquals(1, graph.getEdges("B").size());
        assertEquals("A", graph.getEdges("B").get(0).to());
        assertEquals(2, graph.getEdges("B").get(0).weight());
    }

    @Test
    void givenGraph_whenRetrievingEdgesForUnknownNode_confirmExceptionThrown() {
        assertThrows(KeyNotFoundException.class, () -> {
            graph.getEdges("X");
        });
    }

    @Test
    void givenGraphWithEdges_whenCalculatingTotalWeight_confirmCorrectSum() throws KeyNotFoundException {
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 10);
        assertEquals(15, graph.getTotalWeight());
    }


    @Test
    void givenGraphWithVariousEdges_whenMakingMST_confirmCorrectStartNodeUsed() throws KeyNotFoundException {
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 3);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 5);

        SaxGraph<String> mst = graph.minimumCostSpanningTree();

        assertEquals(4, mst.size());
        assertEquals(7, mst.getTotalWeight());
    }

    @Test
    void givenGraphWithDisconnectedNode_whenMakingMST_confirmNodeNotIncluded() throws KeyNotFoundException {
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("D", "E", 3);

        SaxGraph<String> mst = graph.minimumCostSpanningTree();

        assertEquals(3, mst.size());
        assertThrows(KeyNotFoundException.class, () -> mst.getEdges("D"));
        assertThrows(KeyNotFoundException.class, () -> mst.getEdges("E"));
    }

    @Test
    void givenGraphWithSameWeightEdges_whenMakingMST_confirmMinimumWeight() throws KeyNotFoundException {
        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 2);
        graph.addEdge("C", "D", 2);

        SaxGraph<String> mst = graph.minimumCostSpanningTree();

        assertEquals(4, mst.size());
        assertEquals(14, mst.getTotalWeight());
    }

    @Test
    void givenGraphWithDifferentWeights_WhenCalculatingMST_ConfirmMinimumEdgesSelected() throws KeyNotFoundException {
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "D", 4);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 3);
        graph.addEdge("C", "D", 5);

        SaxGraph<String> mst = graph.minimumCostSpanningTree();
        assertEquals(4, mst.size());
        assertEquals(6, mst.getTotalWeight());

        assertTrue(mst.getEdges("A").get(0).weight() <= 4);
        assertTrue(mst.getEdges("B").get(0).weight() <= 3);
    }

    @Test
    void givenEmptyGraph_whenNextCalled_thenThrowIllegalStateException() {
        MyGraph<String> graph = new MyGraph<>(10);
        assertThrows(IllegalStateException.class, () -> {
            Iterator<String> iterator = graph.iterator();
            iterator.next();
        });
    }

    @Test
    void givenGraphWithNodeHavingNullEdge_WhenReconstructingPath_ConfirmEdgeHandling() {
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "D", 3);

        MyGraph<String>.AStarSearchNode nodeWithNullEdge = graph.new AStarSearchNode(null, 0, 0, null);

        MyHashMap<String, MyGraph<String>.AStarSearchNode> closedList = new MyHashMap<>();
        closedList.add("C", nodeWithNullEdge);

        SaxList<SaxGraph.DirectedEdge<String>> path = graph.reconstructPath(closedList, nodeWithNullEdge);

        assertTrue(path.isEmpty());
    }

    @Test
    void givenEmptyGraph_WhenCalculatingMST_ConfirmEmptyMST() {
        SaxGraph<String> mst = graph.minimumCostSpanningTree();
        assertTrue(mst.isEmpty());
    }

    @Test
    void givenGraph_whenGeneratingGraphVizOutput_confirmDotFormatCorrect() throws KeyNotFoundException {
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 5);
        String graphVizOutput = graph.graphViz("TestGraph");

        assertTrue(graphVizOutput.contains("digraph TestGraph {"));
        assertTrue(graphVizOutput.contains("\"A\" -> \"B\" [label=\"10.0\"]"));
        assertTrue(graphVizOutput.contains("\"B\" -> \"C\" [label=\"5.0\"]"));
    }


}