package com.pc001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests validating the correctness and thread-safety
 * of the Producer–Consumer implementation.
 */
class ProducerConsumerTest {

    @BeforeEach
    void printHeader(TestInfo info) {
        System.out.println("\n=======================================");
        System.out.println(" Starting Test: " + info.getDisplayName());
        System.out.println("=======================================\n");
    }

    @Test
    void testProducerConsumerTransfer() throws InterruptedException {
        // Source items for the producer to generate
        List<Integer> source = List.of(1, 2, 3, 4, 5);
        List<Integer> destination = new ArrayList<>();
        SharedQueue sharedQueue = new SharedQueue(2);

        Producer producer = new Producer(source, sharedQueue);
        Consumer consumer = new Consumer(sharedQueue, destination);

        consumer.start();
        producer.start();

        producer.join();
        consumer.join();

        // Verify all items produced were successfully consumed
        assertEquals(source, destination);

        System.out.println("✔ testProducerConsumerTransfer passed\n");
    }

    @Test
    void testQueueBlockingBehavior() throws InterruptedException {
        // Large dataset to force repeated queue blocking/unblocking
        List<Integer> source = List.of(1,2,3,4,5,6,7,8,9,10);
        List<Integer> destination = new ArrayList<>();
        SharedQueue sharedQueue = new SharedQueue(3);

        Producer producer = new Producer(source, sharedQueue);
        Consumer consumer = new Consumer(sharedQueue, destination);

        consumer.start();
        producer.start();

        producer.join();
        consumer.join();

        // All items must still transfer correctly even under blocking conditions
        assertEquals(source, destination);

        System.out.println("✔ testQueueBlockingBehavior passed\n");
    }
    

    @Test
    void testConsumerStopsAtPoisonPill() throws InterruptedException {
        // Ensure consumer terminates when the poison pill (-1) is encountered
        List<Integer> source = List.of(1, 2, 3);
        List<Integer> destination = new ArrayList<>();
        SharedQueue queue = new SharedQueue(2);

        Producer producer = new Producer(source, queue);
        Consumer consumer = new Consumer(queue, destination);

        consumer.start();
        producer.start();

        producer.join(); 
        queue.put(-1);
        consumer.join();

        // Consumer should not store the poison pill and must stop correctly
        assertEquals(List.of(1, 2, 3), destination);

        System.out.println("✔ testConsumerStopsAtPoisonPill passed\n");
    }

    @Test
    void testEmptySourceList() throws InterruptedException {
        // Verify that an empty input list produces no output and still terminates properly
        List<Integer> source = List.of(); // empty
        List<Integer> destination = new ArrayList<>();
        SharedQueue queue = new SharedQueue(2);

        Producer producer = new Producer(source, queue);
        Consumer consumer = new Consumer(queue, destination);

        consumer.start();
        producer.start();

        producer.join();
        consumer.join();

        // No items should be consumed
        assertEquals(List.of(), destination);

        System.out.println("✔ testEmptySourceList passed\n");
    }

    @Test
    void testQueueSizeOne() throws InterruptedException {
        // A queue of size 1 forces maximum blocking interaction
        List<Integer> source = List.of(10, 20, 30, 40);
        List<Integer> destination = new ArrayList<>();
        SharedQueue queue = new SharedQueue(1); // forces blocking

        Producer producer = new Producer(source, queue);
        Consumer consumer = new Consumer(queue, destination);

        consumer.start();
        producer.start();

        producer.join();
        consumer.join();

        // Order must be preserved even under heavy blocking
        assertEquals(source, destination);

        System.out.println("✔ testQueueSizeOne passed\n");
    }
    
    @Test
    void testConsumerWaitsOnEmptyQueue() throws InterruptedException {
        // Ensures that the consumer correctly waits (blocks) 
        // when it starts before any items are produced
        SharedQueue queue = new SharedQueue(2);
        List<Integer> destination = new ArrayList<>();

        List<Integer> source = List.of(100, 200, 300);
        Producer producer = new Producer(source, queue);
        Consumer consumer = new Consumer(queue, destination);

        // Start consumer first so it must block on an empty queue
        consumer.start();

        Thread.sleep(50); // Ensure consumer hits wait()

        producer.start();

        producer.join();
        consumer.join();

        // All items must still transfer successfully
        assertEquals(source, destination);

        System.out.println("✔ testConsumerWaitsOnEmptyQueue passed\n");
    }



}
