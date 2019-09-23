package simplefactory;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-23 11:52
 **/
public class MinusOperation implements Operation {
    @Override
    public void calc(int a, int b) {
        System.out.println("result:" + (a + b));
    }
}

