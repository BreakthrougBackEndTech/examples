package mq;

import org.redisson.api.RTopic;
import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.StatusListener;

public class SubscribeClient {
    public static final String CHANNEL_KEY = "channel:message";


    public static void main(String[] args) {
        RTopic topic = RedissionFactory.getRedissonClient().getTopic(CHANNEL_KEY);

        topic.addListener(new StatusListener() {


            @Override
            public void onSubscribe(String s) {
                System.out.println("onSubscribe message");
            }

            @Override
            public void onUnsubscribe(String s) {
                System.out.println("onUnsubscribe message");
            }


        });


        topic.addListener(String.class, new MessageListener<String>() {
            @Override
            public void onMessage(CharSequence charSequence, String s) {
                System.out.println("receive message" + s);
            }
        });
    }
}
