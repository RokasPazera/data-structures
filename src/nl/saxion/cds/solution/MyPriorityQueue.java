package nl.saxion.cds.solution;


import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxQueue;
import java.util.Comparator;

public class MyPriorityQueue<V> implements SaxQueue<V> {
    private final MyArrayList<V> elements;
    private final Comparator<V> comparator;

    public MyPriorityQueue(Comparator<V> comparator) {
        elements = new MyArrayList<>();
        this.comparator = comparator;
    }

    @Override
    public void enqueue(V value) {
        elements.addLast(value);
        elements.quickSort(comparator);
    }

    @Override
    public V dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return elements.removeFirst();
    }

    @Override
    public V peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        }
        return elements.get(0);
    }

    public boolean contains(V v){
        for (V element: elements){
            if(element.equals(v)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public String graphViz(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append("digraph ").append(name).append(" {\n");

        for (int i = 0; i < elements.size(); i++) {
            builder.append("    ").append(elements.get(i)).append(";\n");
            if (i < elements.size() - 1) {
                builder.append("    ").append(elements.get(i)).append(" -> ").append(elements.get(i + 1)).append(";\n");
            }
        }

        builder.append("}");
        return builder.toString();
    }
}
