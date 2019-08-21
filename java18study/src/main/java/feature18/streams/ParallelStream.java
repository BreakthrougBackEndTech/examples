package feature18.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhegong
 **/
public class ParallelStream {
    public static void main(String[] args) {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();

        long count = values.stream().sorted((a, b) -> b.compareTo(a)).count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

// sequential sort took: 899 ms



         t0 = System.nanoTime();

         count = values.parallelStream().sorted((a, b) -> b.compareTo(a)).count();
        System.out.println(count);

         t1 = System.nanoTime();

         millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));

// parallel sort took: 472 ms

    }
}
