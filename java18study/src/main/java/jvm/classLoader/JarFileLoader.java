package jvm.classLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-08-06 13:59
 **/
public class JarFileLoader extends URLClassLoader {
    public JarFileLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
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

