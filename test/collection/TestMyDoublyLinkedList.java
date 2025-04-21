package collection;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.ValueNotFoundException;
import nl.saxion.cds.solution.MyDoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyDoublyLinkedList {
    private MyDoublyLinkedList<Integer> list;

    @BeforeEach
    void createList() {
        list = new MyDoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
    }

    @Test
    void givenEmptyList_WhenCallingEmptyList_ConfirmTheListIsEmpty() {
        MyDoublyLinkedList<Object> dummyList = new MyDoublyLinkedList<>();
        assertTrue(dummyList.isEmpty());
        assertEquals(0, dummyList.size());
    }

    @Test
    void givenSheetsList_WhenUsingLinearSearch_ReturnsCorrectElement() {
        int number = list.linearSearch(3);
        assertEquals(3, number);
        assertThrows(RuntimeException.class, () -> list.linearSearch(100));
    }

    @Test
    void givenSheetsList_WhenUsingBinarySearch_ThrowsException() {
        Integer element = null;
        Comparator<Integer> comparator = Integer::compareTo;
        assertThrows(RuntimeException.class, () -> list.binarySearch(comparator, element));
    }

    @Test
    void givenSheetsList_WhenCheckingIsSorted_ConfirmsListIsSorted() {
        MyDoublyLinkedList<Integer> dummyList = new MyDoublyLinkedList<>();
        dummyList.isSorted(Integer::compareTo);

        dummyList.addLast(5);
        dummyList.addLast(2);
        dummyList.addLast(5);

        assertFalse(dummyList.isSorted(Integer::compareTo));
        assertTrue(list.isSorted(Integer::compareTo));
    }

    @Test
    void givenSheetsList_WhenCheckingIfContains_ConfirmsItemIsInList() {
        assertTrue(list.contains(3));
        assertFalse(list.contains(7));
    }

    @Test
    void givenSheetsList_WhenCheckingItemIndex_ReturnsTheItem() {
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void givenNewList_WhenAddingLastItem_ConfirmsLastItemIsAdded() {
        MyDoublyLinkedList<Integer> dummyList = new MyDoublyLinkedList<>();
        dummyList.addLast(5);
        dummyList.addLast(7);
        dummyList.addLast(11);

        assertEquals(5, dummyList.get(0));
        assertEquals(7, dummyList.get(1));
        assertEquals(11, dummyList.get(2));
    }

    @Test
    void givenNewList_WhenAddingFirstItem_ConfirmsLastItemIsAdded() {
        MyDoublyLinkedList<Integer> dummyList = new MyDoublyLinkedList<>();
        dummyList.addFirst(5);
        dummyList.addFirst(7);
        dummyList.addFirst(11);

        assertEquals(11, dummyList.get(0));
        assertEquals(7, dummyList.get(1));
        assertEquals(5, dummyList.get(2));
    }

    @Test
    void givenSheetsList_WhenAddingItemAtIndex_ConfirmsItemIsAdded() {
        list.addAt(2, 8);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(8, list.get(2));
        assertEquals(3, list.get(3));
        assertEquals(4, list.get(4));
        assertEquals(5, list.get(5));
        assertEquals(6, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> list.addAt(7, 6));
    }

    @Test
    void givenSheetsList_WhenSettingItemAtIndex_ConfirmsItemIsChanged() {
        list.set(3, 511);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(511, list.get(3));

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 5));
    }

    @Test
    void givenSheetsList_WhenRemovingLastItem_ConfirmsLastItemIsRemoved() {
        assertEquals(5, list.size());
        list.removeLast();
        assertEquals(4, list.size());

        MyDoublyLinkedList<Integer> dummyList = new MyDoublyLinkedList<>();

        assertThrows(EmptyCollectionException.class, dummyList::removeLast);
    }

    @Test
    void givenSheetsList_WhenRemovingFirstItem_ConfirmsFirstItemIsRemoved() {
        assertEquals(5, list.size());
        list.removeFirst();
        assertEquals(4, list.size());

        MyDoublyLinkedList<Integer> dummyList = new MyDoublyLinkedList<>();

        assertThrows(EmptyCollectionException.class, dummyList::removeFirst);
    }

    @Test
    void givenSheetsList_WhenRemovingItemAtIndex_ConfirmsItemIsRemoved() {
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
        assertEquals(5, list.get(4));

        assertEquals(5, list.size());
        list.removeAt(2);
        assertEquals(4, list.size());

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(4, list.get(2));
        assertEquals(5, list.get(3));

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(10));
    }

    @Test
    void givenSheetsList_WhenRemovingItem_ConfirmsItemIsRemoved() {
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
        assertEquals(5, list.get(4));

        assertEquals(5, list.size());
        list.remove(3);
        assertEquals(4, list.size());

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(4, list.get(2));
        assertEquals(5, list.get(3));

        assertThrows(ValueNotFoundException.class, () -> list.remove(10));
    }

    @Test
    void givenSheetsList_WhenIteratingThroughList_ConfirmsItemsInList() {
        int index = 1;
        for (int current : list) {
            assertEquals(index, current);
            index++;
        }
    }

    @Test
    void givenNewList_WhenCheckingIfTheListIsEmpty_ReturnsTrue() {
        MyDoublyLinkedList<Integer> dummyList = new MyDoublyLinkedList<>();
        assertTrue(dummyList.isEmpty());
    }

    @Test
    void givenSheetsList_WhenCheckingSize_ReturnsListSize() {
        assertEquals(5, list.size());
    }

    @Test
    void givenNewList_WhenUsingSimpleSort_SortsTheList() {
        MyDoublyLinkedList<Integer> dummyList = new MyDoublyLinkedList<>();
        dummyList.addLast(5);
        dummyList.addLast(3);
        dummyList.addLast(8);
        dummyList.addLast(1);

        dummyList.simpleSort(Integer::compareTo);

        assertEquals(1, dummyList.get(0));
        assertEquals(3, dummyList.get(1));
        assertEquals(5, dummyList.get(2));
        assertEquals(8, dummyList.get(3));
    }

    // New tests added

    @Test
    void givenSingleItemList_WhenUsingQuickSort_DoesNotThrowException() {
        MyDoublyLinkedList<Integer> singleItemList = new MyDoublyLinkedList<>();
        singleItemList.addLast(42);
        assertDoesNotThrow(() -> singleItemList.quickSort(Integer::compareTo));
    }

    @Test
    void givenSheetsList_WhenUsingBinarySearch_ReturnsFirstElement() {
        assertEquals(0, list.binarySearch(Integer::compareTo, 1));
    }

    @Test
    void givenSheetsList_WhenUsingBinarySearch_ReturnsLastElement() {
        assertEquals(4, list.binarySearch(Integer::compareTo, 5));
    }

    @Test
    void givenSingleItemList_WhenRemovingFirstItem_ConfirmsListIsEmpty() {
        MyDoublyLinkedList<Integer> singleItemList = new MyDoublyLinkedList<>();
        singleItemList.addLast(42);
        singleItemList.removeFirst();
        assertTrue(singleItemList.isEmpty());
    }

    @Test
    void givenSingleItemList_WhenRemovingLastItem_ConfirmsListIsEmpty() {
        MyDoublyLinkedList<Integer> singleItemList = new MyDoublyLinkedList<>();
        singleItemList.addLast(42);
        singleItemList.removeLast();
        assertTrue(singleItemList.isEmpty());
    }

    @Test
    void givenSingleItemList_WhenRemovingItem_ConfirmsItemIsRemoved() {
        MyDoublyLinkedList<Integer> singleItemList = new MyDoublyLinkedList<>();
        singleItemList.addLast(42);
        singleItemList.remove(42);
        assertTrue(singleItemList.isEmpty());
    }

    @Test
    void givenSheetsList_WhenGettingItemAtInvalidIndex_ThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(10));
    }

    @Test
    void givenSheetsList_WhenRemovingItemAtInvalidIndex_ThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(10));
    }

    @Test
    void givenSortedList_WhenUsingQuickSort_DoesNotChangeOrder() {
        MyDoublyLinkedList<Integer> sortedList = new MyDoublyLinkedList<>();
        sortedList.addLast(1);
        sortedList.addLast(2);
        sortedList.addLast(3);
        sortedList.quickSort(Integer::compareTo);
        assertEquals(1, sortedList.get(0));
        assertEquals(2, sortedList.get(1));
        assertEquals(3, sortedList.get(2));
    }

    @Test
    void givenReverseSortedList_WhenUsingQuickSort_SortsTheList() {
        MyDoublyLinkedList<Integer> reverseSortedList = new MyDoublyLinkedList<>();
        reverseSortedList.addLast(5);
        reverseSortedList.addLast(4);
        reverseSortedList.addLast(3);
        reverseSortedList.addLast(2);
        reverseSortedList.addLast(1);
        reverseSortedList.quickSort(Integer::compareTo);
        assertEquals(1, reverseSortedList.get(0));
        assertEquals(2, reverseSortedList.get(1));
        assertEquals(3, reverseSortedList.get(2));
        assertEquals(4, reverseSortedList.get(3));
        assertEquals(5, reverseSortedList.get(4));
    }

    @Test
    void givenListWithDuplicates_WhenUsingSimpleSort_SortsTheListCorrectly() {
        MyDoublyLinkedList<Integer> listWithDuplicates = new MyDoublyLinkedList<>();
        listWithDuplicates.addLast(3);
        listWithDuplicates.addLast(1);
        listWithDuplicates.addLast(2);
        listWithDuplicates.addLast(2);
        listWithDuplicates.addLast(3);
        listWithDuplicates.simpleSort(Integer::compareTo);
        assertEquals(1, listWithDuplicates.get(0));
        assertEquals(2, listWithDuplicates.get(1));
        assertEquals(2, listWithDuplicates.get(2));
        assertEquals(3, listWithDuplicates.get(3));
        assertEquals(3, listWithDuplicates.get(4));
    }

    @Test
    void givenSheetsList_WhenCheckingIfContainsElement_ReturnsCorrectResult() {
        assertTrue(list.contains(1));
        assertTrue(list.contains(3));
        assertTrue(list.contains(5));
        assertFalse(list.contains(10));
    }

    @Test
    void givenEmptyList_WhenIterating_ConfirmsNoItems() {
        MyDoublyLinkedList<Integer> emptyList = new MyDoublyLinkedList<>();
        int count = 0;
        for (Integer ignored : emptyList) {
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    void givenSingleItemList_WhenIterating_ConfirmsSingleItem() {
        MyDoublyLinkedList<Integer> singleItemList = new MyDoublyLinkedList<>();
        singleItemList.addLast(42);
        int count = 0;
        for (Integer item : singleItemList) {
            assertEquals(42, item);
            count++;
        }
        assertEquals(1, count);
    }

    @Test
    void givenEmptyList_WhenAddingItem_ConfirmsItemIsAdded() {
        MyDoublyLinkedList<Integer> emptyList = new MyDoublyLinkedList<>();
        emptyList.addLast(10);
        assertEquals(1, emptyList.size());
        assertEquals(10, emptyList.get(0));
    }

    @Test
    void givenList_WhenAddingItemAtInvalidIndex_ThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAt(-1, 100));
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAt(10, 100));
    }

    @Test
    void givenList_WhenModifyingItemInMiddle_ConfirmsModification() {
        list.set(2, 999);
        assertEquals(999, list.get(2));
    }

    @Test
    void givenList_WhenRemovingAllItems_ConfirmsListIsEmpty() {
        while (!list.isEmpty()) {
            list.removeFirst();
        }
        assertTrue(list.isEmpty());
    }

    @Test
    void givenList_WhenRemovingItemsUntilEmpty_ConfirmsSizeReduces() {
        int initialSize = list.size();
        while (!list.isEmpty()) {
            list.removeFirst();
        }
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void givenList_WhenAddingAndRemovingItemsInSequence_ConfirmsCorrectBehavior() {
        list.addLast(6);
        assertEquals(6, list.get(5));
        list.removeFirst();
        assertEquals(2, list.get(0));
    }

    @Test
    void givenList_WhenRemovingItemNotInList_ThrowsException() {
        assertThrows(ValueNotFoundException.class, () -> list.remove(100));
    }


    @Test
    void givenList_WhenUsingForEachLoop_IteratesCorrectly() {
        final int[] sum = {0};
        list.forEach(item -> sum[0] += item);
        assertEquals(15, sum[0]);
    }

    @Test
    void givenListWithIdenticalElements_WhenUsingSimpleSort_DoesNotChangeOrder() {
        MyDoublyLinkedList<Integer> identicalList = new MyDoublyLinkedList<>();
        identicalList.addLast(5);
        identicalList.addLast(5);
        identicalList.addLast(5);

        identicalList.simpleSort(Integer::compareTo);

        assertEquals(5, identicalList.get(0));
        assertEquals(5, identicalList.get(1));
        assertEquals(5, identicalList.get(2));
    }


    @Test
    void givenSheetsList_WhenUsingCustomComparator_SortsInDescendingOrder() {
        list.quickSort((a, b) -> b - a);
        assertEquals(5, list.get(0));
        assertEquals(4, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(2, list.get(3));
        assertEquals(1, list.get(4));
    }


    @Test
    void givenLargeList_WhenAddingAndSorting_PerformsWithinLimits() {
        MyDoublyLinkedList<Integer> largeList = new MyDoublyLinkedList<>();
        int size = 10000;

        for (int i = size; i > 0; i--) {
            largeList.addLast(i);
        }

        assertDoesNotThrow(() -> largeList.quickSort(Integer::compareTo));
        for (int i = 0; i < size; i++) {
            assertEquals(i + 1, largeList.get(i));
        }
    }

    @Test
    void givenListWithNegativeAndPositiveNumbers_WhenSorting_SortsCorrectly() {
        MyDoublyLinkedList<Integer> mixedList = new MyDoublyLinkedList<>();
        mixedList.addLast(-5);
        mixedList.addLast(3);
        mixedList.addLast(0);
        mixedList.addLast(-1);
        mixedList.addLast(7);

        mixedList.simpleSort(Integer::compareTo);

        assertEquals(-5, mixedList.get(0));
        assertEquals(-1, mixedList.get(1));
        assertEquals(0, mixedList.get(2));
        assertEquals(3, mixedList.get(3));
        assertEquals(7, mixedList.get(4));
    }

    @Test
    void givenListWithNullValues_WhenAdding_ThrowsException() {
        MyDoublyLinkedList<Integer> listWithNull = new MyDoublyLinkedList<>();
        assertThrows(NullPointerException.class, () -> listWithNull.addLast(null));
        assertThrows(NullPointerException.class, () -> listWithNull.addFirst(null));
    }

    @Test
    void givenListWithDuplicates_WhenRemovingItem_RemovesFirstOccurrence() {
        MyDoublyLinkedList<Integer> listWithDuplicates = new MyDoublyLinkedList<>();
        listWithDuplicates.addLast(1);
        listWithDuplicates.addLast(2);
        listWithDuplicates.addLast(2);
        listWithDuplicates.addLast(3);

        listWithDuplicates.remove(2);

        assertEquals(1, listWithDuplicates.get(0));
        assertEquals(2, listWithDuplicates.get(1));
        assertEquals(3, listWithDuplicates.get(2));
        assertEquals(3, listWithDuplicates.size());
    }

    @Test
    void givenDummyList_WhenTestingGraphViz_ConfirmGraphVizCorrect() {
        MyDoublyLinkedList<String> list = new MyDoublyLinkedList<>();

        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        String graphVizOutput = list.graphViz("TestGraph");

        String expectedOutput = """
                digraph TestGraph {
                rankdir=LR;
                node0 [label="A"];
                node0 -> node1;
                node1 -> node0;
                node1 [label="B"];
                node1 -> node2;
                node2 -> node1;
                node2 [label="C"];
                }
                """;

        assertEquals(expectedOutput.strip(), graphVizOutput.strip());
    }




}
