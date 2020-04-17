package composite;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-17 13:19
 **/
public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void add(Component component);

    public abstract void remove(Component component);

    public abstract void display(int depth);
}

