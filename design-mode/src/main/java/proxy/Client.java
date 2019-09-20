package proxy;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-20 11:51
 **/
public class Client {
    public static void main(String[] args) {

        Subject subject = new Proxy();

        subject.printMediationInfo();

    }
}

