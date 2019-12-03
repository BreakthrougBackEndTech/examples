package state;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-03 10:04
 **/
public class StartState implements State {
    @Override
    public void handle(Context context) {
        System.out.println("当前状态为" + context.getState());
        context.setState(new StopState());
    }

    @Override
    public String toString() {
        return "start state";
    }
}

