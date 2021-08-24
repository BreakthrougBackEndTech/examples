package composite;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-17 13:23
 **/
public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        throw new RuntimeException("Leaf not support add");
    }

    @Override
    public void remove(Component component) {
        throw new RuntimeException("Leaf not support remove");
    }

    @Override
    public void display(int depth) {
        for(int i=0; i<depth; i++)
            System.out.print("-");
        System.out.println(name);
    }
}

