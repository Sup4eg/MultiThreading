package part1;

/**
 * Describes Runnable
 * @author Sup4eg
 */

public class RunnableTask {

    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };
        task.run();

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!s");
    }
}
