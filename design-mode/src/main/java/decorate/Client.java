package decorate;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-20 17:15
 **/
public class Client {
    public static void main(String[] args) {
        Mediation northBoundMediation = new NorthBoundMediation();
        Decorator snmpfmMediation = new SNMPFMMediation();
        Decorator ftppmMediation = new FTPPMMediation();

        snmpfmMediation.setMediation(northBoundMediation);
        ftppmMediation.setMediation(snmpfmMediation);

        ftppmMediation.printMediationInfo();
    }
}

