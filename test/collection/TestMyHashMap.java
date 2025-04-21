package collection;

import nl.saxion.cds.collection.DuplicateKeyException;
import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.solution.MyArrayList;
import nl.saxion.cds.solution.MyHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyHashMap {

    private MyHashMap<String, Integer> map;
    @BeforeEach
    public void setUp() {
        map = new MyHashMap<>();
    }

    @Test
    public void GivenEmptyHashMap_WhenInitializingHashMap_ThenConfirmsEmpty() {
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void GivenHashMapWithOneEntry_WhenAddingEntry_ThenCanRetrieveValue() throws DuplicateKeyException {
        map.add("key1", 10);

        assertEquals(10, map.get("key1"));
    }


    @Test
    public void GivenDuplicateKey_WhenAddingEntry_ThenThrowsDuplicateKeyException(){
        assertThrows(DuplicateKeyException.class, () ->{
            map.add("key1", 10);
            map.add("key1", 20);
        });
    }

    @Test
    public void GivenHashMapWithEntries_WhenRemovingKey_ThenKeyShouldBeRemoved() {
        map.add("key1", 10);
        map.add("key2", 20);

        Integer removedValue = map.remove("key1");

        assertEquals(10, removedValue);
        assertFalse(map.contains("key1"));
    }

    @Test
    public void GivenHashMapWithEntries_WhenRemovingNonexistentKey_ThenThrowsKeyNotFoundException() {
        assertThrows(KeyNotFoundException.class, () -> map.remove("nonExistentKey"));
    }

    @Test
    public void GivenHashMapWithEntries_WhenGettingKeys_ThenReturnsAllKeys() throws DuplicateKeyException {
        map.add("key1", 10);
        map.add("key2", 20);
        map.add("key3", 30);

        SaxList<String> keys = map.getKeys();

        assertTrue(keys.contains("key1"));
        assertTrue(keys.contains("key2"));
        assertTrue(keys.contains("key3"));
        assertEquals(3, keys.size());
    }



    @Test
    public void GivenHashMap_WhenGettingKeysAfterRemoval_ThenReturnsRemainingKeys() throws DuplicateKeyException, KeyNotFoundException {
        map.add("key1", 10);
        map.add("key2", 20);
        map.add("key3", 30);

        map.remove("key2");

        SaxList<String> keys = map.getKeys();
        assertFalse(keys.contains("key2"));
        assertEquals(2, keys.size());
    }

    @Test
    public void GivenHashMap_WhenCallingGraphViz_ThenGeneratesGraphRepresentation() throws DuplicateKeyException {
        map.add("key1", 10);
        map.add("key2", 20);


        String expectedGraph = """
                digraph TestGraph {
                bucket_0 [label="NULL"];
                bucket_0 -> bucket_1;
                bucket_1 [label="NULL"];
                bucket_1 -> bucket_2;
                bucket_2 [label="2:key1,10"];
                bucket_2 -> bucket_3;
                bucket_3 [label="3:key2,20"];
                bucket_3 -> bucket_4;
                bucket_4 [label="NULL"];
                bucket_4 -> bucket_5;
                bucket_5 [label="NULL"];
                bucket_5 -> bucket_6;
                bucket_6 [label="NULL"];
                bucket_6 -> bucket_7;
                bucket_7 [label="NULL"];
                bucket_7 -> bucket_8;
                bucket_8 [label="NULL"];
                bucket_8 -> bucket_9;
                bucket_9 [label="NULL"];
                bucket_9 -> bucket_10;
                bucket_10 [label="NULL"];
                bucket_10 -> bucket_11;
                bucket_11 [label="NULL"];
                bucket_11 -> bucket_12;
                bucket_12 [label="NULL"];
                bucket_12 -> bucket_13;
                bucket_13 [label="NULL"];
                bucket_13 -> bucket_14;
                bucket_14 [label="NULL"];
                bucket_14 -> bucket_15;
                bucket_15 [label="NULL"];
                }
                """;

        assertEquals(expectedGraph.trim(), map.graphViz("TestGraph").trim());
    }

    @Test
    public void GivenHashMap_WhenAddingAndRemovingMultipleEntries_ThenMaintainsCorrectSize() throws KeyNotFoundException, DuplicateKeyException {
        map.add("key1", 10);
        map.add("key2", 20);
        map.add("key3", 30);

        map.remove("key2");
        map.remove("key1");

        assertEquals(1, map.size());
        assertFalse(map.contains("key1"));
        assertFalse(map.contains("key2"));
        assertTrue(map.contains("key3"));
    }

    @Test
    public void GivenFullHashMap_WhenAddingMoreEntries_ThenTriggersResize() throws DuplicateKeyException {
        for (int i = 0; i < 20; i++) {
            map.add("key" + i, i * 10);
        }

        assertEquals(20, map.size());
    }

    @Test
    public void GivenEmptyHashMap_WhenGettingNonExistentKey_ThenThrowsKeyNotFoundException() {
        assertThrows(KeyNotFoundException.class, () -> map.get("key100"));
    }




    @Test
    public void GivenFullHashMap_WhenAddingMoreEntries_ThenThrowsOutOfMemoryError() {
        map.add("key1", 10);
        map.add("key2", 20);
        map.add("key3", 30);
        map.add("key4", 40);
        map.add("key5", 50);
    }


    @Test
    public void GivenEmptyHashMap_WhenInitializingHashMap_ConfirmsEmpty(){
        MyHashMap<Integer, String> map = new MyHashMap<>();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void GivenHashMap_WhenAddingValues_ConfirmSizeAndRetrieval(){
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.add(62, "one");
        map.add(26, "two");
        map.add(35, "three");
        map.add(72, "four");
        map.add(52, "five");
        map.add(44, "six");
        map.add(16, "seven");
        map.add(81, "eight");

        assertEquals(8, map.size());
        assertFalse(map.isEmpty());
        assertTrue(map.contains(26));
        assertEquals("two", map.get(26));
        assertTrue(map.contains(72));
        assertEquals("four", map.get(72));
        assertTrue(map.contains(62));
        assertEquals("one", map.get(62));
        assertFalse(map.contains(42));
        System.out.println(map.graphViz(" MyHashMap"));

        map.add(9, "nine");
        map.add(10, "ten");

    }

    @Test
    public void GivenHashMapWithoutKey_WhenRemovingKey_ThenThrowsKeyNotFoundException() {
        assertThrows(KeyNotFoundException.class, () -> map.remove("nonExistentKey"));
    }

    @Test
    public void GivenDeletedKey_WhenRemovingKey_ThenThrowsKeyNotFoundException() {
        map.add("key1", 10);
        map.remove("key1");

        assertThrows(KeyNotFoundException.class, () -> map.remove("key1"));
    }

    @Test
    public void GivenHashMap_WhenResizingAfterDeletions_ThenDoesNotIncludeDeletedEntries() throws DuplicateKeyException, KeyNotFoundException {
        map.add("key1", 10);
        map.add("key2", 20);
        map.add("key3", 30);

        map.remove("key2");

        for (int i = 4; i <= 20; i++) {
            map.add("key" + i, i * 10);
        }

        assertFalse(map.contains("key2"));
        assertEquals(19, map.size());
    }

    @Test
    public void GivenHashMap_WhenAddingEntriesBeyondLoadFactor_ThenResizesCorrectly() throws DuplicateKeyException {
        for (int i = 0; i < 20; i++) {
            map.add("key" + i, i * 10);
        }

        assertEquals(20, map.size());
        for (int i = 0; i < 20; i++) {
            assertTrue(map.contains("key" + i));
            assertEquals(i * 10, map.get("key" + i));
        }
    }

    @Test
    public void GivenDeletedKey_WhenReAddingKey_ThenKeyIsReAdded() throws DuplicateKeyException, KeyNotFoundException {
        map.add("key1", 10);
        map.remove("key1");
        map.add("key1", 20);

        assertEquals(20, map.get("key1"));
        assertEquals(1, map.size());
    }



}
