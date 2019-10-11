package adapter;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-10-10 17:22
 **/
public class Adapter implements Target {

    private SNMPMediation snmpMediation = new SNMPMediation();

    @Override
    public void printMediationInfo() {
        snmpMediation.printInfo();
    }
}

