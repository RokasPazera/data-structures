# Algorithms and classes

## MyArrayList

Implementation: [MyArrayList](../src/nl/saxion/cds/solution/MyArrayList.java)


The MyArrayList class implements a dynamic array that grows and shrinks automatically based on the number of elements it stores.

Tests: [TestMyArrayList.java](../test/collection/TestMyArrayList.java)

### My binary search algorithm

Implementation: [MyArrayList](../src/nl/saxion/cds/solution/MyArrayList.java)

This search method assumes the list is already sorted according to the provided comparator.

It has a time complexity of O(log n) in the average and best cases, making it much faster than linear search for large data.

### My linear search algorithm
Implemented in [MyDoublyLinkedList](../src/nl/saxion/cds/solution/MyDoublyLinkedList.java)

### Element Not Found:

After the loop completes (no matching element found), a RuntimeException is thrown with a message indicating the element doesn't exist.
Classification: O(N)

This linear search algorithm has a time complexity of O(N) in the average and worst cases. It needs to iterate through the entire linked list in the worst-case scenario (element not found at the end).

### sources

the whole class was already given. no changes were made.


### My QuickSort algorithm
## MySort Class

This class, MySort<V>, implements the SaxSortable<V> interface. It provides functionalities for sorting generic data types (V) using various methods.

implemented in [MySort](../src/nl/saxion/cds/solution/MySort.java) class

### Implementation

The MySort class offers the following methods:

- setList(V[] list)
Sets the list of elements (V[]) to be sorted.

- size()
Returns the size (number of elements) of the currently set list.

- isSorted(Comparator<V> comparator)
Checks if the current list is sorted according to the provided comparator.

- simpleSort(Comparator<V> comparator)
Sorts the list using a simple bubble sort algorithm based on the given comparator.

- quickSort(Comparator<V> comparator)
Sorts the list using the efficient quicksort algorithm based on the provided comparator.

### Tests

- givenEmptyList_WhenCheckingIfItsSorted_ConfirmItsSorted:
Verifies that an empty list is considered sorted and has a size of 0.


- givenSingleElementList_WhenCheckingIfItsSorted_ConfirmItsSorted:
Confirms that a single-element list is sorted and its size is 1.


- givenListWithDuplicates_WhenUsingSimpleSort_ListIsSorted:
Tests if the simpleSort method correctly sorts a list containing duplicate elements.


- givenListWithNullElements_WhenUsingSimpleSort_ListIsSorted:
Ensures simpleSort can handle lists with null elements using a custom comparator and confirms sorting.


- givenSortedList_WhenCheckingIfItsSorted_ConfirmItsSorted:
Confirms that a pre-sorted list is recognized as sorted without modifications.


- givenUnsortedList_WhenUsingSimpleSort_ListIsSorted:
Validates that the quickSort method sorts an unsorted list and confirms it is sorted afterward.


- givenNullList_WhenCheckingIfItsSorted_ThrowNullPointerException:
Checks if isSorted throws a NullPointerException when the list is null.


- givenNullList_WhenTryingToSort_ThrowNullPointerException:
Ensures quickSort throws a NullPointerException when attempting to sort a null list.

### Time Complexity:

Average case O(NlogN), worst case O(N^2)

### sources

isSorted and simpleSort was easy to make by myself, however for the quickSort, chatgpt help was used.


## My AVL
The [MyAVLTree](../src/nl/saxion/cds/solution/MyAVLTree.java) class implements a binary search tree (AVL) data structure. It provides methods for adding, removing, searching, and traversing elements in the tree, based on their keys.

### Method Descriptions:

- getHeight(Node node):
Returns the height of a node, treating null nodes as having a height of 0.

- getBalance(Node node):
Calculates and returns the balance factor of a node by subtracting the height of its right subtree from its left subtree.

- rotateRight(Node y):
Performs a right rotation around the specified node and updates the heights of affected nodes.

- rotateLeft(Node x):
Performs a left rotation around the specified node and updates the heights of affected nodes.

- balance(Node node):
Balances the subtree rooted at the given node if its balance factor is outside the allowed range (-1 to 1).

- add(K key, V value):
Adds a key-value pair to the tree, ensuring AVL balance, and throws a DuplicateKeyException if the key already exists.

- addRecursive(Node node, K key, V value):
Recursively inserts a new key-value pair into the subtree while maintaining AVL balance.

- remove(K key):
Removes a node with the specified key, rebalances the tree, and throws a KeyNotFoundException if the key doesn't exist.

 - removeRecursive(Node node, K key, V[] removedValue):
Recursively finds and removes a node with the given key while maintaining AVL balance and tracks the removed value.

- findSmallestNode(Node node):
Finds and returns the node with the smallest key in the given subtree.

- contains(K key):
Checks if the specified key exists in the tree.

- containsRecursive(Node current, K key):
Recursively searches for the specified key in the tree.

- get(K key):
Retrieves the value associated with the specified key, or returns null if the key doesn't exist.

