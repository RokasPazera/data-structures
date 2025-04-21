package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.solution.MyHeap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyHeap {
    @Test
    void givenNewHeap_WhenCheckingSize_ConfirmEmpty(){
        MyHeap<Integer> minHeap = new MyHeap<>();
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    void givenEmptyHeap_WhenTryingEnqueuingDequeuing_ConfirmThrowsException(){
        MyHeap<Integer> minHeap = new MyHeap<>();
        assertThrows(EmptyCollectionException.class, minHeap::dequeue);
        assertThrows(EmptyCollectionException.class, minHeap::peek);
    }

    @Test
    void givenNewHeap_WhenAddingTheExampleData_ConfirmSize(){
        MyHeap<Integer> minHeap = new MyHeap<>();

        minHeap.enqueue(21);
        minHeap.enqueue(24);
        minHeap.enqueue(31);
        minHeap.enqueue(65);
        minHeap.enqueue(26);
        minHeap.enqueue(32);
        minHeap.enqueue(16);
        minHeap.enqueue(19);
        minHeap.enqueue(68);
        minHeap.enqueue(13);

        assertEquals(10, minHeap.size());
        assertEquals("minHeap[ 13 16 21 24 19 32 31 65 68 26 ]", minHeap.toString());

        minHeap.enqueue(14);

        assertEquals(11, minHeap.size());
        assertEquals("minHeap[ 13 14 21 24 16 32 31 65 68 26 19 ]", minHeap.toString());
    }

    @Test
    void givenNewExampleHeap_WhenRemoval_ConfirmSizeAndStructure(){
        MyHeap<Integer> minHeap = new MyHeap<>();

        minHeap.enqueue(21);
        minHeap.enqueue(24);
        minHeap.enqueue(31);
        minHeap.enqueue(65);
        minHeap.enqueue(26);
        minHeap.enqueue(32);
        minHeap.enqueue(16);
        minHeap.enqueue(19);
        minHeap.enqueue(68);
        minHeap.enqueue(13);



        assertEquals(13, minHeap.dequeue());
        assertEquals(16, minHeap.dequeue());
        assertEquals(19, minHeap.dequeue());
        assertEquals(21, minHeap.dequeue());
        assertEquals(24, minHeap.dequeue());

        assertEquals(5, minHeap.size());

        assertEquals(26, minHeap.dequeue());
        assertEquals(31, minHeap.dequeue());
        assertEquals(32, minHeap.dequeue());
        assertEquals(65, minHeap.dequeue());
        assertEquals(68, minHeap.dequeue());


        assertTrue(minHeap.isEmpty());
    }



    @Test
    void givenHeapWithElements_WhenPeeking_ConfirmTopElement() throws EmptyCollectionException {
        MyHeap<Integer> minHeap = new MyHeap<>();

        minHeap.enqueue(21);
        minHeap.enqueue(24);
        minHeap.enqueue(31);
        minHeap.enqueue(65);
        minHeap.enqueue(26);
        minHeap.enqueue(32);
        minHeap.enqueue(16);
        minHeap.enqueue(19);
        minHeap.enqueue(68);
        minHeap.enqueue(13);

        assertEquals(13, minHeap.peek());
        minHeap.dequeue();
        assertEquals(16, minHeap.peek());
    }

    @Test
    void givenHeapWithDescendingOrder_WhenAddingAndRemoving_ConfirmCorrectOrder() {
        MyHeap<Integer> minHeap = new MyHeap<>();

        minHeap.enqueue(50);
        minHeap.enqueue(40);
        minHeap.enqueue(30);
        minHeap.enqueue(20);
        minHeap.enqueue(10);

        assertEquals(10, minHeap.dequeue());
        assertEquals(20, minHeap.dequeue());
        assertEquals(30, minHeap.dequeue());
        assertEquals(40, minHeap.dequeue());
        assertEquals(50, minHeap.dequeue());
        assertTrue(minHeap.isEmpty());
    }

    @Test
    void givenHeapWithChildrenOnlyLeftOrRight_WhenRemoving_ConfirmCorrectStructure() {
        MyHeap<Integer> minHeap = new MyHeap<>();

        minHeap.enqueue(10);
        minHeap.enqueue(20);
        minHeap.enqueue(30);

        minHeap.enqueue(15);

        assertEquals(10, minHeap.dequeue());
        assertEquals(15, minHeap.peek());
    }

    @Test
    void givenHeap_WhenAddingElements_ThenChecksGraphViz(){
        MyHeap<Integer> minHeap = new MyHeap<>();

        minHeap.enqueue(1);
        minHeap.enqueue(2);
        minHeap.enqueue(3);
        minHeap.enqueue(4);
        minHeap.enqueue(5);

        String expectedGraph = """
                digraph TestGraph {
                    1;
                    1 -> 2;
                    1 -> 3;
                    2;
                    2 -> 4;
                    2 -> 5;
                    3;
                    4;
                    5;
                }
                """;

        assertEquals(expectedGraph.trim() , minHeap.graphViz("TestGraph").trim());
    }



}
