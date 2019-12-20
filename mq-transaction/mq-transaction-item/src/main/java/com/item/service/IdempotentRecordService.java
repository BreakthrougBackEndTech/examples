package com.item.service;

import com.common.entity.MessageEntity;
import com.item.entity.IdempotentRecordEntity;

/**
 * @author: zhegong
 **/
public interface IdempotentRecordService {

    void insertSelective(Long id);

    IdempotentRecordEntity selectByPrimaryKey(Long id);
}