- getRecursive(Node current, K key):
Recursively searches for the value associated with the specified key in the tree.

- getKeys():
Returns an ordered list of all keys in the tree using an in-order traversal.

- inorderTraversal(Node current, SaxList<K> keys):
Performs an in-order traversal of the tree, adding all keys to a list.

- isEmpty():
Checks if the tree is empty by evaluating the size.

- clear():
Empties the tree by setting the root to null and size to 0.

- size():
Returns the current size (number of nodes) of the tree.

- graphViz(String name):
Generates a GraphViz-compatible string representation of the tree for visualization.

### Tests

- givenEmptyTree_WhenCheckingIfListIsEmpty_ConfirmsItsEmpty:
- Verifies that a newly created AVL tree is empty and its size is zero.

- givenTree_WhenAddingElements_ThenChecksSizeAndGraphViz:
- Confirms that adding elements updates the size and generates the correct GraphViz representation.

- givenTree_WhenAddingDuplicateKey_ThrowsException:
- Ensures that attempting to add a duplicate key throws a DuplicateKeyException.

- givenTree_WhenCheckingContainsAndGet_ConfirmsCorrectBehavior:
- Validates that contains and get work as expected for existing and non-existing keys.

- givenTree_WhenRemovingElements_ConfirmsCorrectRemovalAndBalancing:
- Checks that removing elements decreases size, balances the tree, and throws an exception for non-existing keys.

- givenTree_WhenCheckingKeys_ReturnsAllKeysInOrder:
- Ensures getKeys returns keys in sorted order as a string representation.

- givenTree_WhenBalancing_ConfirmsTreeRemainsBalanced:
- Verifies that the tree self-balances correctly after insertions and deletions.

- givenSingleNodeTree_WhenCallingMethods_ThenBehaveCorrectly:
- Confirms correct behavior for add, get, contains, and remove on a tree with one node.

- givenUnbalancedTree_WhenBalancing_ThenTreeIsBalanced:
- Tests various unbalanced insertion orders to ensure the tree self-balances correctly.

- givenTree_WhenRemovingRootWithTwoChildren_ThenTreeIsBalanced:
- Verifies that removing the root node with two children results in a balanced tree.

### Time Complexity:

Insertion, Removal, and Search: O(log n).

### Traversal: O(n)

### Space Complexity: O(n)


### sources

I was lost in the whole trees implementation, so thanks to teacher Remco and ChatGPT the class was made(for 90% of the methods).


## My HashMap
Class Overview:

The [MyHashMap](../src/nl/saxion/cds/solution/MyHashMap.java) class implements a hash map data structure, which provides efficient key-value storage and retrieval. It uses a hash function to map keys to indices within an array of buckets.

### Key Features:

Hash Function: A simple hash function is used to calculate the initial index for a given key.

Linear Probing: When a collision occurs, the hash map probes for the next available bucket to store the key-value pair.

Load Factor: The load factor determines when the hash map should be resized to maintain efficient performance.

Resize: When the load factor exceeds a certain threshold, the hash map is resized to a larger capacity.

Deletion: Removed elements are marked as deleted to avoid unnecessary rehashing.

### Method Descriptions:
- MyHashMap():
- Initializes the hash map with a default capacity and fills the buckets with null.

- getIndex(K key):
- Computes the bucket index for a given key using its hash code.

- getBucket(K key):
- Finds and returns the bucket containing the given key or null if the key is not present.

- contains(K key):
- Checks if the map contains the specified key.

- get(K key):
- Retrieves the value associated with the given key or throws a KeyNotFoundException if the key is not found.

- add(K key, V value):
- Adds a key-value pair to the map or throws a DuplicateKeyException if the key already exists.

- resize():
- Doubles the bucket array size and rehashes all existing key-value pairs to maintain proper distribution.

- remove(K key):
- Deletes the bucket containing the specified key, marking it as deleted, or throws a KeyNotFoundException if the key does not exist.

- getKeys():
- Returns a list of all keys currently in the map, excluding deleted entries.

- isEmpty():
- Checks if the hash map contains no non-deleted buckets.

- size():
- Returns the number of key-value pairs in the hash map.

- graphViz(String name):
- Generates a GraphViz representation of the hash map's buckets and their states.

### Tests

- GivenEmptyHashMap_WhenInitializingHashMap_ThenConfirmsEmpty:
Verifies that a newly initialized hash map is empty with size 0.

- GivenHashMapWithOneEntry_WhenAddingEntry_ThenCanRetrieveValue:
Confirms that adding an entry allows retrieval of its value.

- GivenDuplicateKey_WhenAddingEntry_ThenThrowsDuplicateKeyException:
Ensures that adding a duplicate key throws a DuplicateKeyException.

- GivenHashMapWithEntries_WhenRemovingKey_ThenKeyShouldBeRemoved:
Tests that a key can be removed, and it's no longer present in the hash map.

