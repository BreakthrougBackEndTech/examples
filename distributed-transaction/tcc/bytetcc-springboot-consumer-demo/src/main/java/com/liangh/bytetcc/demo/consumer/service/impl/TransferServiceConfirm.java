package com.liangh.bytetcc.demo.consumer.service.impl;

import com.liangh.bytetcc.demo.consumer.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("transferServiceConfirm")
public class TransferServiceConfirm implements TransferService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public void transferAmount(String source, String target, double amount) {
		String sql = "update tb_account_one set frozen = frozen - ? where acct_id = ?";
		jdbcTemplate.update(sql,amount,source);
		log.info("xxxxxxxxxxxxxxxxxxxx---------confirm transferServiceConfirm");
	}

}
