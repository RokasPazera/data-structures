package collection;

import nl.saxion.cds.collection.DuplicateKeyException;
import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.solution.MyAVLTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyAVLTree {
    @Test
    void givenEmptyTree_WhenCheckingIfListIsEmpty_ConfirmsItsEmpty() {
        MyAVLTree<String, String> tree = new MyAVLTree<>();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void givenTree_WhenAddingElements_ThenChecksSizeAndGraphViz() {
        MyAVLTree<String, String> tree = new MyAVLTree<>();

        tree.add("D", "first");
        tree.add("B", "second");
        tree.add("F", "third");
        tree.add("A", "fourth");
        tree.add("C", "fifth");
        tree.add("E", "sixth");
        tree.add("G", "seventh");

        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());

        String expectedGraph = """
                digraph tree{
                D[label="D\\nfirst"]
                D -> B
                B[label="B\\nsecond"]
                B -> A
                A[label="A\\nfourth"]
                B -> C
                C[label="C\\nfifth"]
                D -> F
                F[label="F\\nthird"]
                F -> E
                E[label="E\\nsixth"]
                F -> G
                G[label="G\\nseventh"]
                }
                """;
        assertEquals(expectedGraph.trim(), tree.graphViz("tree").trim());
    }

    @Test
    void givenTree_WhenAddingDuplicateKey_ThrowsException() {
        MyAVLTree<String, String> tree = new MyAVLTree<>();
        tree.add("A", "first");
        assertThrows(DuplicateKeyException.class, () -> tree.add("A", "duplicate"));
    }

    @Test
    void givenTree_WhenCheckingContainsAndGet_ConfirmsCorrectBehavior() {
        MyAVLTree<String, String> tree = new MyAVLTree<>();

        tree.add("D", "first");
        tree.add("B", "second");
        tree.add("F", "third");
        tree.add("A", "fourth");
        tree.add("C", "fifth");

        assertTrue(tree.contains("D"));
        assertTrue(tree.contains("B"));
        assertTrue(tree.contains("F"));
        assertFalse(tree.contains("X"));

        assertEquals("first", tree.get("D"));
        assertEquals("second", tree.get("B"));
        assertEquals("third", tree.get("F"));

        assertNull(tree.get("X"));
    }

    @Test
    void givenTree_WhenRemovingElements_ConfirmsCorrectRemovalAndBalancing() {
        MyAVLTree<String, String> tree = new MyAVLTree<>();

        tree.add("D", "first");
        tree.add("B", "second");
        tree.add("F", "third");
        tree.add("A", "fourth");
        tree.add("C", "fifth");
        tree.add("E", "sixth");
        tree.add("G", "seventh");

        assertTrue(tree.contains("D"));
        tree.remove("D");
        assertFalse(tree.contains("D"));

        assertTrue(tree.contains("A"));
        tree.remove("A");
        assertFalse(tree.contains("A"));

        assertTrue(tree.contains("B"));
        tree.remove("B");
        assertFalse(tree.contains("B"));

        assertThrows(KeyNotFoundException.class, () -> tree.remove("X")); // Non-existing key

        assertEquals(4, tree.size());
    }

    @Test
    void givenTree_WhenCheckingKeys_ReturnsAllKeysInOrder() {
        MyAVLTree<String, String> tree = new MyAVLTree<>();
        tree.add("D", "first");
        tree.add("B", "second");
        tree.add("F", "third");
        tree.add("A", "fourth");
        tree.add("C", "fifth");

        assertEquals("[ A B C D F ]", tree.getKeys().toString());
    }

    @Test
    void givenTree_WhenBalancing_ConfirmsTreeRemainsBalanced() {
        MyAVLTree<Integer, String> tree = new MyAVLTree<>();

        tree.add(10, "Root");
        tree.add(20, "Right");
        tree.add(30, "Right-Right");

        assertEquals("[ 10 20 30 ]", tree.getKeys().toString());
        assertTrue(tree.contains(20));
        assertEquals(3, tree.size());

        tree.remove(20);
        assertEquals("[ 10 30 ]", tree.getKeys().toString());
    }


    @Test
    void givenSingleNodeTree_WhenCallingMethods_ThenBehaveCorrectly() {
        MyAVLTree<String, String> tree = new MyAVLTree<>();
        tree.add("A", "value");

        assertEquals(1, tree.size());
        assertTrue(tree.contains("A"));
        assertEquals("value", tree.get("A"));

        tree.remove("A");
        assertTrue(tree.isEmpty());
    }


    @Test
    void givenUnbalancedTree_WhenBalancing_ThenTreeIsBalanced() {
        MyAVLTree<Integer, String> tree = new MyAVLTree<>();

        tree.add(30, "A");
        tree.add(20, "B");
        tree.add(10, "C");
        assertEquals("[ 10 20 30 ]", tree.getKeys().toString());

        tree.clear();
        tree.add(10, "A");
        tree.add(20, "B");
        tree.add(30, "C");
        assertEquals("[ 10 20 30 ]", tree.getKeys().toString());

        tree.clear();
        tree.add(30, "A");
        tree.add(10, "B");
        tree.add(20, "C");
        assertEquals("[ 10 20 30 ]", tree.getKeys().toString());

        tree.clear();
        tree.add(10, "A");
        tree.add(30, "B");
        tree.add(20, "C");
        assertEquals("[ 10 20 30 ]", tree.getKeys().toString());
    }


    @Test
    void givenTree_WhenRemovingRootWithTwoChildren_ThenTreeIsBalanced() {
        MyAVLTree<Integer, String> tree = new MyAVLTree<>();
        tree.add(20, "Root");
        tree.add(10, "Left");
        tree.add(30, "Right");

        tree.remove(20);
        assertEquals("[ 10 30 ]", tree.getKeys().toString());
    }



}
