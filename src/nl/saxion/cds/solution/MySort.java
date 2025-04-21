package nl.saxion.cds.solution;

import nl.saxion.cds.collection.SaxSortable;

import java.util.Comparator;

public class MySort<V> implements SaxSortable<V> {
    private V[] list;

    public void setList(V[] list) {
        this.list = list;
    }

    public int size() {
        return list.length;
    }

    @Override
    public boolean isSorted(Comparator<V> comparator) {
        if (list == null ) throw new NullPointerException();

        if(list.length <= 1) return true;

        for (int i = 1; i < list.length; i++) {
            if (comparator.compare(list[i - 1], list[i]) > 0) return false;
        }
        return true;
    }

    @Override
    public void simpleSort(Comparator<V> comparator) {
        if (list == null) throw new NullPointerException();

        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (comparator.compare(list[i], list[j]) > 0) {
                    V temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

    @Override
    public void quickSort(Comparator<V> comparator) {
        if (list == null) throw new NullPointerException();
        quickSort(comparator, 0, list.length - 1);
    }

    private void quickSort(Comparator<V> comparator, int low, int high) {
        if (low < high) {
            int number = partition(comparator, low, high);
            quickSort(comparator, low, number - 1);
            quickSort(comparator, number + 1, high);
        }
    }

    private int partition(Comparator<V> comparator, int low, int high) {
        V pivot = list[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(list[j], pivot) <= 0) {
                i++;
                V temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }
        V temp = list[i + 1];
        list[i + 1] = list[high];
        list[high] = temp;
        return i + 1;
    }
}