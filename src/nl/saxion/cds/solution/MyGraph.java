package nl.saxion.cds.solution;

import nl.saxion.cds.collection.KeyNotFoundException;
import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.collection.SaxList;

import java.util.Comparator;
import java.util.Iterator;

public class MyGraph<V> implements SaxGraph<V> {
    private MyHashMap<V, MyArrayList<DirectedEdge<V>>> adjacencyList;
    private int nodeCount = 0;

    public MyGraph(int initialCapacity) {
        this.adjacencyList = new MyHashMap<>();
    }

    @Override
    public void addEdge(V sourceNode, V destinationNode, double edgeWeight) throws KeyNotFoundException {
        if (!adjacencyList.contains(sourceNode)) {
            adjacencyList.add(sourceNode, new MyArrayList<>());
            nodeCount++;
        }

        if (!adjacencyList.contains(destinationNode)) {
            adjacencyList.add(destinationNode, new MyArrayList<>());
            nodeCount++;
        }

        MyArrayList<DirectedEdge<V>> edgesFromSource = adjacencyList.get(sourceNode);
        edgesFromSource.addFirst(new DirectedEdge<>(sourceNode, destinationNode, edgeWeight));
    }

    @Override
    public void addEdgeBidirectional(V sourceNode, V destinationNode, double edgeWeight) {
        addEdge(destinationNode, sourceNode, edgeWeight);
        addEdge(sourceNode, destinationNode, edgeWeight);
    }

    @Override
    public SaxList<DirectedEdge<V>> getEdges(V nodeValue) throws KeyNotFoundException {
        if (!adjacencyList.contains(nodeValue)) {
            throw new KeyNotFoundException("Node does not exist in the graph: " + nodeValue);
        }
        return adjacencyList.get(nodeValue);
    }

    @Override
    public double getTotalWeight() {
        double totalWeight = 0;
        for (Object vertex : adjacencyList.getKeys()) {
            MyArrayList<DirectedEdge<V>> outgoingEdges = adjacencyList.get((V) vertex);
            for (int index = 0; index < outgoingEdges.size(); index++) {
                DirectedEdge<V> edge = outgoingEdges.get(index);
                totalWeight += edge.weight();
            }
        }
        return totalWeight;
    }

    @Override
    public SaxGraph<V> shortestPathsDijkstra(V startingNode) {
        if (!adjacencyList.contains(startingNode)) {
            return new MyGraph<>(this.nodeCount);
        }

        MyGraph<V> shortestPathsGraph = new MyGraph<>(this.nodeCount);
        MyHashSet<V> visitedNodes = new MyHashSet<>();
        Comparator<DirectedEdge<V>> edgeComparator = Comparator.comparingDouble(DirectedEdge::weight);
        MyPriorityQueue<DirectedEdge<V>> priorityQueue = new MyPriorityQueue<>(edgeComparator);

        priorityQueue.enqueue(new DirectedEdge<>(startingNode, startingNode, 0));
        while (!priorityQueue.isEmpty()) {
            DirectedEdge<V> currentEdge = priorityQueue.dequeue();

            if (!visitedNodes.contains(currentEdge.from())) {
                visitedNodes.add(currentEdge.from());
                shortestPathsGraph.addEdge(currentEdge.from(), currentEdge.to(), currentEdge.weight());
                for (DirectedEdge<V> neighborEdge : getEdges(currentEdge.from())) {
                    if (!visitedNodes.contains(neighborEdge.to())) {
                        priorityQueue.enqueue(new DirectedEdge<>(
                                neighborEdge.to(),
                                neighborEdge.from(),
                                neighborEdge.weight() + currentEdge.weight()
                        ));
                    }
                }
            }
        }
        return shortestPathsGraph;
    }


    public class AStarSearchNode implements Comparable<AStarSearchNode> {
        DirectedEdge<V> edge;
        double gCost;
        double hCost;
        AStarSearchNode previousNode;

        public AStarSearchNode(DirectedEdge<V> edge, double gCost, double hCost, AStarSearchNode previousNode) {
            this.edge = edge;
            this.gCost = gCost;
            this.hCost = hCost;
            this.previousNode = previousNode;
        }

        public V getCurrentNode() {
            return edge == null ? null : edge.to();
        }

        @Override
        public int compareTo(AStarSearchNode otherNode) {
            return Double.compare(this.gCost + this.hCost, otherNode.gCost + otherNode.hCost);
        }
    }

