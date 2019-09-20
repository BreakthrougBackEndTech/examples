package strategy;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-19 12:04
 **/
public class StrategyFTPPM implements Strategy {
    @Override
    public void printMediationInfo() {
        System.out.print("this is ftppm mediation, it use ftp/sftp protocol");
    }
}

