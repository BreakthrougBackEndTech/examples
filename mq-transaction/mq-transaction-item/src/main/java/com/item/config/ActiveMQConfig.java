package com.item.config;

import com.common.enmus.QueueNameEnum;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@Configuration
@EnableJms
public class ActiveMQConfig {

    /**
     * 订单状态队列
     *
     * @return
     */
    @Bean("callBackBuyRecordQueue")
    public Queue localQueue() {
        return new ActiveMQQueue(QueueNameEnum.CALLBACK_BUY_RECORD_QUEUE.getValue());
    }

}
