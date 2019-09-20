package decorate;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-20 17:11
 **/
public abstract class Decorator implements Mediation {
    protected Mediation mediation;

    public void setMediation(Mediation mediation) {
        this.mediation = mediation;
    }


    @Override
    public void printMediationInfo() {
        if (mediation != null) {
            mediation.printMediationInfo();
        }
    }
}

