package part1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Describes ScheduleExecutorService
 * @author Sup4eg
 */

public class ScheduledExecutorServiceJava {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);
        try {
            TimeUnit.MILLISECONDS.sleep(1337);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms", remainingDelay);

        System.out.println("-".repeat(20));
        int initialDelay = 0;
        int period = 1;


        //executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
        //учитываем время выполнения задачи
        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
    }

}
