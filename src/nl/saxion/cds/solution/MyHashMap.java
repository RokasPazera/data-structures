package nl.saxion.cds.solution;

import nl.saxion.cds.collection.DuplicateKeyException;
import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.collection.SaxHashMap;
import nl.saxion.cds.collection.SaxList;

public class MyHashMap<K, V> implements SaxHashMap<K, V> {
    private class Bucket {
        private K key;
        private V value;
        private boolean isDeleted;

        public Bucket(K key, V value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }
    }

    private int size = 0;
    private MyArrayList<Bucket> buckets;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public MyHashMap() {
        buckets = new MyArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets.addLast(null);
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % buckets.size());
    }

    private Bucket getBucket(K key) {
        int index = getIndex(key);
        int i = index;
        while (true) {
            Bucket bucket = buckets.get(i);
            if (bucket == null) {
                return null;
            }
            if (!bucket.isDeleted && bucket.key.equals(key)) {
                return bucket;
            }
            i = (i + 1) % buckets.size();
            if (i == index) {
                return null;
            }
        }
    }

    @Override
    public boolean contains(K key) {
        return getBucket(key) != null;
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        Bucket bucket = getBucket(key);

        if(bucket == null || bucket.isDeleted){
            throw new KeyNotFoundException(key.toString());
        }

        return bucket.value;
    }

    @Override
    public void add(K key, V value) throws DuplicateKeyException, OutOfMemoryError {
        Bucket bucket = getBucket(key);
        if (bucket != null && !bucket.isDeleted) {
            throw new DuplicateKeyException(key.toString());
        }

        if (size >= buckets.size() * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key);
        int i = index;
        while (true) {
            bucket = buckets.get(i);
            if (bucket == null || bucket.isDeleted) {
                buckets.set(i, new Bucket(key, value));
                size++;
                break;
            }
            i = (i + 1) % buckets.size();

        }
    }

    private void resize() {
        MyArrayList<Bucket> oldBuckets = buckets;
        buckets = new MyArrayList<>(oldBuckets.size() * 2);
        for (int i = 0; i < oldBuckets.size() * 2; i++) {
            buckets.addLast(null);
        }
        size = 0;
        for (Bucket bucket : oldBuckets) {
            if (bucket != null && !bucket.isDeleted) {
                add(bucket.key, bucket.value);
            }
        }
    }

    @Override
    public V remove(K key) throws KeyNotFoundException {
        int index = getIndex(key);
        int i = index;
        while (true) {
            Bucket bucket = buckets.get(i);
            if (bucket == null) {
                throw new KeyNotFoundException(key.toString());
            }
            if (bucket.key.equals(key) && !bucket.isDeleted) {
                bucket.isDeleted = true;
                size--;
                return bucket.value;
            }
            i = (i + 1) % buckets.size();
        }
    }

    @Override
    public SaxList<K> getKeys() {
        MyArrayList<K> list = new MyArrayList<>(size);
        for (var bucket : buckets) {
            if (bucket != null && !bucket.isDeleted) {
                list.addLast(bucket.key);
            }
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        for (Bucket bucket : buckets) {
            if (bucket != null && !bucket.isDeleted) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String graphViz(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append("digraph ").append(name).append(" {\n");
        int index = 0;
        for (var bucket : buckets) {
            builder.append("bucket_").append(index);
            if (bucket == null) {
                builder.append(" [label=\"NULL\"]");
            } else {
                builder.append(" [label=\"").append(index).append(':')
                        .append(bucket.key).append(',').append(bucket.value).append("\"]");
            }
            builder.append(";\n");
            if (index < buckets.size() - 1) {
                builder.append("bucket_").append(index).append(" -> bucket_")
                        .append(index + 1).append(";\n");
            }
            index++;
        }
        builder.append("}");
        return builder.toString();
    }
}