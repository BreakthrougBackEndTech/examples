package decorate;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-20 17:10
 **/
public class NorthBoundMediation implements Mediation {
    @Override
    public void printMediationInfo() {
        System.out.println("north bound mediation");
    }
}

