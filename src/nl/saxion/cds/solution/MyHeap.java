package nl.saxion.cds.solution;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxHeap;

public class MyHeap<V extends Comparable<V>> implements SaxHeap<V> {

    private final MyArrayList<V> list = new MyArrayList<>();
    private final int IS_LEAF_INDEX = -1;

    @Override
    public void enqueue(V value) {
        list.addLast(value);
        percolateUp(list.size() - 1);

    }

    @Override
    public V dequeue() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();


        V rootElement = list.get(0);

        if (size() == 1) {
            return list.removeLast();
        }


        list.set(0, list.removeLast());
        percolateDown(0);
        return rootElement;
    }

    @Override
    public V peek() throws EmptyCollectionException {
        if(isEmpty()) throw new EmptyCollectionException();
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
            int leftChildIndex = getLeftChildIndex(i);
            int rightChildIndex = getRightChildIndex(i);
            if (leftChildIndex != IS_LEAF_INDEX) {
                builder.append("    ").append(list.get(i)).append(" -> ").append(list.get(leftChildIndex)).append(";\n");
            }
            if (rightChildIndex != IS_LEAF_INDEX) {
                builder.append("    ").append(list.get(i)).append(" -> ").append(list.get(rightChildIndex)).append(";\n");
            }
        }

        builder.append("}");
        return builder.toString();
    }

    private int getLeftChildIndex(int parentIndex){
        int childIndex = 2 * parentIndex + 1;
        return childIndex >= size() ? IS_LEAF_INDEX : childIndex;
    }

    private int getRightChildIndex(int parentIndex){
        int childIndex = 2 * parentIndex + 2;
        return childIndex >= size() ? IS_LEAF_INDEX : childIndex;
    }

    private int getParentIndex(int childIndex){
        return (childIndex - 1) / 2;
    }

    private void percolateUp(int index){
        if(index == 0) return;

        int parentIndex = getParentIndex(index);
        V indexValue = list.get(index);
        V parentValue = list.get(parentIndex);

        if(indexValue.compareTo(parentValue) >= 0) return;

        list.set(parentIndex, indexValue);
        list.set(index, parentValue);
        percolateUp(parentIndex);
    }

    private void percolateDown(int index) {
        int leftIndex = getLeftChildIndex(index);
        if (leftIndex == IS_LEAF_INDEX) return;

        int rightIndex = getRightChildIndex(index);
        int smallestIndex = leftIndex;

        if (rightIndex != IS_LEAF_INDEX && list.get(rightIndex).compareTo(list.get(leftIndex)) < 0) {
            smallestIndex = rightIndex;
        }

        if (list.get(index).compareTo(list.get(smallestIndex)) > 0) {
            V temp = list.get(index);
            list.set(index, list.get(smallestIndex));
            list.set(smallestIndex, temp);
            percolateDown(smallestIndex);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("minHeap[ ");

        for(var element : list){
            stringBuilder.append(element);
            stringBuilder.append(' ');
        }

        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