- GivenHashMapWithEntries_WhenRemovingNonexistentKey_ThenThrowsKeyNotFoundException:
Checks that trying to remove a non-existent key throws a KeyNotFoundException.

- GivenHashMapWithEntries_WhenGettingKeys_ThenReturnsAllKeys:
Confirms that getKeys returns all keys present in the hash map.

- GivenHashMap_WhenGettingKeysAfterRemoval_ThenReturnsRemainingKeys:
Verifies that getKeys returns only remaining keys after a key is removed.

- GivenHashMap_WhenCallingGraphViz_ThenGeneratesGraphRepresentation: 
Ensures that graphViz generates a correct graph representation of the hash map.

- GivenHashMap_WhenAddingAndRemovingMultipleEntries_ThenMaintainsCorrectSize:
Checks that the hash map maintains the correct size after multiple additions and removals.

- GivenFullHashMap_WhenAddingMoreEntries_ThenTriggersResize:
Confirms that adding many entries triggers a resize of the hash map.

- GivenEmptyHashMap_WhenGettingNonExistentKey_ThenThrowsKeyNotFoundException: 
Ensures that trying to get a non-existent key throws a KeyNotFoundException.

- GivenFullHashMap_WhenAddingMoreEntries_ThenThrowsOutOfMemoryError: 
Tests the behavior when the hash map's memory capacity is exceeded.

- GivenEmptyHashMap_WhenInitializingHashMap_ConfirmsEmpty: 
Re-checks that a newly initialized integer-keyed hash map is empty.

- GivenHashMap_WhenAddingValues_ConfirmSizeAndRetrieval: 
Verifies that the hash map accurately reflects the size and can retrieve values after adding entries.

- GivenHashMapWithoutKey_WhenRemovingKey_ThenThrowsKeyNotFoundException: 
Tests that removing a non-existent key throws a KeyNotFoundException.

- GivenDeletedKey_WhenRemovingKey_ThenThrowsKeyNotFoundException: 
Confirms that removing a previously removed key throws a KeyNotFoundException.

- GivenHashMap_WhenResizingAfterDeletions_ThenDoesNotIncludeDeletedEntries: 
Ensures that resizing after deletions excludes deleted entries.

- GivenHashMap_WhenAddingEntriesBeyondLoadFactor_ThenResizesCorrectly: 
Verifies that adding entries beyond the load factor triggers correct resizing and retains all data.

- GivenDeletedKey_WhenReAddingKey_ThenKeyIsReAdded: 
Confirms that a previously deleted key can be re-added and behaves correctly.


### Time Complexity:

### Average Case: O(1)

### Space Complexity: O(n)

### Additional Notes:

The resize method is used to maintain efficient performance by increasing the capacity of the hash map as needed.


### sources

it was a hard concept to even comprehend, but even figuring out how hashing works, writing the code is a whole different story. Therefore, ChatGPT has helped for 90% of the code.

(I wrote size and isEmpty methods)

## My HashSet
The [MyHashSet](../src/nl/saxion/cds/solution/MyHashSet.java) class implements a set data structure which provides storing unique values. 

### Key Features:
- Backing Structure:
A HashSet is implemented using a HashMap, which provides the mechanisms for hashing and collision resolution.

- No Duplicate Elements:
A HashSet automatically ensures that no duplicate elements are stored by leveraging the key uniqueness property of a HashMap.

- Unordered:
The elements in a HashSet are not stored in any particular order and may appear in a different sequence than they were inserted.

### Method Descriptions:

- add(E element):
Adds the specified element to the set if it is not already present, using a HashMap with the element as the key and true as the value.

- remove(E element):
Removes the specified element from the set if it exists, returning true if the element was present and removed, otherwise false.

- contains(E element):
Checks whether the specified element exists in the set, returning true if it is present, otherwise false.

- size():
Returns the number of elements currently in the set.

- isEmpty():
Returns true if the set contains no elements, otherwise false.

- clear():
Removes all elements from the set, effectively making it empty.

- iterator():
Returns an iterator over the elements in the set, allowing traversal.

### Tests

- whenGivenEmptySet_AfterAddingElements_ConfirmsElementsAdded:
Verifies that elements can be added to the set, ensures duplicates are not counted, and confirms the correct size after adding elements.

- whenGivenDummySet_AfterRemovingElements_ConfirmsElementsRemoved:
Ensures elements can be removed from the set, confirms removal accuracy, and verifies the size after successful and unsuccessful removal attempts.

- whenGivenDummySet_AfterCheckingElementsInSet_ConfirmsElements:
Confirms that the set accurately reports whether it contains specific elements.

- whenGivenDummySet_AfterCheckingSetSize_ConfirmsElementSize:
Validates that the size of the set reflects the correct number of elements after adding and removing elements.

- whenGivenEmptySet_AfterCheckingElementsInSet_ConfirmsEmptySet:
Checks that the set is correctly identified as empty or not based on whether elements have been added or removed.

- whenGivenDummySet_AfterClearingElements_ConfirmsEmptySet:
Ensures that calling clear empties the set, resets its size to zero, and confirms no elements are contained.

