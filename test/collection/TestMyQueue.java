package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.solution.MyQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyQueue{
    @Test
    void givenEmptyList_WhenEnqueuingElements_ConfirmElementIsAdded(){
        MyQueue<Integer> list = new MyQueue<>();

        list.enqueue(1);
        list.enqueue(2);
        list.enqueue(3);

        assertEquals(1, list.peek());
    }

    @Test
    void givenEmptyQueue_WhenDequeuingOrPeeking_ThrowException() {
        MyQueue<Integer> queue = new MyQueue<>();

        assertThrows(EmptyCollectionException.class, queue::dequeue);
        assertThrows(EmptyCollectionException.class, queue::peek);
    }

    @Test
    void givenQueueWithElements_WhenGeneratingGraphViz_ConfirmOutputIsCorrect() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        String graphVizOutput = queue.graphViz("TestGraph");

        String expectedOutput = """
                digraph TestGraph {
                    1;
                    1 -> 2;
                    2;
                    2 -> 3;
                    3;
                }
                """;

        assertEquals(expectedOutput.strip(), graphVizOutput.strip());
    }

    @Test
    void givenDummyList_WhenDequeuingElements_ConfirmElementIsRemoved(){
        MyQueue<Integer> list = new MyQueue<>();

        list.enqueue(1);
        list.enqueue(2);
        list.enqueue(3);

        list.dequeue();

        assertEquals(2, list.peek());
    }

    @Test
    void givenEmptyList_WhenCheckingIfListIsEmpty_ConfirmListIsEmpty(){
        MyQueue<Integer> list = new MyQueue<>();

        assertTrue(list.isEmpty());
    }

    @Test
    void givenDummyList_WhenCheckingSize_ReturnsListSize(){
        MyQueue<Integer> list = new MyQueue<>();

        list.enqueue(1);
        list.enqueue(2);
        list.enqueue(3);

        assertEquals(3, list.size());
    }
}
