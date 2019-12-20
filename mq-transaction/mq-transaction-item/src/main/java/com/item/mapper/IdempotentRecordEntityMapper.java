package com.item.mapper;

import com.item.entity.IdempotentRecordEntity;

public interface IdempotentRecordEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IdempotentRecordEntity record);

    int insertSelective(IdempotentRecordEntity record);

    IdempotentRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IdempotentRecordEntity record);

    int updateByPrimaryKeyWithBLOBs(IdempotentRecordEntity record);

    int updateByPrimaryKey(IdempotentRecordEntity record);
}