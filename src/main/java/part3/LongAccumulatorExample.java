/**
 * Describes long accumulator example
 * @author Sup4eg
 */
package part3;

import service.ExecutorServiceMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

public class LongAccumulatorExample {

    public static void main(String[] args) {
        LongBinaryOperator op = (x, y) -> 2 * x + y;
        LongAccumulator accumulator = new LongAccumulator(op, 1L);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10).forEach(i -> executor.submit(() -> accumulator.accumulate(i)));
        ExecutorServiceMethods.stop(executor);
        System.out.println(accumulator.getThenReset());
    }
}
