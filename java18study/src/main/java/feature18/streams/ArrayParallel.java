package feature18.streams;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-08-21 09:12
 **/
public class ArrayParallel {

    public static double[] imperativeInitilize(int size) {
        double[] values = new double[size];
        for(int i = 0; i < values.length;i++) {
            values[i] = i;
        }
        return values;
    }

    public static double[] parallelInitialize(int size) {
        double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);
        return values;
    }


    public void forRange(){
        IntStream.range(0, 100).forEach(System.out::println);
    }

    public static void main(String[] args) {
        IntStream.range(0, 100).forEach(System.out::println);


        Optional optional = Stream.of("1","2").reduce((s1, s2)->s1+"#"+s2);

        System.out.println(optional.orElse("no object"));
    }
}

