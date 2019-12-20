package com.order.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.common.common.Constants;
import com.common.domain.CallBackMessage;
import com.item.api.exception.ItemBizException;
import com.order.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


/**
 * mq消息消费者
 */
@Service
public class CallBackMessageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(CallBackMessageConsumer.class);

    @Autowired
    private MessageService messageService;

    @JmsListener(destination = Constants.CALLBACK_BUY_RECORD_QUEUE)
    public void receiveMessage(String msg) {

        LOG.info("商品订单服务接收到消息：" + msg);

        CallBackMessage message = JSON.parseObject(msg, CallBackMessage.class);

        // 商品购买记录，消息状态更改
//        itemService.buyRecordAndConfirmMessageSuccess(message);
        try {
            messageService.modifyDBMessageSendStatus(message.getMessageId(), message.getMessageStatus());
        } catch (ItemBizException e) {
            LOG.error("更新消息状态失败", e);
        }
    }

}