### Time Complexity: average case: O(1), worst case O(n)

### Sources: Did everything myself!!!


## My MinHeap
The [MyHeap](../src/nl/saxion/cds/solution/MyHeap.java) class implements a min-heap data structure using an array-based representation. It provides operations for inserting elements (enqueue), removing the minimum element (dequeue), and peeking at the minimum element.

### Key Features:

Array-Based Implementation: The heap is represented as an array.

Min-Heap Property: The parent node is always less than or equal to its children, ensuring that the minimum element is always at the root.

Percolation: The percolateUp and percolateDown methods are used to maintain the heap property after insertion and removal operations.

Graph Visualization: The graphViz method generates a graphviz prompt format string to visualize the heap's structure.

### Method Descriptions:

- enqueue(V value):
Adds a value to the heap and ensures the heap property is maintained by percolating up.

- dequeue():
Removes and returns the smallest value from the heap, reordering the heap to maintain its property by percolating down.

- peek():
Returns the smallest value in the heap without removing it.

- isEmpty():
Checks whether the heap contains any elements.

- size():
Returns the number of elements in the heap.

- graphViz(String name):
Generates a GraphViz-compatible representation of the heap for visualization.

- getLeftChildIndex(int parentIndex):
Calculates the index of the left child for a given parent index or returns a marker for a leaf node if the index is out of bounds.

- getRightChildIndex(int parentIndex):
Calculates the index of the right child for a given parent index or returns a marker for a leaf node if the index is out of bounds.

- getParentIndex(int childIndex):
Calculates the index of the parent for a given child index.

- percolateUp(int index):
Moves a node up the heap to maintain the heap property after insertion.

- percolateDown(int index):
Moves a node down the heap to maintain the heap property after removal.

- toString():
Returns a string representation of the heap's contents.


### Tests

- givenNewHeap_WhenCheckingSize_ConfirmEmpty:
Verifies that a newly created heap is empty and its size is zero.

- givenEmptyHeap_WhenTryingEnqueuingDequeuing_ConfirmThrowsException:
Ensures that dequeuing or peeking from an empty heap throws an EmptyCollectionException.

- givenNewHeap_WhenAddingTheExampleData_ConfirmSize:
Tests adding elements to the heap, verifying the size and structure match expectations.

- givenNewExampleHeap_WhenRemoval_ConfirmSizeAndStructure:
Ensures the heap correctly maintains its size and order when repeatedly removing elements.

- givenHeapWithElements_WhenPeeking_ConfirmTopElement: 
Verifies that peeking always returns the smallest element, even after removing the top.

- givenHeapWithDescendingOrder_WhenAddingAndRemoving_ConfirmCorrectOrder:
Confirms that the heap correctly sorts elements added in descending order and removes them in ascending order.

- givenHeapWithChildrenOnlyLeftOrRight_WhenRemoving_ConfirmCorrectStructure:
Ensures the heap maintains the correct structure and order when nodes have only left or right children.

- givenHeap_WhenAddingElements_ThenChecksGraphViz:
Verifies that the graphViz method correctly represents the heap's structure in GraphViz format.

### Time Complexity:

### Enqueue and Dequeue: O(log n).

### Peek and isEmpty: O(1)

### Space Complexity: O(n)

### Additional Notes:

The percolateUp and percolateDown methods are essential for maintaining the heap property.

### sources

the data structure doesn't seem that hard, since it is a lot like queue. However, percolate methods were done only thanks to teacher Remco.


## My Stack
Class Overview:

The [MyStack](../src/nl/saxion/cds/solution/MyStack.java) class implements a stack data structure using an array-based implementation. It provides operations for pushing elements onto the stack, popping elements from the stack, and peeking at the top element.

### Key Features:

Array-Based Implementation: The stack is represented as an array, where the top of the stack is the last element in the array.

Dynamic Resizing: The array is dynamically resized when it becomes full to accommodate more elements.

Last-In-First-Out (LIFO) Order: Elements are added and removed from the top of the stack, following the LIFO principle.

### Method Descriptions:

- public void push(V value):
adds a value to the stack

- public V pop():
removes the element from the stack. if the stack is empty, throws exception.

- public V peek():
returns the top element of the stack.

- public boolean isEmpty():
checks if the stack is empty

- public int size():
returns the size of the stack

- public String graphViz:
Generates a GraphViz representation of the stack

- private void increaseSize():
if there are more elements than the stack size, it automatically increases twice the size.

### Tests

- givenEmptyList_WhenPushingElements_ConfirmElementIsAdded:
Verifies that when an element is pushed onto an empty stack, it becomes the top element.

- givenList_WhenPushingMultipleElements_ConfirmOrder:
Confirms that elements pushed onto the stack are popped in reverse order (LIFO).

- givenList_WhenPoppingElements_ConfirmElementIsRemoved:
Ensures that when popping from a stack, the top element is removed, and an exception is thrown when the stack is empty.

