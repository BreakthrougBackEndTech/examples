package lock;
import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-06 14:11
 **/
public class OptimisticUsage {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        String userId = "abc";
        String key = keyFor(userId);
        jedis.setnx(key, String.valueOf(5)); // setnx 做初始化
        System.out.println(doubleAccount(jedis, userId));
        jedis.close();
    }

    /**
     * watch 会在事务开始之前盯住 1 个或多个关键变量, 禁止在 multi 和 exec 之间执行 watch 指
     while True:
        do_watch()
        commands()
        multi()
        send_commands()
        try:
            exec()
            break
        except WatchError:
            continue
     */
    public static int doubleAccount(Jedis jedis, String userId) {
        String key = keyFor(userId);
        while (true) {
            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            value *= 2; // 加倍
            Transaction tx = jedis.multi();
            tx.set(key, String.valueOf(value));
            List<Object> res = tx.exec();
            if (res != null) {
                break; // 成功了
            }
        }
        return Integer.parseInt(jedis.get(key)); // 重新获取余额
    }

    public static String keyFor(String userId) {
        return String.format("account_{}", userId);
    }
}

