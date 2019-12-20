package com.item.mq.producer.impl;

import com.item.mq.producer.CallBackMsgProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

@SuppressWarnings("ALL")
@Component
public class CallBackMsgProducerServiceImpl implements CallBackMsgProducerService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CallBackMsgProducerServiceImpl.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource(name = "callBackBuyRecordQueue")
    private Queue localBuyRecordQueue;

    @Override
    public void sendMessage(String message) {
        this.jmsMessagingTemplate.convertAndSend(this.localBuyRecordQueue, message);
        LOG.info("=========消息发送成功===========：" + message);
    }
}
