package nl.saxion.cds.solution;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxStack;

import java.util.Arrays;

public class MyStack<V> implements SaxStack<V> {
    private V[] stack;
    private int size;
    private int capacity = 10;

    @SuppressWarnings("unchecked")
    public MyStack(){
        stack = (V[]) new Object[capacity];
        size = 0;
    }
    @Override
    public void push(V value) {
        if(size == capacity){
            increaseSize();
        }
        stack[size] = value;
        size++;

    }

    @Override
    public V pop() throws EmptyCollectionException {
        if(size==0) throw new  EmptyCollectionException();
        V value = stack[size-1];
        size--;
        return value;
    }

    @Override
    public V peek() throws EmptyCollectionException {
        if(size==0) throw new EmptyCollectionException();
        return stack[size-1];
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
        StringBuilder builder = new StringBuilder();
        builder.append("digraph ").append(name).append(" {\n");

        for (int i = size - 1; i >= 0; i--) {
            builder.append("    ").append(stack[i]).append(";\n");
            if (i < size - 1) {
                builder.append("    ").append(stack[i + 1]).append(" -> ").append(stack[i]).append(";\n");
            }
        }

        builder.append("}");
        return builder.toString();
    }

    private void increaseSize(){
        capacity*=2;
        stack = Arrays.copyOf(stack, capacity);
    }
}
