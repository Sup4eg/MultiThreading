package part1;

import java.util.concurrent.*;
import java.util.concurrent.Executor;

/**
 * Describes callable tasks
 * @author Sup4eg
 */

public class CallableTask {
    public static void main(String[] args) {
        //create callable task with return
        Callable<Integer> task = () -> {
          try {
              TimeUnit.SECONDS.sleep(1);
              return 123;
          } catch (InterruptedException e) {
              throw new IllegalStateException("task interrupted", e);
          }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());
        Integer result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);
    }
}
