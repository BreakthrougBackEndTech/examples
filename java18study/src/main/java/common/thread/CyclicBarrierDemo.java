package common.thread;

import java.util.concurrent.*;

/**
 * @author: zhegong
 **/
public class CyclicBarrierDemo {
    //指定必须有6个运动员到达才行
    private static CyclicBarrier barrier = new CyclicBarrier(6, () -> {
        System.out.println("所有运动员入场，裁判员一声令下！！！！！");
    });

    private static CountDownLatch endSignal = new CountDownLatch(12);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("运动员准备进场，全场欢呼............");

        ExecutorService service = Executors.newFixedThreadPool(12);
        for (int i = 0; i < 12; i++) {
            service.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 运动员，进场");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + "  运动员出发");
                    endSignal.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        endSignal.await();
        System.out.println("所有运动员到达，比赛结束！");
        service.shutdown();
    }
}
