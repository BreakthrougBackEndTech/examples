package mq;

import org.redisson.api.RTopic;

public class Publisher {
    public static final String CHANNEL_KEY = "channel:message";

    public Publisher() {
//        jedis = MyJedisFactory.getLocalJedis();
    }

    public void publishMessage(String message) {
//        if(StringUtils.isBlank(message)) {
//            return;
//        }
        RTopic topic = RedissionFactory.getRedissonClient().getTopic(CHANNEL_KEY);

        topic.publish(message);

    }

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.publishMessage("Hello Redis!" + args[0]);

        System.out.println("finish pub");

        System.exit(0);
    }


}
