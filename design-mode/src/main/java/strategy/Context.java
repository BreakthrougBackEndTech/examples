package strategy;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-19 12:06
 **/
public class Context {

    private Strategy strategy;

    public Context(String type) {

        switch (type) {
            case "SNMPFM":
                this.strategy = new StrategySNMPFM();
                break;
            case "FTPPM":
                this.strategy = new StrategyFTPPM();
                break;
            default:
                throw new IllegalArgumentException("Not supported type");
        }
    }

    public void printMediationInfo() {
        this.strategy.printMediationInfo();
    }
}

