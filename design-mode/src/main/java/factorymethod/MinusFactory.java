package factorymethod;

import simplefactory.MinusOperation;
import simplefactory.Operation;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-23 12:09
 **/
public class MinusFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new MinusOperation();
    }
}

