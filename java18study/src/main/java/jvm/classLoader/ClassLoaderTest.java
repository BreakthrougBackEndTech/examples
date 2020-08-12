package jvm.classLoader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-08-06 11:03
 **/
public class ClassLoaderTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        //如果父loader已加载， 会用父loader的
        URLClassLoader loader1 = JarFileLoader.getJarClassLoader("java18study/src/main/resources/jar1/test.jar");
        URLClassLoader loader2 = JarFileLoader.getJarClassLoader("java18study/src/main/resources/jar2/test.jar");

        Class forName1 = loader1.loadClass("com.classloader.test.Mock");
        Class<?> forName2 = Class.forName("com.classloader.test.Mock", true, loader2);

        Method method1 = forName1.getMethod("print");
        method1.invoke(forName1.newInstance());

        Method method2 = forName2.getMethod("print");
        method2.invoke(forName2.newInstance());

        Class<?> forNameString1 = Class.forName("java.lang.String", true, loader1);
        Class<?> forNameString2 = Class.forName("java.lang.String", true, loader2);
        System.out.println(forNameString1 == forNameString2); //true


        //完全隔离
        MyWebAppLoader myWebAppLoader1 = new MyWebAppLoader("java18study/src/main/resources/jar1/");
        Class<?> loaderClass = myWebAppLoader1.findClass("com.classloader.test.Mock"); //jar1
        Method method3 = loaderClass.getMethod("print");
        method3.invoke(loaderClass.newInstance());

        MyWebAppLoader myWebAppLoader2 = new MyWebAppLoader("java18study/src/main/resources/jar2/");
        Class<?> loaderClass1 = myWebAppLoader2.loadClass("java.lang.String");
        System.out.println(forNameString1 == loaderClass1); //true

        Class<?> loaderClass2 = myWebAppLoader2.loadClass("com.classloader.test.Mock"); //parent
        Method method4 = loaderClass2.getMethod("print");
        method4.invoke(loaderClass2.newInstance());
    }



}