- givenList_WhenPoppingAllElements_ConfirmStackIsEmpty: 
Verifies that the stack becomes empty after all elements are popped and an exception is thrown on further pop attempts.

- givenList_WhenPeeking_ConfirmTopElementWithoutRemoving:
Confirms that peeking returns the top element without removing it, and the stack size remains unchanged.

- givenEmptyList_WhenCheckingIfListIsEmpty_ConfirmListIsEmpty:
Ensures that the stack correctly identifies whether it's empty before and after elements are added.

- givenList_WhenExceedingInitialCapacity_ConfirmSizeIncreases:
Verifies that the stack dynamically increases its size when the initial capacity is exceeded.



### Time Complexity:

### Push, Pop, Peek, isEmpty, size: O(1).

### Resize: O(n).

### Space Complexity: O(n)

### Additional Notes:

The increaseSize method is used to double the capacity of the underlying array when it becomes full.

### sources

I am happy to announce that at least this class was made by me.


## My Queue
The [MyQueue](../src/nl/saxion/cds/solution/MyQueue.java) class implements a queue data structure using a doubly linked list. It provides operations for adding elements to the rear of the queue (enqueue), removing elements from the front of the queue (dequeue), and peeking at the front element.

### Key Features:

Doubly Linked List Implementation: The queue is implemented using a doubly linked list, where each node stores a value and references to the previous and next nodes.

First-In-First-Out (FIFO) Order: Elements are added to the rear of the queue and removed from the front, following the FIFO principle.

### Method Descriptions:

- enqueue(V value):
Adds a value to the end of the queue by appending it to the doubly linked list.

- dequeue(): 
Removes and returns the front value of the queue, throwing an exception if the queue is empty.

- peek(): 
Returns the front value of the queue without removing it, throwing an exception if the queue is empty.

- isEmpty(): 
Checks if the queue is empty by checking if the underlying list is empty.

- size():
Returns the number of elements currently in the queue.

- graphViz(String name):
Generates a Graphviz-compatible string representation of the queue's elements and their connections.


### Tests

- givenEmptyList_WhenEnqueuingElements_ConfirmElementIsAdded:
Verifies that when elements are enqueued into an empty queue, the front element remains the first one added.

- givenEmptyQueue_WhenDequeuingOrPeeking_ThrowException:
Ensures that attempting to dequeue or peek on an empty queue throws an EmptyCollectionException.

- givenQueueWithElements_WhenGeneratingGraphViz_ConfirmOutputIsCorrect:
Confirms that the correct Graphviz representation is generated for a queue with multiple enqueued elements.

- givenDummyList_WhenDequeuingElements_ConfirmElementIsRemoved:
Validates that dequeuing an element removes it from the front of the queue and the next element becomes the front.

- givenEmptyList_WhenCheckingIfListIsEmpty_ConfirmListIsEmpty:
Verifies that an empty queue is correctly identified as empty.

- givenDummyList_WhenCheckingSize_ReturnsListSize: 
Checks that the size method returns the correct number of elements in the queue after enqueuing multiple elements.

### Time Complexity:

### Enqueue, Dequeue, Peek, isEmpty, size: O(1).

### Space Complexity: O(n)

### Additional Notes:

Using a doubly linked list for the queue implementation allows for efficient insertion and removal operations at both ends.

### sources

this one also was one of the easiest ones, so I made it myself.


## My priority Queue


A [MyQueue](../src/nl/saxion/cds/solution/MyPriorityQueue.java) is preferred over a normal queue because I need to process elements based on their priority rather than the order in which they were added

Dijkstra's algorithm: Used in graph traversal algorithms where nodes with the lowest cost are processed first.

### Method description

- enqueue(V value):
Adds a new element to the priority queue and sorts the elements based on the provided comparator to maintain the order.

- dequeue():
Removes and returns the highest priority element (the first element in the sorted list), throwing an EmptyCollectionException if the queue is empty.

- peek(): 
Returns the highest priority element without removing it, throwing an EmptyCollectionException if the queue is empty.

- contains(V v): 
Checks if a specific element exists in the priority queue.

- isEmpty():
Returns true if the priority queue is empty, otherwise false.

- size():
Returns the number of elements currently in the priority queue.

- graphViz(String name): 
Generates a Graphviz string representation of the priority queue, showing the elements and their relationships.

### Tests

- GivenQueue_WhenEnquingElements_ConfirmElementsAdded:
Verifies that elements are correctly added to the priority queue and the queue maintains the correct size and top element.

- GivenQueue_WhenDequingElements_ConfirmElementRemoved: 
Ensures that the correct element is removed from the priority queue when dequeuing and the size and top element are updated.

- GivenQueue_WhenPeekingElement_ReturnsTopElement:
Confirms that the peek method returns the top element of the priority queue without removing it, while the queue size remains the same.

- GivenQueue_WhenCheckingIsEmpty_ConfirmFalse: 
Verifies that the isEmpty method correctly identifies an empty queue and returns false after adding an element.

