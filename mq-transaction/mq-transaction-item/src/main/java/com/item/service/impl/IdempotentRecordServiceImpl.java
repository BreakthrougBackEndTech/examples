package com.item.service.impl;

import com.common.common.Constants;
import com.common.common.MsgCode;
import com.common.enmus.MessageSendStatusEnum;
import com.common.entity.MessageEntity;
import com.item.api.exception.ItemBizException;
import com.item.entity.IdempotentRecordEntity;
import com.item.mapper.IdempotentRecordEntityMapper;
import com.item.service.IdempotentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class IdempotentRecordServiceImpl implements IdempotentRecordService {

    @Autowired
    private IdempotentRecordEntityMapper idempotentRecordEntityMapper;


    @Override
    public void insertSelective(Long id) {
        IdempotentRecordEntity idempotentRecordEntity = new IdempotentRecordEntity();

        idempotentRecordEntity.setId(id);
        idempotentRecordEntity.setCreate_time(Calendar.getInstance().getTime());
        idempotentRecordEntity.setStatus(MessageSendStatusEnum.SUCCESS.name());

        if (idempotentRecordEntityMapper.insertSelective(idempotentRecordEntity) <= 0) {
            throw new ItemBizException(MsgCode.INSERT_RESULT_0);
        }
    }

    @Override
    public IdempotentRecordEntity selectByPrimaryKey(Long id) {
        return idempotentRecordEntityMapper.selectByPrimaryKey(id);
    }
}
