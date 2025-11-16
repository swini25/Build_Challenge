package com.pc001;

import java.util.*;


/**
 * Producer thread that reads items from a source container
 * and places them into the shared queue. 
 * Sends a termination signal (-1) after producing all items.
 */
class Producer extends Thread {
    private final List<Integer> source;
    private final SharedQueue queue;

    public Producer(List<Integer> source, SharedQueue queue) {
        this.source = source;
        this.queue = queue;
    }

    public void run() {
        try {
            for (Integer item : source) {
                Thread.sleep(10); // Simulate production delay
                queue.put(item);  // Blocks if the queue is full
                System.out.println("Produced: " + item);
            }
            queue.put(-1); // Send termination signal
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

