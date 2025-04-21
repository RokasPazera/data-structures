package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.solution.MyStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyStack {

    @Test
    void givenEmptyList_WhenPushingElements_ConfirmElementIsAdded(){
        MyStack<Integer> list = new MyStack<>();
        list.push(1);
        assertEquals(1, list.peek());
    }

    @Test
    void givenList_WhenPushingMultipleElements_ConfirmOrder() {
        MyStack<Integer> list = new MyStack<>();
        list.push(1);
        list.push(2);
        list.push(3);
        System.out.println(list.graphViz("TestPush"));
        assertEquals(3, list.pop());
        assertEquals(2, list.pop());
        assertEquals(1, list.pop());
    }

    @Test
    void givenList_WhenPoppingElements_ConfirmElementIsRemoved(){
        MyStack<Integer> list = new MyStack<>();
        assertThrows(EmptyCollectionException.class, list::pop);
        assertThrows(EmptyCollectionException.class, list::peek);


        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        assertEquals(5, list.peek());

        list.pop();
        assertEquals(4, list.peek());
    }

    @Test
    void givenList_WhenPoppingAllElements_ConfirmStackIsEmpty() throws EmptyCollectionException {
        MyStack<Integer> list = new MyStack<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.pop();
        list.pop();
        list.pop();
        assertTrue(list.isEmpty());
        assertThrows(EmptyCollectionException.class, list::pop);
    }

    @Test
    void givenList_WhenPeeking_ConfirmTopElementWithoutRemoving() throws EmptyCollectionException {
        MyStack<Integer> list = new MyStack<>();
        list.push(1);
        list.push(2);
        list.push(3);
        assertEquals(3, list.peek());
        assertEquals(3, list.size());
    }

    @Test
    void givenEmptyList_WhenCheckingIfListIsEmpty_ConfirmListIsEmpty(){
        MyStack<Integer> list = new MyStack<>();
        assertTrue(list.isEmpty());

        list.push(1);

        assertFalse(list.isEmpty());
    }

    @Test
    void givenList_WhenExceedingInitialCapacity_ConfirmSizeIncreases() {
        MyStack<Integer> list = new MyStack<>();
        for (int i = 0; i < 15; i++) {
            list.push(i);
        }
        assertEquals(15, list.size());
        assertEquals(14, list.peek());
    }



}
