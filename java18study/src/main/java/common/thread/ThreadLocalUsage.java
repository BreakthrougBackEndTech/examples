package common.thread;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 会和线程池中的线程绑定
 * InheritableThreadLocal  子线程会可以使用父进程的值
 *
 * @author: zhegong
 **/
public class ThreadLocalUsage {
    private static ThreadPoolExecutor workerThreadPool = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(400));

    public static ThreadLocal<String> parentThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        workerThreadPool.setThreadFactory(new BasicThreadFactory.Builder()
                .namingPattern("namePattern" + "-%d")
                .build());
        parentThreadLocal.set("main thread local vale");
        for (int i = 0; i < 10; i++) {
            Thread thread = new ThreadLocalThread("线程" + i);
            workerThreadPool.execute(thread);
//            thread.start();
        }
    }
}


class ThreadLocalThread extends Thread {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    String name;

    public ThreadLocalThread(String name) {
//        thread.setName(thread.getName().replace("Thread-", newName + "-"));
//        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        threadLocal.set(name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        threadLocal.remove();
        System.out.println(ThreadLocalUsage.parentThreadLocal.get());
//        ThreadLocalUsage.parentThreadLocal.set(name + " set parent local value");
    }
}
