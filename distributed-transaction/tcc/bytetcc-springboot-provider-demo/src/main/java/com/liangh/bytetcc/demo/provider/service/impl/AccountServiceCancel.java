package com.liangh.bytetcc.demo.provider.service.impl;

import com.liangh.bytetcc.demo.provider.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("accountServiceCancel")
public class AccountServiceCancel implements AccountService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public void increaseAmount(String acctId, double amount) {
		String sql = "update tb_account_two set frozen = frozen - ? where acct_id = ?";
		jdbcTemplate.update(sql, amount, acctId);
		log.info("xxxxxxxxxxxxxxxxxxxx---------cancel provider increase amount");
	}


}
