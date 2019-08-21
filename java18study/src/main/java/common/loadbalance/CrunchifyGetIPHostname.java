package common.loadbalance; /**
 * @author: zhegong
 **/
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author Crunchify.com
 */

public class CrunchifyGetIPHostname {

    public static void main(String[] args) {

        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);

            new Random().nextInt(1);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
    }
}