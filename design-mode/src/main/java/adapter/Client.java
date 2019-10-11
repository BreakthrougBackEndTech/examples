package adapter;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-10-11 09:00
 **/
public class Client {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.printMediationInfo();
    }
}

