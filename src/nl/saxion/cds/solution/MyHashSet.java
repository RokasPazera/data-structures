package nl.saxion.cds.solution;

import java.util.HashMap;
import java.util.Iterator;

public class MyHashSet<E> implements Iterable<E> {
    private final HashMap<E, Boolean> map;

    public MyHashSet() {
        map = new HashMap<>();
    }

    public void add(E element) {
        map.put(element, true);
    }

    public boolean remove(E element) {
        return map.remove(element) != null;
    }

    public boolean contains(E element) {
        return map.containsKey(element);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }


}
