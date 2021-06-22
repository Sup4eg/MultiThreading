package part1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Check invokeAny with an executor
 *
 * @author Sup4eg
 */

public class InvokeAnyExecutor {

    public static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3)
        );
        String result = null;
        try {
            result = executor.invokeAny(callables);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
