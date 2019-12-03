package state;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-03 10:03
 **/
public class StopState implements State {
    @Override
    public void handle(Context context) {
        System.out.println("当前状态为" + context.getState());
        context.setState(new StartState());
    }

    @Override
    public String toString() {
        return "stop state";
    }


}

