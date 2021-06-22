/**
 * Describes long adder in java
 * @author Sup4eg
 */
package part3;

import service.ExecutorServiceMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class LongAdderExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        LongAdder adder = new LongAdder();
        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(adder::increment));
        ExecutorServiceMethods.stop(executor);
        System.out.println(adder.sumThenReset());

    }
}
