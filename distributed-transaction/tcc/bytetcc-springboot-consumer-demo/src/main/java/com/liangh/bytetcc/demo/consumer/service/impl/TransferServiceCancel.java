package com.liangh.bytetcc.demo.consumer.service.impl;

import com.liangh.bytetcc.demo.consumer.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("transferServiceCancel")
public class TransferServiceCancel implements TransferService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public void transferAmount(String source, String target, double amount) {
		String sql = "update tb_account_one set amount = amount + ?, fronzen = fronzen + ? where acct_id = ?";
		this.jdbcTemplate.update(sql, amount,amount, target);
		log.info("xxxxxxxxxxxxxxxxxxxx---------cancel transferAmount");
	}

}
