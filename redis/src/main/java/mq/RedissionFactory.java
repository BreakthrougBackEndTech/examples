package mq;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;

public final class RedissionFactory {


    private static final RedissionFactory REDISSION_FACTORY = new RedissionFactory();

    //    private static final String REDIS_CONFIG_FILE = com.nsn.obs.mw.mf.core.config.Config.get("com.nsn.oss.mediations.ne3sws.redis.client.configFile","/opt/oss/NSN-common_mediations/smx/mf-conf/ne3sws_redis_config.yaml");
    private static final String REDIS_CONFIG_FILE = "/var/opt/oss/global/isdk/redis/isdk_redis_config.yaml";

    /*
    protect time duration for create redisson client to avoid frequent redis client create
     */
//    private static final long PROTECT_TIME = com.nsn.obs.mw.mf.core.config.Config.getLong("com.nsn.oss.mediations.ne3sws.redis.client.createProtectTime", 30000L);
    private static final long PROTECT_TIME = 30000L;
    /*
    each mediation JVM use single redisson client
     */
    private volatile RedissonClient redissonClient;

    private long lastClientInitTime;

    private RedissionFactory() {
    }

    /**
     * If redisson client exists, return redisson client
     * If not, create new redisson client
     *
     * @return
     * @throws RuntimeException
     */
    private RedissonClient getOrCreateRedissonClient() throws RuntimeException {
        if (redissonClient == null) {
            long duration = System.currentTimeMillis() - lastClientInitTime;
            if (duration >= PROTECT_TIME) {
                synchronized (RedissionFactory.class) {
                    if (redissonClient == null) {
                        try {
                            /*
                            redis config file is expected to be present before redissonClient create
                             */
                            Config config = Config.fromYAML(new File(REDIS_CONFIG_FILE));
                            redissonClient = Redisson.create(config);
                        } catch (Exception e) {
                            String errorMsg = "Error happen while creating redisson client";
                            lastClientInitTime = System.currentTimeMillis();
                            throw new RuntimeException(errorMsg, e);
                        }
                    }
                }
            } else {
                throw new RuntimeException("Redisson client creation request is rejected due to frequent request, expect minimum duration is " + PROTECT_TIME + "ms, acually duration is " + duration + "ms");
            }

        }
        return redissonClient;
    }

    /**
     * @return redissonClient
     * @throws RuntimeException
     */
    public static final RedissonClient getRedissonClient() throws RuntimeException {
        return REDISSION_FACTORY.getOrCreateRedissonClient();
    }
}