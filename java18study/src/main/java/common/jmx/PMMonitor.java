package common.jmx;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-02 10:27
 **/
public class PMMonitor implements PMMonitorMBean {
    private String name;
    private AtomicInteger receivedTasks = new AtomicInteger();

    public PMMonitor(String name) {
        this.name = name;
    }

    public void incReceivedTasks() {
        this.receivedTasks.incrementAndGet();
    }

    public String getName() {
        return name;
    }

    public long getReceivedTasks() {
        return receivedTasks.get();
    }
}


