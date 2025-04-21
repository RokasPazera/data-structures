package collection;

import nl.saxion.cds.solution.MySort;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class TestMySort {


    @Test
    void givenEmptyList_WhenCheckingIfItsSorted_ConfirmItsSorted(){
        Integer[] arr = new Integer[0];

        MySort<Integer> list = new MySort<>();

        list.setList(arr);

        Comparator<Integer> comparator = Integer::compareTo;
        assertTrue(list.isSorted(comparator));
        assertEquals(0, list.size());
    }

    @Test
    void givenSingleElementList_WhenCheckingIfItsSorted_ConfirmItsSorted(){
        Integer[] arr = new Integer[1];
        arr[0] = 1;

        MySort<Integer> list = new MySort<>();

        list.setList(arr);

        Comparator<Integer> comparator = Integer::compareTo;
        assertTrue(list.isSorted(comparator));
        assertEquals(1, list.size());
    }

    @Test
    void givenListWithDuplicates_WhenUsingSimpleSort_ListIsSorted(){
        Integer[] arr = new Integer[5];

        arr[0] = 8;
        arr[1] = 8;
        arr[2] = 7;
        arr[3] = 7;
        arr[4] = 1;

        MySort<Integer> list = new MySort<>();

        list.setList(arr);

        Comparator<Integer> comparator = Integer::compareTo;
        list.simpleSort(comparator);
        assertTrue(list.isSorted(comparator));
        assertEquals(5, list.size());
    }

    @Test
    void givenListWithNullElements_WhenUsingSimpleSort_ListIsSorted(){
        Integer[] arr = new Integer[5];

        arr[0] = 8;
        arr[1] = null;
        arr[2] = 7;
        arr[3] = null;
        arr[4] = 1;

        MySort<Integer> list = new MySort<>();

        list.setList(arr);

        Comparator<Integer> comparator = (a, b) -> {
            if(a == null && b == null) return 0;
            if(a == null) return -1;
            if(b == null) return 1;
            return a.compareTo(b);
        };
        list.simpleSort(comparator);
        assertTrue(list.isSorted(comparator));
        assertEquals(5, list.size());
    }

    @Test
    void givenSortedList_WhenCheckingIfItsSorted_ConfirmItsSorted(){
        Integer[] arr = new Integer[5];
        for(int i = 0; i < 5; i++){
            arr[i] = i;
        }

        MySort<Integer> list = new MySort<>();

        list.setList(arr);

        Comparator<Integer> comparator = Integer::compareTo;
        assertTrue(list.isSorted(comparator));
        assertEquals(5, list.size());
    }

    @Test
    void givenUnsortedList_WhenUsingSimpleSort_ListIsSorted(){
        Integer[] arr = new Integer[5];
        arr[0] = 8;
        arr[1] = 5;
        arr[2] = 7;
        arr[3] = 9;
        arr[4] = 1;

        MySort<Integer> list = new MySort<>();

        list.setList(arr);
        Comparator<Integer> comparator = Integer::compareTo;
        list.quickSort(comparator);
        assertTrue(list.isSorted(comparator));
        assertEquals(5, list.size());


    }

    @Test
    void givenNullList_WhenCheckingIfItsSorted_ThrowNullPointerException() {
        MySort<Integer> list = new MySort<>();
        Comparator<Integer> comparator = Integer::compareTo;

        assertThrows(NullPointerException.class, () -> list.isSorted(comparator));
    }

    @Test
    void givenNullList_WhenTryingToSort_ThrowNullPointerException() {
        MySort<Integer> list = new MySort<>();
        Comparator<Integer> comparator = Integer::compareTo;

        assertThrows(NullPointerException.class, () -> list.quickSort(comparator));
    }



}
