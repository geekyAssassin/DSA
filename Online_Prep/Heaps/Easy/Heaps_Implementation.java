package Heaps.Easy;

import java.util.ArrayList;

class Heaps {
    ArrayList<Integer> heap = new ArrayList<>();

    // Insert a new value into the heap and maintain the max-heap property
    void insert(int val) {
        heap.add(val); // Add value at the end
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            // If child is greater than parent, swap them
            if (heap.get(index) > heap.get(parentIndex)) {
                int temp = heap.get(index);
                heap.set(index, heap.get(parentIndex));
                heap.set(parentIndex, temp);
            }
            index = parentIndex; // Move up to parent
        }
    }

    // Heapify the subtree rooted at index to maintain max-heap property
    void heapify(int index) {
        int leftChildIndex = 2 * index + 1; // Left child index
        int rightChildIndex = 2 * index + 2; // Right child index

        // If left child exists and is greater than current node, swap and heapify left
        if (leftChildIndex < heap.size() && heap.get(leftChildIndex) > heap.get(index)) {
            int temp = heap.get(index);
            heap.set(index, heap.get(leftChildIndex));
            heap.set(leftChildIndex, temp);
            heapify(leftChildIndex);
        }

        // If right child exists and is greater than current node, swap and heapify
        // right
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(index)) {
            int temp = heap.get(index);
            heap.set(index, heap.get(rightChildIndex));
            heap.set(rightChildIndex, temp);
            heapify(rightChildIndex);
        }
    }

    // Delete the element at the given index from the heap
    void delete(int index) {
        if (heap.isEmpty()) {
            System.out.println("Heap is empty");
            return;
        }

        int lastIndex = heap.size() - 1;
        // Swap the element to be deleted with the last element
        int temp = heap.get(index);
        heap.set(index, heap.get(lastIndex));
        heap.set(lastIndex, temp);
        heap.remove(lastIndex); // Remove the last element
        heapify(index); // Heapify at the index to restore heap property
    }
}

public class Heaps_Implementation {

    // Difficult example demonstrating heap operations:
    // Insert the following sequence: 10, 40, 20, 60, 50, 30, 70
    // After all insertions, the heap should be a valid max-heap.
    // Then delete the root (index 0), and observe the heap property restoration.
    //
    // Example usage:
    // Heaps h = new Heaps();
    // h.insert(10);
    // h.insert(40);
    // h.insert(20);
    // h.insert(60);
    // h.insert(50);
    // h.insert(30);
    // h.insert(70);
    // // Heap now: [70, 50, 60, 10, 40, 20, 30]
    // h.delete(0);
    // // Heap after deleting root: [60, 50, 30, 10, 40, 20]
    //
    // You can print the heap after each operation to verify correctness.
    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            public void run() {
                solve();
            }
        }, "Main", 1 << 29).start();
    }

    public static void solve() {
        Heaps h = new Heaps();
        h.insert(10);
        h.insert(40);
        h.insert(20);
        h.insert(60);
        h.insert(50);
        h.insert(30);
        h.insert(70);

        // Print the heap after insertions
        System.out.println("Heap after insertions: " + h.heap);

        // Delete the root of the heap
        h.delete(0);

        // Print the heap after deletion
        System.out.println("Heap after deleting root: " + h.heap);
    }
}
