package common.delay;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-27 15:03
 **/
public class DelayQueueTest {
    public static void main(String[] args) {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();


        //生产者
        producer(delayQueue);

        //消费者
        consumer(delayQueue);

        while (true){
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 每1000毫秒创建一个对象，放入延迟队列，延迟时间10000毫秒
     * @param delayQueue
     */
    private static void producer(final DelayQueue<DelayedElement> delayQueue){
        new Thread(new Runnable() {

            public void run() {
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    DelayedElement element = new DelayedElement(10000,"test");
                    delayQueue.offer(element);
                    System.out.println("delayQueue size:"+delayQueue.size());
                }
            }
        }).start();


    }

    /**
     * 消费者，从延迟队列中获得数据,进行处理
     * @param delayQueue
     */
    private static void consumer(final DelayQueue<DelayedElement> delayQueue){
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    DelayedElement element = null;
                    try {
                        // 没有满足延时的元素 用poll返回 null
                        // 没有满足延时的元素 用take会阻塞
                        element =  delayQueue.take();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis()+"---"+element);
                }
            }
        }).start();
    }

}

