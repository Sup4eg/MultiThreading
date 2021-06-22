/**
 * Describes Reentrant locks in java
 * @author Sup4eg
 */
package part2;

import service.ExecutorServiceMethods;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockExample {

    static ReentrantLock lock = new ReentrantLock();
    static int count = 0;

    public static void increment() {
        lock.lock();
        try {
            count = count + 1;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        java.util.concurrent.ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10_000)
                .forEach(i -> executor.submit(ReentrantLockExample::increment));
        System.out.println(count);

        System.out.println("-".repeat(30));

        ReentrantLock lock = new ReentrantLock();
        executor.submit(() -> {
            lock.lock();
            try {
                ExecutorServiceMethods.sleep(1);
                System.out.println("Hi!");
            } finally {
                lock.unlock();
            }
        });
        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });
        ExecutorServiceMethods.stop(executor);
    }
}