package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-01-02 09:48
 **/
public class DatagramServer {

    private static  int maxInboundMessageSize = (1 << 16) - 1;

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));

    public static void main(String args[])throws IOException {
        System.out.println("Server starting...\n");
        //在端口号10000上创建套接字，从用户程序发送包到这个端口
        DatagramSocket s = new DatagramSocket(10000);
        byte[] data = new byte[maxInboundMessageSize];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        int i=0;
        long start = System.currentTimeMillis();

        while (i<1000000) {
            s.receive(packet);
//            System.out.println("client:" + new String(data));
            i++;

            if(i%10000 == 0){
                System.err.println(System.currentTimeMillis() -start);
                start = System.currentTimeMillis();
            }

            ByteBuffer bis;

            byte[] bytes = new byte[packet.getLength()];
            System.arraycopy(packet.getData(), 0, bytes, 0, bytes.length);
            bis = ByteBuffer.wrap(bytes);

            try {
                threadPoolExecutor.execute(new MessageHandle(bis));
            }catch (Exception e){

            }

        }


    }


   static class MessageHandle implements Runnable{
        ByteBuffer bis;

        public MessageHandle(ByteBuffer bis) {
            this.bis = bis;
        }

        @Override
        public void run() {
            int i = bis.array().length;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

