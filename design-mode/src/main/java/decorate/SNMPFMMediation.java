package decorate;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-20 17:16
 **/
public class SNMPFMMediation extends Decorator {
    private String protocol = "SNMP";

    @Override
    public void printMediationInfo() {
        super.printMediationInfo();

        System.out.println("SNMPFM mediation use " + protocol);
    }
}

