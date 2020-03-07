package udp;

import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-01-02 09:51
 **/
public class DatagramClient {
    public static void main(String args[]) {
        String host = "localhost";
        if (args.length == 1)
            host = args[0];
        DatagramPacket dgp1;
        DatagramSocket s = null;
        try {
            s = new DatagramSocket();
            byte[] buffer;//用来存储发送的数据
            buffer = RandomStringUtils.randomNumeric(65507).getBytes();
            System.out.println(RandomStringUtils.randomNumeric(65535));
//                    buffer = new String("hello我是龚真平，增加长度").getBytes();//字符串转化数组
            //UDP  最大长度65507 = 65535-20-8   ip头和udp头
            //将主机名转变为InetAddress类对象，此对象存储有ip对象和地址
            InetAddress ia = InetAddress.getByName(host);
            int i = 100000;
            while (i >= 0) {
                //创建一个DatagramPacket对象来封装字节数组的指针以及目标地址信息，目标地址包含了ip和端口号
                DatagramPacket dgp = new DatagramPacket(buffer, buffer.length, ia, 10000);
                s.send(dgp);
                i--;
            }
//            byte[] buffer2 = new byte[50];

   /*         //创建一个对象来封装返回来的数据
            dgp1 = new DatagramPacket(buffer2, buffer.length, ia, 10000);
            s.receive(dgp1);
            System.out.println("server:" + new String(dgp1.getData()));*/
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (s != null)
                s.close();
        }

    }
}

