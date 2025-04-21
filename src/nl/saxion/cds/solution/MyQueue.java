package nl.saxion.cds.solution;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxQueue;

public class MyQueue<V> implements SaxQueue<V> {
    private MyDoublyLinkedList<V> list = new MyDoublyLinkedList<>();


    @Override
    public void enqueue(V value) {
        list.addLast(value);
    }

    @Override
    public V dequeue() throws EmptyCollectionException {
        if(list.isEmpty()) throw new EmptyCollectionException();
        V value = list.get(0);
        list.removeFirst();
        return value;
    }

    @Override
    public V peek() throws EmptyCollectionException {
        if(list.isEmpty()) throw new EmptyCollectionException();

        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String graphViz(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append("digraph ").append(name).append(" {\n");

        for (int i = 0; i < list.size(); i++) {
            builder.append("    ").append(list.get(i)).append(";\n");
            if (i < list.size() - 1) {
                builder.append("    ").append(list.get(i)).append(" -> ").append(list.get(i + 1)).append(";\n");
            }
        }

        builder.append("}");
        return builder.toString();
    }
}
