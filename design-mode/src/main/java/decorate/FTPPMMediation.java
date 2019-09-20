package decorate;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-20 17:18
 **/
public class FTPPMMediation extends Decorator {

    @Override
    public void printMediationInfo() {
        super.printMediationInfo();
        System.out.println("FTPPM mediation use FTP/SFTP protocol");
        addBehavior();
    }

    private void addBehavior(){
        System.out.println("FTPPM special thing");
    }
}

