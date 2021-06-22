/**
 * Describes atomic integer update and get in java
 * @author Sup4eg
 */
package part3;

import service.ExecutorServiceMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicIntegerUpdateAndGet {
    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);
        Runnable task = () -> atomicInt.updateAndGet(n -> n + 2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 1000).forEach(i -> executor.submit(task));
        ExecutorServiceMethods.stop(executor);
        System.out.println(atomicInt.get());
    }
}
