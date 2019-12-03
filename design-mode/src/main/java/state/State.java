package state;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-03 10:02
 **/
public interface State {
    void handle(Context context);
}
