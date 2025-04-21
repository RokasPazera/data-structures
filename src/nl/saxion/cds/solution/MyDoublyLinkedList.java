package nl.saxion.cds.solution;

import nl.saxion.cds.collection.*;

import java.util.Comparator;
import java.util.Iterator;

public class MyDoublyLinkedList<V> implements SaxList<V>, SaxSortable<V>, SaxSearchable<V> {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public int linearSearch(Object element) {
        if (size <= 0) throw new IndexOutOfBoundsException();
        int i = 0;

        Node current = head;
        while (current != null) {
            i++;
            if (current.value == element) {
                return i;
            }
            current = current.next;
        }

        throw new RuntimeException("element does not exist");

    }

    @Override
    public int binarySearch(Comparator<V> comparator, V element) {
        if (!isSorted(comparator)) {
            throw new RuntimeException("List is not sorted.");
        }

        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            V midValue = get(mid);

            int cmp = comparator.compare(midValue, element);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    @Override
    public boolean isSorted(Comparator<V> comparator) {
        if (size <= 0) return true;

        Node current = head;

        while (current.next != null) {
            if (comparator.compare(current.value, current.next.value) > 0) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    @Override
    public void simpleSort(Comparator<V> comparator) {
        if (size <= 1) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;

            while (current != null && current.next != null) {
                if (comparator.compare(current.value, current.next.value) > 0) {
                    V temp = current.value;
                    current.value = current.next.value;
                    current.next.value = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    @Override
    public void quickSort(Comparator<V> comparator) {
        if (size <= 1) return;
        quickSortHelper(head, tail, comparator);
    }

    private void quickSortHelper(Node low, Node high, Comparator<V> comparator) {
        if (low != null && high != null && low != high && low != high.next) {
            Node pivot = partition(low, high, comparator);
            quickSortHelper(low, pivot.previous, comparator);
            quickSortHelper(pivot.next, high, comparator);
        }
    }

    private Node partition(Node low, Node high, Comparator<V> comparator) {
        V pivotValue = high.value;
        Node i = low.previous;

        for (Node j = low; j != high; j = j.next) {
            if (comparator.compare(j.value, pivotValue) <= 0) {
                i = (i == null) ? low : i.next;
                V temp = i.value;
                i.value = j.value;
                j.value = temp;
            }
        }

        i = (i == null) ? low : i.next;
        V temp = i.value;
        i.value = high.value;
        high.value = temp;

        return i;
    }

    private class Node {
        V value;
        Node next;
        Node previous;

        public Node(V value) {
            this.value = value;
        }
    }

    public MyDoublyLinkedList() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(V value) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public V get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node current;
        current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    @Override
    public void addLast(V value) {
        if (value == null) throw new NullPointerException();
        Node newNode = new Node(value);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;

    }

    @Override
    public void addFirst(V value) {
        if (value == null) throw new NullPointerException();
        Node newNode = new Node(value);
        if (size == 0) {
            head = tail = newNode;
        } else {
            head.previous = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;

    }

    @Override
    public void addAt(int index, V value) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == 0) addFirst(value);
        else if (index == size) addLast(value);
        else {
            Node newNode = new Node(value);
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode;
            current.previous = newNode;

            size++;

        }

    }

    @Override
    public void set(int index, V value) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.value = value;

    }

    @Override
    public V removeLast() throws EmptyCollectionException {
        if (size == 0) throw new EmptyCollectionException();

        V value = head.value;
        tail = tail.previous;
        if (tail != null) tail.next = null;
        else {
            head = null;
        }

        size--;
        return value;
    }

    @Override
    public V removeFirst() throws EmptyCollectionException {
        if (size == 0) throw new EmptyCollectionException();
        V value = head.value;

        head = head.next;
        if (head != null) {
            head.previous = null;
        } else {
            tail = null;
        }

        size--;
        return value;
    }

    @Override
    public V removeAt(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        V value = current.value;
        current.previous.next = current.next;
        current.next.previous = current.previous;

        size--;
        return value;
    }

    @Override
    public void remove(V value) throws ValueNotFoundException {
        Node current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                if (current == head) {
                    removeFirst();
                } else if (current == tail) {
                    removeLast();
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    size--;
                }
                return;
            }
            current = current.next;
        }
        throw new ValueNotFoundException("Value not found");

    }

    public Iterator<V> iterator() {
        return new Iterator<>() {

            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public V next() {
                var temp = current.value;
                current = current.next;
                return temp;
            }

        };
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String graphViz(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ").append(name).append(" {\n");
        sb.append("rankdir=LR;\n");

        Node current = head;
        int index = 0;
        while (current != null) {
            sb.append("node").append(index).append(" [label=\"").append(current.value).append("\"];\n");
            if (current.next != null) {
                sb.append("node").append(index).append(" -> node").append(index + 1).append(";\n");
                sb.append("node").append(index + 1).append(" -> node").append(index).append(";\n");
            }
            current = current.next;
            index++;
        }

        sb.append("}\n");
        return sb.toString();
    }
}
