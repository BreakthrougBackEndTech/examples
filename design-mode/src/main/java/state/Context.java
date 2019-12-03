package state;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-03 10:02
 **/
public class Context {
    private State state;

    public Context(State state) { //定义Context的初始状态
        super();
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        System.out.println("更改状态为" + state);
    }

    public void request() {
        state.handle(this); //对请求做处理并且指向下一个状态
    }
}

