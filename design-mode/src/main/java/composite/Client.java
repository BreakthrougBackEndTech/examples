package composite;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-17 13:37
 **/
public class Client {
    public static void main(String[] args) {
        Composite root = new Composite("root");
        root.add(new Leaf("leaf1"));
        root.add(new Leaf("leaf2"));

        Composite child1 = new Composite("child1");
        root.add(child1);

        child1.add(new Leaf("child leaf1"));
        child1.add(new Leaf("child leaf2"));

        root.display(1);
    }
}

