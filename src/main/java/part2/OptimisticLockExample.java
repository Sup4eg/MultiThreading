/**
 * Describes Optimistic lock
 * @author Sup4eg
 */
package part2;

import service.ExecutorServiceMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class OptimisticLockExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                ExecutorServiceMethods.sleep(1);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                ExecutorServiceMethods.sleep(2);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            } finally {
                lock.unlock(stamp);
            }
        });
        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write lock acquired");
                ExecutorServiceMethods.sleep(2);
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });
        ExecutorServiceMethods.stop(executor);
    }
}
