package com.luffy.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-17 16:26
 **/
@Component
public class Consumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeMessage(LocalDateTime time) {
        System.out.println("consume message:" + time.toString());
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_2)
    public void consumeMessage2(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("consume message2:" + message);

        channel.basicAck(tag, false);//TODO check
    }
}
