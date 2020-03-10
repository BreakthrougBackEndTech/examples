package jvm;

/**
 * 经过测试，堆内存会正常， 到达最大值后被回收， 有性能risk
 *
 * @author: zhegong
 **/
public class InternTest {
    public static void main(String[] args) {
        int i = 1;
        while (true) {
            System.out.println(String.valueOf(i).intern());
            i++;
        }
    }
}
