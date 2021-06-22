package part1;

import service.ExecutorServiceMethods;

import java.util.concurrent.Executors;

/**
 * Describes executors
 * @author Sup4eg
 */

public class Executor {
    public static void main(String[] args) {
        java.util.concurrent.ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
           String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        ExecutorServiceMethods.stop(executor);
    }
}
