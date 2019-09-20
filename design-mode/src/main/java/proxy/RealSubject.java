package proxy;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-20 11:46
 **/
public class RealSubject implements Subject {
    @Override
    public void printMediationInfo() {
        System.out.print("this is real subject to do something");
    }
}

