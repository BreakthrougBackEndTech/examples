package jvm.serviceLoader;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: zhegong
 **/
public class TestClass {
    public static void main(String[] argus) {
        ServiceLoader<IMyServiceProvider> serviceLoader = ServiceLoader.load(IMyServiceProvider.class);

        Iterator iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            IMyServiceProvider item = (IMyServiceProvider) iterator.next();
            System.out.println(item.getName() + ": " + item.hashCode());
        }
    }


}
