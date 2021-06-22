/**
 * Describes concurrent hash map
 *
 * @author Sup4eg
 */
package part3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        map.forEach(1, (key, value) -> {
                    System.out.printf("key : %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName());
                }
        );

        String result1 = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("foo".equals(key)) {
                return value;
            }
            return null;
        });
        System.out.println("Result1: " + result1);

        String result2 = map.searchValues(1, value -> {
            System.out.println(Thread.currentThread().getName());
            if (value.length() > 3) {
                return value;
            }
            return null;
        });
        System.out.println("Result2: " + result2);

        String result = map.reduce(1,
                (key, value) -> {
                    System.out.println("Transform: " + Thread.currentThread().getName());
                    return key + "=" + value;
                },
                (s1, s2) -> {
                    System.out.println("Reduce: " + Thread.currentThread().getName());
                    return s1 + ", " + s2;
                });
        System.out.println("Result: " + result);
    }
}
