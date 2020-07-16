package common.jmh;

import java.util.stream.IntStream;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-07-16 14:25
 **/
public class JmhDemo {

    public String append() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, 100).forEach(i -> sb.append("index").append(i));
        return sb.toString();
    }


    public String append1() {
        StringBuffer sb = new StringBuffer();
        IntStream.range(0, 100).forEach(i -> sb.append("index").append(i));
        return sb.toString();
    }


    public String append2() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, 100).forEach(i -> sb.append("index" + i));
        return sb.toString();
    }

    public String append3() {
        StringBuffer sb = new StringBuffer();
        IntStream.range(0, 100).forEach(i -> sb.append("index" + i));
        return sb.toString();
    }

    public String append4() {
        String str = "";
        for (int i = 0; i < 100; i++) {
            str += "index" + i;
        }

        return str;
    }
}

