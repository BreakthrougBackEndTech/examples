package state;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-03 10:07
 **/
public class Client {
    public static void main(String[] args) {
        Context context = new Context(new StartState());

        context.request();
        context.request();
    }
}