- GivenQueue_WhenCheckingSize_ReturnsSize:
Ensures that the size method returns the correct number of elements in the priority queue.

- GivenQueue_WhenCheckingIfContains_ConfirmsElementExists:
Tests that the contains method correctly checks if an element is present in the queue.

- GivenEmptyQueue_WhenDequingElement_ThrowsException: 
Ensures that an EmptyCollectionException is thrown when trying to dequeue from an empty queue.

- GivenEmptyQueue_WhenPeekingElement_ThrowsException: 
Verifies that an EmptyCollectionException is thrown when trying to peek from an empty queue.

### Time Complexity:

### Dequeue, Peek, isEmpty, size: O(1).
### Enqueue: O(nlogn) due to quicksort
### Contains: O(n)

### Sources:
Made by me


## My Graph

The [MyGraph](../src/nl/saxion/cds/solution/MyGraph.java) class represents a directed graph data structure. It provides methods for adding nodes and edges, retrieving edges, calculating the total weight of edges, finding shortest paths using Dijkstra's algorithm and A*, finding the minimum spanning tree, and more.


### My iterative depth first search algorithms

### Code Breakdown:

- addEdge(V sourceNode, V destinationNode, double edgeWeight): 
Adds a directed edge with the given weight between the specified source and destination nodes.

- addEdgeBidirectional(V sourceNode, V destinationNode, double edgeWeight):
Adds two directed edges: one from sourceNode to destinationNode and another from destinationNode to sourceNode, both with the given weight.

- getEdges(V nodeValue): 
Retrieves a list of outgoing edges from the specified node.

- getTotalWeight():
Calculates and returns the sum of weights of all edges in the graph.

- shortestPathsDijkstra(V startingNode):
Implements Dijkstra's algorithm to find the shortest paths from the given starting node to all other reachable nodes.

- shortestPathAStar(V startNode, V endNode, Estimator<V> heuristic):
Implements the A* search algorithm to find the shortest path between the start and end nodes, using the provided heuristic function.

- minimumCostSpanningTree():
Implements Kruskal's algorithm to find the minimum spanning tree of the graph.

- iterator():
Returns an iterator that traverses the nodes of the graph in breadth-first order.

- isEmpty():
Checks if the graph is empty (contains no nodes).

- size(): 
Returns the number of nodes in the graph.

- graphViz(String graphName):
Generates a graphViz representation of the graph.

### My iterative breadth first search algorithm
In a breadth-first search (BFS), nodes at each level are explored before progressing to the next depth. This approach guarantees an effective traversal of all reachable nodes from the starting point, systematically examining all immediate connections at each level.

The BreadthFirstIterator offers an iterator designed for traversing nodes in a breadth-first order, making it ideal for applications that need to explore nodes step-by-step through each level.
Why BFS is preferred over DFS:

- Both BFS and DFS can be used to find paths, but with DFS, there's a possibility of exploring paths that are either irrelevant or unnecessarily long before backtracking, which doesn't align well with the priority of finding direct and efficient routes in a railroad system.
- Rail networks typically feature multiple nearby connections (stations just one or two stops away) rather than deep, nested connections. BFS, by exploring stations level by level, first visits stations within one stop, then those within two stops, which aligns with how transportation systems prioritize stations that are closer to the starting point.

### Time complexity: (O(V + E)), where V is the number of vertices and E is the number of edges.


### My Dijkstra algorithm

The shortestPathsDijkstra method implements Dijkstra's algorithm to find the shortest paths from a specified starting node to all other reachable nodes in a graph. It starts by checking if the starting node is valid, then initializes a priority queue to explore edges based on the smallest cumulative weight. As it processes each node, the algorithm keeps track of visited nodes to avoid revisiting and adds the shortest path edges to a new graph. The method returns a graph representing the shortest paths from the starting node.

Implementation: The shortestPathsDijkstra method implements Dijkstra's algorithm to compute the shortest paths from a given starting node to all reachable nodes in the graph. It uses a priority queue to systematically explore edges with the smallest weights.

### Time complexity: O((V + E) log V), where V is the number of vertices and E is the number of edges.

### My A* algorithm

The shortestPathAStar method implements the A* search algorithm to find the shortest path from a starting node to a target node using a heuristic to estimate the remaining cost to the target. It uses a priority queue (openSet) to explore nodes with the lowest combined cost (gCost + hCost) and a hash map (closedSet) to track visited nodes. As it explores each node, it updates the cost and the path, and when the target node is reached, it reconstructs the shortest path by following the previousNode references. If no path is found, it returns an empty list.

### Time complexity: O((V + E) log V), where V is the number of nodes, E is the number of edges, and log V accounts for the priority queue operations

### My MCST algorithm  

