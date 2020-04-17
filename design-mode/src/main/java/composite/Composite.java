package composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-17 13:32
 **/
public class Composite extends Component {
    List<Component> list = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        list.add(component);
    }

    @Override
    public void remove(Component component) {
        list.remove(component);
    }

    @Override
    public void display(int depth) {
        for (int i = 0; i < depth; i++)
            System.out.print("-");
        System.out.println(name);

        for (Component component : list) {
            component.display(depth + 2);
        }
    }

}

