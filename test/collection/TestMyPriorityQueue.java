package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.solution.MyPriorityQueue;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyPriorityQueue {

    @Test
    void GivenQueue_WhenEnquingElements_ConfirmElementsAdded() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(3, queue.size());
        assertEquals(1, queue.peek());
    }

    @Test
    void GivenQueue_WhenDequingElements_ConfirmElementRemoved() throws EmptyCollectionException {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals(2, queue.peek());
    }

    @Test
    void GivenQueue_WhenPeekingElement_ReturnsTopElement() throws EmptyCollectionException {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.peek());
        assertEquals(3, queue.size());
    }

    @Test
    void GivenQueue_WhenCheckingIsEmpty_ConfirmFalse() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    void GivenQueue_WhenCheckingSize_ReturnsSize() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        assertEquals(0, queue.size());
        queue.enqueue(1);
        assertEquals(1, queue.size());
    }

    @Test
    void GivenQueue_WhenCheckingIfContains_ConfirmsElementExists() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        queue.enqueue(1);
        queue.enqueue(2);
        assertTrue(queue.contains(1));
        assertFalse(queue.contains(3));
    }

    @Test
    void GivenEmptyQueue_WhenDequingElement_ThrowsException() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        assertThrows(EmptyCollectionException.class, queue::dequeue);
    }

    @Test
    void GivenEmptyQueue_WhenPeekingElement_ThrowsException() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        assertThrows(EmptyCollectionException.class, queue::peek);
    }

    @Test
    void GivenQueue_WhenCallingGraphViz_ConfirmCorrectGraphRepresentation() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);

        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);

        String graph = queue.graphViz("PriorityQueueGraph");

        String expectedGraph =
                "digraph PriorityQueueGraph {\n" +
                        "    1;\n" +
                        "    1 -> 2;\n" +
                        "    2;\n" +
                        "    2 -> 3;\n" +
                        "    3;\n" +
                        "}";

        assertEquals(expectedGraph, graph);
    }




}