The minimumCostSpanningTree method implements Prim's algorithm to find the Minimum Spanning Tree (MST) of a graph. It starts by selecting an arbitrary initial node, adding it to the visited set, and adding its edges to a priority queue. The algorithm then repeatedly extracts the edge with the smallest weight from the priority queue, adds it to the MST if the target node is unvisited, and adds its edges to the priority queue. This continues until all nodes are included in the MST, and the final graph represents the minimum cost spanning tree.

### Time complexity: O(E log V) where E is the number of edges in the graph, V is the number of vertices in the graph.


### Graph Tests

- givenGraph_whenAddingEdge_confirmEdgeIsStoredCorrectly: 
Verifies that when an edge is added between two nodes, the edge is correctly stored with the expected destination and weight.

- givenGraph_whenAddingBidirectionalEdge_confirmBothEdgesExist: 
Ensures that when a bidirectional edge is added, both directions are correctly represented in the graph.

- givenGraph_whenRetrievingEdgesForUnknownNode_confirmExceptionThrown:
Confirms that an exception is thrown when trying to retrieve edges for a node that does not exist in the graph.

- givenGraphWithEdges_whenCalculatingTotalWeight_confirmCorrectSum: 
Checks that the total weight of all edges in the graph is calculated correctly.

- givenGraphWithVariousEdges_whenMakingMST_confirmCorrectStartNodeUsed:
Verifies that the minimum spanning tree (MST) is correctly generated and the starting node is handled properly.

- givenGraphWithDisconnectedNode_whenMakingMST_confirmNodeNotIncluded:
Ensures that disconnected nodes are not included in the MST.

- givenGraphWithSameWeightEdges_whenMakingMST_confirmMinimumWeight: 
Confirms that when multiple edges have the same weight, the MST selects the correct edges with the minimum weight.

- GivenGraphWithDifferentWeights_WhenCalculatingMST_ConfirmMinimumEdgesSelected: 
Verifies that the MST selects the edges with the minimum weight and the graph has the expected total weight.

- givenEmptyGraph_whenNextCalled_thenThrowIllegalStateException: 
Ensures that calling next() on an empty graph iterator throws an IllegalStateException.

- givenGraphWithNodeHavingNullEdge_WhenReconstructingPath_ConfirmEdgeHandling: 
Verifies that when reconstructing a path with a null edge, it handles it properly by returning an empty path.

- givenEmptyGraph_WhenCalculatingMST_ConfirmEmptyMST: 
Confirms that the MST of an empty graph is also empty.

- givenGraph_whenGeneratingGraphVizOutput_confirmDotFormatCorrect: 
Ensures that the GraphViz output is correctly formatted and includes the expected graph structure and edge labels.


###  A* Tests


- givenGraphWithValidPath_WhenUsingAStar_ConfirmShortestPathFound:
Tests that A* finds the correct shortest path from node "A" to "D" based on the given edge weights and heuristic estimates.

- givenGraphWithNoPath_WhenUsingAStar_ConfirmNoPathReturned:
Verifies that A* returns no path when there is no valid connection between nodes "A" and "E".

- givenGraphWithEqualWeightEdges_WhenUsingAStar_ConfirmShortestPathWithMinimumEdges: 
Confirms that A* selects the shortest path with the fewest edges when all edge weights are equal.

- givenGraphWithComplexWeights_WhenUsingAStar_ConfirmCorrectPath:
Ensures that A* correctly finds the path considering complex edge weights and heuristic estimates between nodes "A" and "E".

- givenGraphWithLoop_WhenUsingAStar_ConfirmLoopIsNotPartOfPath:
Validates that A* avoids cycles in the graph and doesn't include the loop when finding the shortest path from "A" to "D".

- givenNullEdge_WhenGetNodeCalled_ReturnNull: 
Checks that when a node has a null edge, calling getCurrentNode() returns null.


### Dijkstra Tests

- givenGraphWithDisconnectedNode_whenRunningDijkstra_confirmNodeNotInResults: 
Verifies that Dijkstra throws a KeyNotFoundException when trying to access nodes "E" and "F" that are disconnected from the source node "A".

- givenGraphWithUnknownStartNode_whenCalculatingShortestPaths_confirmGraphIsEmpty: 
Ensures that Dijkstra returns an empty graph when an unknown start node ("X") is provided.

- givenGraphWithoutPaths_whenAddingEdges_confirmDijkstraCalculatesCorrectPaths: 
Confirms that Dijkstra correctly calculates the shortest paths and their respective edge weights from node "A" to other nodes in the graph.

# Technical Design of My Application
![img.png](../doc/technicalDesign.png)

### explanation:

### Application

The Application class provides an interface for interacting with a train station management system. It presents a menu of options that allow the user to perform various tasks such as looking up station information by code or name, finding stations by type, finding the shortest route between two stations, displaying a minimum cost spanning tree for the rail network, and showing the network graphically

### TrainStationManager


The TrainStationManager class serves as the core controller for managing train station data and operations in the system. It provides methods to query and manipulate station information, such as looking up stations by code or name, filtering stations by type, calculating the shortest route between stations, and computing the minimum cost spanning tree (MCST) for the rail network.


