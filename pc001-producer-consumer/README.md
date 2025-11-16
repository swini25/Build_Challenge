
# Producer–Consumer Multithreading Challenge

A Java implementation of the classic **Producer–Consumer** problem using:

* A **thread-safe bounded queue**
* `wait()` / `notifyAll()`
* **Poison pill termination**
* **Multiple producers**
* **JUnit test coverage**
* Console output for analysis

This project satisfies all challenge deliverables.

---

## 📁 Project Structure

```
pc001-producer-consumer
│
├── src
│   ├── main/java/com/pc001
│   │   ├── Producer.java
│   │   ├── Consumer.java
│   │   ├── SharedQueue.java
│   │   └── Main.java
│   │
│   └── test/java/com/pc001
│       └── ProducerConsumerTest.java
│
├── pom.xml
└── README.md
```

---

# Features

### ✔ Thread-Safe Bounded Queue

Implements producer–consumer synchronization using `synchronized`, `wait()`, and `notifyAll()`.

### ✔ Producer & Consumer Threads

Producers push items, consumers retrieve them.

### ✔ Poison-Pill Shutdown

Uses **-1** to signal the consumer to stop safely.

### ✔ Multi-Producer Support

Multiple producers writing concurrently.

### ✔ Full Unit Test Suite

Test cases validate:

* Normal data transfer
* Blocking behavior
* Poison-pill termination
* Empty source list
* Queue size = 1 stress case
* Multiple producers
* Consumer waiting on empty queue

All tests print **readable console logs**.

---

# Setup Instructions

### **Requirements**

* Java 17 or higher
* Maven 3.8+
* Git

---

### **Clone the Repository**

```sh
git clone https://github.com/swini25/Build_Challenge
cd pc001-producer-consumer
```

---

### **Build the Project**

```sh
mvn clean install
```

---

### **Run the Application**

```sh
mvn exec:java -Dexec.mainClass="com.pc001.Main"
```

Sample output:

```
Produced: 1
Consumed: 1
Produced: 2
Consumed: 2
Produced: 3
Consumed: 3
Produced: 4
Produced: 5
Consumed: 4
Consumed: 5
Final destination: [1, 2, 3, 4, 5]
```

---

# Running Tests

To execute all unit tests:

```sh
mvn test
```

The tests display clear headers and activity logs.

---

# Sample Test Output (All Analyses)

Below are example outputs captured when running the full test suite.

---

## ✔ testProducerConsumerTransfer()

```
=======================================
 Starting Test: testProducerConsumerTransfer()
=======================================

Produced: 1
Produced: 2
Consumed: 1
Produced: 3
Consumed: 2
Consumed: 3
Produced: 4
Produced: 5
Consumed: 4
Consumed: 5
✔ testProducerConsumerTransfer passed
```

---

## ✔ testQueueBlockingBehavior()

```
=======================================
 Starting Test: testQueueBlockingBehavior()
=======================================

Produced: 1
Consumed: 1
Produced: 2
Produced: 3
Consumed: 2
Consumed: 3
Produced: 4
Consumed: 4
Produced: 5
Produced: 6
Consumed: 5
Produced: 7
Consumed: 6
Consumed: 7
Produced: 8
Produced: 9
Consumed: 8
Produced: 10
Consumed: 9
Consumed: 10
✔ testQueueBlockingBehavior passed
```

---

## ✔ testConsumerStopsAtPoisonPill()

```
=======================================
 Starting Test: testConsumerStopsAtPoisonPill()
=======================================

Produced: 1
Produced: 2
Consumed: 1
Produced: 3
Consumed: 2
Consumed: 3
✔ testConsumerStopsAtPoisonPill passed
```

---

## ✔ testEmptySourceList()

```
=======================================
 Starting Test: testEmptySourceList()
=======================================

✔ testEmptySourceList passed
```

---

## ✔ testQueueSizeOne()

```
=======================================
 Starting Test: testQueueSizeOne()
=======================================

Produced: 10
Consumed: 10
Produced: 20
Consumed: 20
Produced: 30
Produced: 40
Consumed: 30
Consumed: 40
✔ testQueueSizeOne passed
```

---

## ✔ testConsumerWaitsOnEmptyQueue()

```
=======================================
 Starting Test: testConsumerWaitsOnEmptyQueue()
=======================================

Produced: 100
Consumed: 100
Produced: 200
Consumed: 200
Produced: 300
Consumed: 300
✔ testConsumerWaitsOnEmptyQueue passed
```

---

# Summary

This project successfully demonstrates:

* Thread synchronization
* Bounded queue implementation
* Multiple producers / single consumer behavior
* Support for poison-pill shutdown 
* Blocking and unblocking analysis
* Fully passing JUnit test suite
* Clean console logging

All challenge **deliverables** are fulfilled.

---

