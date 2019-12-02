package command;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-02 13:48
 **/
public class Invoker {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void removeCommand(Command command) {
        commands.remove(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
    }
}

