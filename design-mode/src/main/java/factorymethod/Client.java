package factorymethod;

import simplefactory.Operation;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-23 12:12
 **/
public class Client {
    public static void main(String[] args) {
        IFactory factory = new AddFactory();

        Operation operation = factory.createOperation();

        operation.calc(1, 2);
    }
}

