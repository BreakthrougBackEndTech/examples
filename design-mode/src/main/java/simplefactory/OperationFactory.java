package simplefactory;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-23 11:53
 **/
public class OperationFactory {

    public static Operation createOperation(String type) {

        switch (type) {
            case "add":
                return new AddOperation();
            case "minus":
                return new MinusOperation();
            default:
                throw new IllegalArgumentException("not support type");
        }

    }
}

