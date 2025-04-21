package collection;

import nl.saxion.cds.solution.MyHashSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestMyHashSet {

    @Test
    void whenGivenEmptySet_AfterAddingElements_ConfirmsElementsAdded() {
        MyHashSet<String> set = new MyHashSet<>();

        set.add("apple");
        set.add("banana");

        assertTrue(set.contains("apple"));
        assertTrue(set.contains("banana"));

        set.add("apple");
        assertEquals(2, set.size());
    }

    @Test
    void whenGivenDummySet_AfterRemovingElements_ConfirmsElementsRemoved() {
        MyHashSet<String> set = new MyHashSet<>();
        set.add("apple");
        set.add("banana");

        assertTrue(set.remove("apple"));
        assertFalse(set.contains("apple"));
        assertEquals(1, set.size());

        assertFalse(set.remove("grape"));
        assertEquals(1, set.size());
    }

    @Test
    void whenGivenDummySet_AfterCheckingElementsInSet_ConfirmsElements() {
        MyHashSet<String> set = new MyHashSet<>();
        set.add("apple");

        assertTrue(set.contains("apple"));

        assertFalse(set.contains("banana"));
    }

    @Test
    void whenGivenDummySet_AfterCheckingSetSize_ConfirmsElementSize() {
        MyHashSet<String> set = new MyHashSet<>();
        assertEquals(0, set.size());

        set.add("apple");
        set.add("banana");
        assertEquals(2, set.size());

        set.remove("apple");
        assertEquals(1, set.size());
    }

    @Test
    void whenGivenEmptySet_AfterCheckingElementsInSet_ConfirmsEmptySet() {
        MyHashSet<String> set = new MyHashSet<>();
        assertTrue(set.isEmpty());

        set.add("apple");
        assertFalse(set.isEmpty());

        set.remove("apple");
        assertTrue(set.isEmpty());
    }

    @Test
    void whenGivenDummySet_AfterClearingElements_ConfirmsEmptySet() {
        MyHashSet<String> set = new MyHashSet<>();
        set.add("apple");
        set.add("banana");

        set.clear();
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
        assertFalse(set.contains("apple"));
    }

}
