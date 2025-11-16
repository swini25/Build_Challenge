package com.pc001;

import java.util.List;

/**
 * Consumer thread that retrieves items from the shared queue
 * and stores them into a destination container. 
 * Stops when it receives the termination signal (-1).
 */
class Consumer extends Thread {
    private final SharedQueue queue;
    private final List<Integer> destination;

    public Consumer(SharedQueue queue, List<Integer> destination) {
        this.queue = queue;
        this.destination = destination;
    }

    public void run() {
        try {
            while (true) {
                // Retrieve next item (blocks if queue is empty)
                Integer item = queue.get();

                // Termination signal detected
                if (item == -1) {
                    queue.put(-1); // Pass termination to any other consumer
                    break;
                }
                Thread.sleep(10); // Simulate processing delay
                destination.add(item);
                System.out.println("Consumed: " + item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}