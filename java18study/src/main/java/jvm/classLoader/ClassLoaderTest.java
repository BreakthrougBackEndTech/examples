package jvm.classLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-08-06 11:03
 **/
public class ClassLoaderTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        URLClassLoader loader1 = getJarClassLoader("java18study/src/main/resources/jar1/test.jar");
        URLClassLoader loader2 = getJarClassLoader("java18study/src/main/resources/jar2/test.jar");

//        Class myClass=loader1.loadClass("com.classloader.test.Mock");

        Class<?> forName1 = Class.forName("com.classloader.test.Mock", true, loader1);
        Class<?> forName2 = Class.forName("com.classloader.test.Mock", true, loader2);

        Method method1 = forName1.getMethod("print");
        method1.invoke(forName1.newInstance());

        Method method2 = forName2.getMethod("print");
        method2.invoke(forName2.newInstance());

        Class<?> forNameString1 = Class.forName("java.lang.String", true, loader1);
        Class<?> forNameString2 = Class.forName("java.lang.String", true, loader2);

        System.out.println(forNameString1 == forNameString2);

    }

    public static URLClassLoader getJarClassLoader(String fullPathJarName) throws IOException {
        String urlPath = "jar:" + new File(fullPathJarName).toURI().toURL() + "!/";
        URL url = new URL(urlPath);

    /*    URLConnection uc = url.openConnection();
        if( uc instanceof JarURLConnection)
        {
            uc.setUseCaches( true );
        }*/
        return new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
    }

}

