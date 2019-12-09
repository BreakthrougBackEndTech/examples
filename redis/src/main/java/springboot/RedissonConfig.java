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
        return Redisson.create(config);
    }
}

