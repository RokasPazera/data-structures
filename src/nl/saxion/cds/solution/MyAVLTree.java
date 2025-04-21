package nl.saxion.cds.solution;

import nl.saxion.cds.collection.DuplicateKeyException;
import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.collection.SaxBinaryTree;
import nl.saxion.cds.collection.SaxList;

public class MyAVLTree<K extends Comparable<K>, V> implements SaxBinaryTree<K, V> {

    private Node root = null;
    private int size = 0;

    public class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }

        public void graphViz(StringBuilder builder) {
            builder.append(key.toString())
                    .append("[label=\"")
                    .append(key.toString())
                    .append("\\n")
                    .append(value.toString())
                    .append("\"]\n");

            if (left != null) {
                builder.append(key.toString())
                        .append(" -> ")
                        .append(left.key.toString())
                        .append("\n");
                left.graphViz(builder);
            }
            if (right != null) {
                builder.append(key.toString())
                        .append(" -> ")
                        .append(right.key.toString())
                        .append("\n");
                right.graphViz(builder);
            }
        }
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    private Node balance(Node node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    @Override
    public void add(K key, V value) throws DuplicateKeyException {
        root = addRecursive(root, key, value);
    }

    private Node addRecursive(Node node, K key, V value) throws DuplicateKeyException {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int compare = key.compareTo(node.key);
        if (compare == 0) {
            throw new DuplicateKeyException(key.toString());
        } else if (compare < 0) {
            node.left = addRecursive(node.left, key, value);
        } else {
            node.right = addRecursive(node.right, key, value);
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return balance(node);
    }

    @Override
    public V remove(K key) throws KeyNotFoundException {
        if (root == null) throw new KeyNotFoundException(key.toString());

        V[] removedValue = (V[]) new Object[1];
        root = removeRecursive(root, key, removedValue);

        if (removedValue[0] == null) throw new KeyNotFoundException(key.toString());
        size--;
        return removedValue[0];
    }

    private Node removeRecursive(Node node, K key, V[] removedValue) {
        if (node == null) return null;

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = removeRecursive(node.left, key, removedValue);
        } else if (compare > 0) {
            node.right = removeRecursive(node.right, key, removedValue);
        } else {
            removedValue[0] = node.value;

            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node smallest = findSmallestNode(node.right);
            node.key = smallest.key;
            node.value = smallest.value;
            node.right = removeRecursive(node.right, smallest.key, removedValue);
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return balance(node);
    }

    private Node findSmallestNode(Node node) {
        return (node.left == null) ? node : findSmallestNode(node.left);
    }

    @Override
    public boolean contains(K key) {
        return containsRecursive(root, key);
    }

    private boolean containsRecursive(Node current, K key) {
        if (current == null) return false;
        int compare = current.key.compareTo(key);
        if (compare == 0) return true;
        return (compare < 0) ? containsRecursive(current.right, key) : containsRecursive(current.left, key);
    }

    @Override
    public V get(K key) {
        return getRecursive(root, key);
    }

    private V getRecursive(Node current, K key) {
        if (current == null) return null;
        int compare = current.key.compareTo(key);
        if (compare == 0) return current.value;
        return (compare < 0) ? getRecursive(current.right, key) : getRecursive(current.left, key);
    }

    @Override
    public SaxList<K> getKeys() {
        SaxList<K> keys = new MyArrayList<>();
        inorderTraversal(root, keys);
        return keys;
    }

    private void inorderTraversal(Node current, SaxList<K> keys) {
        if (current != null) {
            inorderTraversal(current.left, keys);
            keys.addLast(current.key);
            inorderTraversal(current.right, keys);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String graphViz(String name) {
        StringBuilder builder = new StringBuilder("digraph " + name + "{\n");
        if (root != null) {
            root.graphViz(builder);
        }
        builder.append("}");
        return builder.toString();
    }
}
