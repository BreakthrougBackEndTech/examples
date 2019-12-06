package jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-06 16:04
 **/
public class JedisTest {
    public static void main(String[] args) {
        RedisPool redis = new RedisPool();
    /*    redis.execute(new CallWithJedis() {
            @Override
            public void call(Jedis jedis) {
            // do something with jedis
            }
        });*/

        redis.execute(jedis -> {
            // do something with jedis
        });
    }
}

interface CallWithJedis {
    public void call(Jedis jedis);
}

class RedisPool {
    private JedisPool pool;

    public RedisPool() {
        this.pool = new JedisPool();
    }

    public void execute(CallWithJedis caller) {
        try (Jedis jedis = pool.getResource()) {
            caller.call(jedis);
        }

        /**连接失败 重试*/
  /*      Jedis jedis = pool.getResource();
        try {
            caller.call(jedis);
        } catch (JedisConnectionException e) {
            caller.call(jedis); // 重试一次
        } finally {
            jedis.close();
        }*/
    }
}

