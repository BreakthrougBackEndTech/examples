package simplefactory;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-23 12:02
 **/
public class Client {
    public static void main(String[] args) {
        Operation operation = OperationFactory.createOperation("add");

        operation.calc(1, 2);
    }
}

