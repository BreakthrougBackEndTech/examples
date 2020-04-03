package common.jmx;


import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-02 10:36
 **/
/*-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.port=9999
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
使用Jconsole Mbean查看
*/
//@Service
public class MBeanRegister {
//    private final Logger LOGGER = LoggerFactory.getLogger(MBeanRegister.class);

    @PostConstruct
    public void init() {
//        LOGGER.info("register jmx");
        String MBEAN_OBJECT_NAME = "com.nokia.oss.luffy.demo:type=PM";
        PMMonitor pmMbean = new PMMonitor("Demo_MBean");

        //Get MBean server
        MBeanServer mbServer = ManagementFactory.getPlatformMBeanServer();
        //Register MBean to MBean server
        try {
            mbServer.registerMBean(pmMbean, new ObjectName(MBEAN_OBJECT_NAME));
        } catch (Exception e) {
//            LOGGER.error("register failure", e);
            e.printStackTrace();
        }

    }
}

