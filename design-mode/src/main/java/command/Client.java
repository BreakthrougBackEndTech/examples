package command;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-02 13:48
 **/
public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command snmpFmCommand =  new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();

        invoker.addCommand(snmpFmCommand);

        invoker.executeCommands();
    }
}