    @Override
    public SaxList<DirectedEdge<V>> shortestPathAStar(V startNode, V targetNode, Estimator<V> heuristic) {
        Comparator<AStarSearchNode> nodeComparator = Comparator.comparingDouble(node -> node.gCost + node.hCost);
        MyPriorityQueue<AStarSearchNode> openSet = new MyPriorityQueue<>(nodeComparator);
        MyHashMap<V, AStarSearchNode> closedSet = new MyHashMap<>();

        AStarSearchNode initialNode = new AStarSearchNode(new DirectedEdge<>(startNode, startNode, 0), 0, heuristic.estimate(startNode, targetNode), null);
        openSet.enqueue(initialNode);

        while (!openSet.isEmpty()) {
            AStarSearchNode currentNode = openSet.dequeue();
            V currentVertex = currentNode.getCurrentNode();

            if (currentVertex == null) continue;
            if (closedSet.contains(currentVertex)) continue;

            closedSet.add(currentVertex, currentNode);

            if (currentVertex.equals(targetNode)) {
                return reconstructPath(closedSet, currentNode);
            }

            for (DirectedEdge<V> neighborEdge : getEdges(currentVertex)) {
                V neighborVertex = neighborEdge.to();
                if (closedSet.contains(neighborVertex)) continue;

                double newGCost = currentNode.gCost + neighborEdge.weight();
                double newHCost = heuristic.estimate(neighborVertex, targetNode);
                AStarSearchNode neighborNode = new AStarSearchNode(
                        neighborEdge,
                        newGCost,
                        newHCost,
                        currentNode
                );

                openSet.enqueue(neighborNode);
            }
        }
        return new MyArrayList<>();
    }


    public SaxList<DirectedEdge<V>> reconstructPath(MyHashMap<V, AStarSearchNode> closedSet, AStarSearchNode goalNode) {
        MyArrayList<DirectedEdge<V>> path = new MyArrayList<>();
        AStarSearchNode currentNode = goalNode;

        while (currentNode != null && currentNode.edge != null) {
            path.addFirst(currentNode.edge);
            currentNode = currentNode.previousNode;
        }

        return path;
    }

    @Override
    public SaxGraph<V> minimumCostSpanningTree() {
        SaxGraph<V> mstGraph = new MyGraph<>(this.nodeCount);
        MyHashSet<V> visitedNodes = new MyHashSet<>();
        MyHeap<DirectedEdge<V>> edgePriorityQueue = new MyHeap<>();

        Iterator<V> graphNodes = this.iterator();
        if (!graphNodes.hasNext()) {
            return mstGraph;
        }
        V initialNode = graphNodes.next();

        visitedNodes.add(initialNode);
        for (DirectedEdge<V> edge : getEdges(initialNode)) {
            edgePriorityQueue.enqueue(edge);
        }

        while (!edgePriorityQueue.isEmpty()) {
            DirectedEdge<V> smallestEdge = edgePriorityQueue.dequeue();

            V sourceNode = smallestEdge.from();
            V targetNode = smallestEdge.to();

            if (!visitedNodes.contains(targetNode)) {
                mstGraph.addEdge(sourceNode, targetNode, smallestEdge.weight());
                visitedNodes.add(targetNode);

                for (DirectedEdge<V> edge : getEdges(targetNode)) {
                    if (!visitedNodes.contains(edge.to())) {
                        edgePriorityQueue.enqueue(edge);
                    }
                }
            }
        }

        return mstGraph;
    }

    @Override
    public Iterator<V> iterator() {
        return new BreadthFirstIterator();
    }

    @Override
    public boolean isEmpty() {
        return nodeCount == 0;
    }

    @Override
    public int size() {
        return this.nodeCount;
    }

    @Override
    public String graphViz(String graphName) {
        StringBuilder dotRepresentation = new StringBuilder("digraph " + graphName + " {\n");

        for (Object vertex : adjacencyList.getKeys()) {
            MyArrayList<DirectedEdge<V>> outgoingEdges = adjacencyList.get((V) vertex);

            for (int index = 0; index < outgoingEdges.size(); index++) {
                DirectedEdge<V> edge = outgoingEdges.get(index);
                V targetNode = edge.to();
                double edgeWeight = edge.weight();

                dotRepresentation.append("    \"").append(vertex).append("\" -> \"")
                        .append(targetNode).append("\"")
                        .append(" [label=\"").append(edgeWeight).append("\"];\n");
            }
        }

        dotRepresentation.append("}");
        return dotRepresentation.toString();
    }

    private class BreadthFirstIterator implements Iterator<V> {
        private MyQueue<V> traversalQueue = new MyQueue<>();
        private MyHashMap<V, Boolean> visitedNodes = new MyHashMap<>();

        public BreadthFirstIterator() {
            for (Object vertex : adjacencyList.getKeys()) {
                V startVertex = (V) vertex;
                traversalQueue.enqueue(startVertex);
                break;
            }
        }

        @Override
        public boolean hasNext() {
            return !traversalQueue.isEmpty();
        }

        @Override
        public V next() {
            while (hasNext()) {
                V nextVertex = traversalQueue.dequeue();
                if (visitedNodes.contains(nextVertex)) {
                    continue;
                }
                visitedNodes.add(nextVertex, true);
                MyArrayList<DirectedEdge<V>> neighbors = adjacencyList.get(nextVertex);
                if (neighbors != null) {
                    for (int index = 0; index < neighbors.size(); index++) {
                        DirectedEdge<V> edge = neighbors.get(index);
                        V neighborNode = edge.to();
                        if (!visitedNodes.contains(neighborNode)) {
                            traversalQueue.enqueue(neighborNode);
                        }
                    }
                }
                return nextVertex;
            }
            throw new IllegalStateException();
        }
    }
}
