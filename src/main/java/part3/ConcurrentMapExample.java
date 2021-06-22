/**
 * Describes Concurrent map in java
 * @author Sup4eg
 */
package part3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentMapExample {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        map.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));
        String value1 = map.putIfAbsent("c3", "p1");
        System.out.println(value1);

        String defaultValue = map.getOrDefault("hi", "there");
        System.out.println(defaultValue);

        map.replaceAll((key, value) -> "r2".equals(key) ? "d3" : value);
        System.out.println(map.get("r2"));

        map.compute("foo", (key, value) -> value + value);
        System.out.println(map.get("foo"));

        map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + oldVal);
        System.out.println(map.get("foo"));

        System.out.println(ForkJoinPool.getCommonPoolParallelism());

    }

}
