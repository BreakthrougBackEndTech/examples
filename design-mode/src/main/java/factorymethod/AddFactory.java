package factorymethod;

import simplefactory.AddOperation;
import simplefactory.Operation;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-23 12:08
 **/
public class AddFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new AddOperation();
    }
}

