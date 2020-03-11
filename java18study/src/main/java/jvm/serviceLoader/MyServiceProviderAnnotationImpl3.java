package jvm.serviceLoader;

import com.google.auto.service.AutoService;

/**
 * @author: zhegong
 **/
@AutoService(IMyServiceProvider.class)
public class MyServiceProviderAnnotationImpl3 implements IMyServiceProvider {
    public String getName() {
        return "name:AnnotationImpl3";
    }

}
