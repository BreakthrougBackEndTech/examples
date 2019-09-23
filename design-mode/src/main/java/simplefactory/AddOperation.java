package simplefactory;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-23 11:51
 **/
public class AddOperation implements Operation {
    @Override
    public void calc(int a, int b) {
        System.out.println("result:" + (a + b));
    }
}

