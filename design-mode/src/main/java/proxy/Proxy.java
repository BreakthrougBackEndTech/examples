package proxy;

import java.util.PriorityQueue;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-20 11:49
 **/
public class Proxy implements Subject {
    private Subject realSubject = new RealSubject();

    @Override
    public void printMediationInfo() {
        this.realSubject.printMediationInfo();
    }
}

