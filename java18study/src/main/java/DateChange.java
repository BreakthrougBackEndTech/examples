import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author: zhegong
 **/
public class DateChange
{
    public static void main(String[] args) {



        while (true){
            CountDownLatch downLatch = new CountDownLatch(1);
            SyncThread thread = new SyncThread(downLatch);

            thread.start();
        }



    }
}

class SyncThread extends Thread{

    CountDownLatch downLatch;

    public SyncThread(CountDownLatch downLatch){
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
                System.out.println(Thread.currentThread().getName());

        downLatch.countDown();

    }
}
