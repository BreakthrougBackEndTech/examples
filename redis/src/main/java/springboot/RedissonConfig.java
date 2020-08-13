package springboot;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-09 12:07
 **/
//@Configuration
public class RedissonConfig {
//    @Value("${spring.redis.host}")
    private String host;

//    @Value("${spring.redis.port}")
    private String port;

//    @Value("${spring.redis.password}")
    private String password;

//    @Bean
    public RedissonClient getRedisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        //config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        //添加主从配置
        //config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"", ""});


/*//指定使用哨兵部署方式
        config.useSentinelServers()
                //设置sentinel.conf配置里的sentinel别名
                //比如sentinel.conf里配置为sentinel monitor my-sentinel-name 127.0.0.1 6379 2,那么这里就配置my-sentinel-name
                .setMasterName("my-sentinel-name")
                //这里设置sentinel节点的服务IP和端口，sentinel是采用Paxos拜占庭协议，一般sentinel至少3个节点
                //记住这里不是配置redis节点的服务端口和IP，sentinel会自己把请求转发给后面monitor的redis节点
                .addSentinelAddress("redis://127.0.0.1:26379")
                .addSentinelAddress("redis://127.0.0.1:26389")*/


//指定使用集群部署方式
/*        config.useClusterServers()
                // 集群状态扫描间隔时间，单位是毫秒
                .setScanInterval(2000)
                //cluster方式至少6个节点(3主3从，3主做sharding，3从用来保证主宕机后可以高可用)
                .addNodeAddress("redis://127.0.0.1:6379" )
                .addNodeAddress("redis://127.0.0.1:6380")
                .addNodeAddress("redis://127.0.0.1:6381")
                .addNodeAddress("redis://127.0.0.1:6382")
                .addNodeAddress("redis://127.0.0.1:6383")
                .addNodeAddress("redis://127.0.0.1:6384");*/

        return Redisson.create(config);
    }
}

