package factorymethod;

import simplefactory.Operation;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-23 12:07
 **/
public interface IFactory {
    Operation createOperation();
}