- readCSVData(): 
Reads and loads data from a CSV file to populate the stations and tracks.

- buildGraph(): 
Constructs a graph representation of the railroad network based on the stations and tracks.

- getStationByCode(String code): 
Retrieves a station by its unique station code.

- showStationByCode(String stationCode): 
Displays information about a station based on its code.

- showStationByName(String stationName):
Displays information about a station based on its name.

- showStationTypes():
Lists all types of stations available in the system.

- showStationByType(String type): 
Shows stations filtered by a specific type.

- findShortestRoute(String start, String end): 
Finds and displays the shortest route between two stations.

- calculateMinimumRailConnections(): 
Calculates and displays the minimum cost spanning tree (MCST) for the railroad network.

- showStationInfo(Stations station): 
Displays detailed information about a given station.

### CSVReader

The CSVReader class is responsible for reading station and track data from CSV files and converting them into appropriate objects (Stations and Tracks). These objects are then returned as lists (MyArrayList

- readStations(String file):
This method reads station data from a CSV file, parses each line into station attributes (like code, name, type, coordinates), and returns a list of Stations objects.

- readTracks(String file):
This method reads track data from a CSV file, parses each line into track attributes (such as origin, destination, cost, and distance), and returns a list of Tracks objects.


### Tracks and Station reading

reads station and track data from CSV files into two `MyArrayList` objects (for stations and tracks) and constructs a graph representation using `MyGraph`.

- chose:
  MyArrayList was selected because it allows dynamic resizing, which is important when dealing with an unpredictable number of stations and tracks. Although resizing requires O(n) time for each operation, the resizing occurs infrequently as the array expands, making most insertions run in O(1) time. This approach is efficient for handling large datasets. Once the data is loaded, MyArrayList offers constant-time (O(1)) access, which is vital for quickly iterating through stations and tracks while constructing the graph. Additionally, MyArrayList preserves the order of insertion, which simplifies debugging and helps maintain the original sequence of stations and tracks from the CSV file.


- Alternative:
  MyHashMap, where the station code serves as the key and the Station object as the value. This would enhance lookup times to O(1) when searching for stations by their code, which could be useful during the graph construction. However, since station lookups are infrequent after the graph is built, the added complexity and memory overhead of a hash map might not be warranted. On the other hand, MyArrayList offers a more straightforward solution, maintains the insertion order, and can be advantageous for debugging and traceability purposes.

# Station search by station code

MyArrayList is used to store all the stations, and the method getStationByCode iterates through the list to find a station by its code.

- chose:
  MyArrayList is chosen because it is simple to implement and provides an easy way to store and iterate through the stations. Although searching through the list has a time complexity of O(n), it works well for cases where the number of stations is not very large.


- Alternative:
  A MyAVLTree could also be used, which would provide O(log n) search time but would require the station codes to be unique and sorted.



# Station search based on the beginning of the name

MyArrayList is used to store the stations, and the getStationByCode method searches for a station by iterating over the list and comparing the station code.

- chose:
  MyArrayList was chosen because it is simple to use and provides straightforward iteration through the stations. For the task of searching by code, it works adequately when the number of stations is relatively small. Since MyArrayList offers O(n) lookup time, it is acceptable for cases where search operations are not the primary performance concern. Additionally, it maintains the order of insertion, which can be helpful in debugging or tracking the original order of stations.


- Alternative: 
A HashMap could be a better alternative for station code-based search, where the key is the station code and the value is the station object. This would provide O(1) average lookup time.

## Implementation shortest route

A* algorithm was chosen to find the shortest route between two stations. It uses a heuristic function to guide the search and optimize the pathfinding


- chose:
A* was selected because it is more efficient than Dijkstra when there is a good heuristic that can help estimate the distance between the current station and the destination. A* prioritizes nodes that are likely to lead to the goal, making it more efficient than Dijkstra, which blindly explores all possible paths.

- Alternative:
Dijkstra’s algorithm could have been used as an alternative. It guarantees finding the shortest path in a weighted graph without relying on a heuristic. However, without a heuristic, Dijkstra would explore more paths, potentially making it slower than A* in scenarios where a good heuristic is available.


## Implementation minimum cost spanning tree 

The Minimum Cost Spanning Tree (MST) is implemented using a graph-based approach where only the stations in the Netherlands are considered. After filtering the stations, a MyGraph is used to represent the graph, and the edges (tracks) are added to the graph. The MST is then computed using a method for finding the minimum spanning tree.

- chose: 
Prim's algorithm. The primary goal is to find the minimum number of rail connections and the total length of the connections needed to connect all stations in the Netherlands. By using the MST algorithm, we can ensure that the total distance of the rail connections is minimized, making it cost-effective.


- Alternative:
An alternative approach could involve using a different MST algorithm, such as Kruskal's algorithm, which could be used instead of a graph-based approach. Kruskal's algorithm works by sorting all edges and adding them to the tree while avoiding cycles.


## Implementation graphic representation(s)
non-existent
