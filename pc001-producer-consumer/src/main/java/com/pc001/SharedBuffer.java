package com.pc001;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Thread-safe blocking queue implementation using wait/notify.
 * Producers block when the queue is full, and consumers block
 * when the queue is empty.
 */
class SharedQueue {

    // FIFO queue for storing items
    private final Deque<Integer> queue = new ArrayDeque<>();

    // Maximum items allowed in the queue at a time
    private final int maxSize;

    public SharedQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Adds an item to the queue.
     * Blocks if queue has reached max capacity.
     */
    public synchronized void put(Integer item) throws InterruptedException {
        while (queue.size() >= maxSize) {
            wait(); // Wait until space becomes available
        }
        queue.add(item); // Insert at the end of queue
        notifyAll(); // Wake up waiting consumers
    }

    /**
     * Retrieves and removes the next item from the queue.
     * Blocks if queue is empty until a producer adds an item.
     */
    public synchronized Integer get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait until an item is available
        }
        Integer item = queue.removeFirst(); // Remove from the front
        notifyAll(); // Wake up waiting producers
        return item;
    }
}
