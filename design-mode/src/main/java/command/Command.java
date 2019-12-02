package command;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-02 13:48
 **/
public abstract class Command {

    protected Receiver receiver;

    public Command(Receiver receiver){
        this.receiver = receiver;
    }

    protected abstract void execute();

}

