/**
 * Describes race condition example with executors
 * @author Sup4eg
 */
package part2;


import service.ExecutorServiceMethods;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class RaceExecutorExample {

    static int count = 0;

    public static synchronized void increment() {
        count = count + 1;
    }

    public static void main(String[] args) {
        java.util.concurrent.ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10_000)
                .forEach(i -> executor.submit(RaceExecutorExample::increment));

        ExecutorServiceMethods.stop(executor);
        System.out.println(count);
    }
}
