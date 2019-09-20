package strategy;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-19 12:02
 **/
public class StrategySNMPFM implements Strategy {
    @Override
    public void printMediationInfo() {
        System.out.print("this is snmpfm mediation, it use snmp v1 v2c v3 protocol");

    }
}

