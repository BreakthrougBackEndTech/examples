package com.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.common.domain.BuyRecordMessage;
import com.common.domain.CallBackMessage;
import com.common.domain.OrderItemRecordDO;
import com.common.enmus.MessageSendStatusEnum;
import com.common.utils.IdWorker;
import com.item.api.service.ItemService;
import com.item.entity.IdempotentRecordEntity;
import com.item.entity.ItemUserRecordEntity;
import com.item.mapper.ItemUserRecordEntityMapper;
import com.item.mq.producer.CallBackMsgProducerService;
import com.item.service.IdempotentRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品购买记录服务
 */
@org.springframework.stereotype.Service // 测试mq本地消息最终一致，不用dubbo
//@Service(
//        version = "1.0.0",
//        application = "${dubbo.application.id}",
//        protocol = "${dubbo.protocol.id}",
//        registry = "${dubbo.registry.id}"
//)
public class ItemServiceImpl implements ItemService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemUserRecordEntityMapper itemUserRecordEntityMapper;
    @Autowired
    private IdempotentRecordService idempotentRecordService;

    @Autowired
    private CallBackMsgProducerService callBackMsgProducerService;

    @Transactional
    @Override
    public void recordOrderItem(List<OrderItemRecordDO> list) {
        for (OrderItemRecordDO orderItemRecordDO : list) {
            ItemUserRecordEntity itemUserRecordEntity = new ItemUserRecordEntity();
            itemUserRecordEntity.setId(IdWorker.getId());
            convertItemUserRecord(orderItemRecordDO, itemUserRecordEntity);
            itemUserRecordEntityMapper.insertSelective(itemUserRecordEntity);
        }
    }

    @Transactional
    @Override
    public void buyRecordAndConfirmMessageSuccess(BuyRecordMessage message) {
        Long messageId = message.getMessageId();
        List<OrderItemRecordDO> list = message.getList();
        String queueName = message.getQueueName();

        getCallBackMessage(messageId);

        IdempotentRecordEntity idempotentRecordEntity = idempotentRecordService.selectByPrimaryKey(messageId);

        if (idempotentRecordEntity != null) {    // 消费成功的消息不再次消费了, 是否需要考虑状态
            LOG.info("无需再次消费消息ID为" + messageId + "的消息, 直接发送成功消息");
            callBackMsgProducerService.sendMessage(getCallBackMessage(messageId));
            return;
        }

        // 商品购买记录
        recordOrderItem(list);

        //幂等性 插入
        idempotentRecordService.insertSelective(messageId);

//        if (true) {
//        	throw new ItemBizException(MsgCode.ERROR);
//        }

        // 库存记录成功消息
        callBackMsgProducerService.sendMessage(getCallBackMessage(messageId));

        LOG.info("消息ID为" + messageId + "的消息本次成功消费");
    }

    private String getCallBackMessage(Long messageId) {
        CallBackMessage callBackMessage = new CallBackMessage();
        callBackMessage.setMessageId(messageId);
        callBackMessage.setMessageStatus(MessageSendStatusEnum.SUCCESS.name());

        return JSON.toJSONString(callBackMessage);
    }

    private void convertItemUserRecord(OrderItemRecordDO orderItemRecordDO, ItemUserRecordEntity itemUserRecordEntity) {
        itemUserRecordEntity.setItem_id(orderItemRecordDO.getItem_id());
        itemUserRecordEntity.setOrder_id(orderItemRecordDO.getOrder_id());
        itemUserRecordEntity.setPhone(orderItemRecordDO.getPhone());
        itemUserRecordEntity.setUsername(orderItemRecordDO.getUsername());
    }

}
