package com.pc001;

import java.util.*;

/**
 * Demonstrates the producer-consumer pattern by transferring
 * data from a source list to a destination list using threads.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Source data items to be produced
        List<Integer> source = List.of(1, 2, 3, 4, 5);

        // Destination where consumer will store processed items
        List<Integer> destination = new ArrayList<>();

        // Shared queue with a capacity of 2
        SharedQueue queue = new SharedQueue(2);

        Producer producer = new Producer(source, queue);
        Consumer consumer = new Consumer(queue, destination);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Final destination: " + destination);
    }
}
