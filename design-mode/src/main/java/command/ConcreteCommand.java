package command;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-02 13:53
 **/
public class ConcreteCommand extends Command {

    public ConcreteCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    protected void execute() {
        receiver.action();
    }
}

