package jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM args:  -Xmx20M --XX:MaxDirectMemorySize=10M
 * 越过了DirectByteBuffer 类
 * @author: zhegong
 **/
public class DirectMemoryOOM {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField= Unsafe.class.getDeclaredFields()[0];

        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        int i=0;
        while (true){
            System.out.println(i++);
            unsafe.allocateMemory(_1MB);
        }
    }
}
