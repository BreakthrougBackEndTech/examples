package com.liangh.bytetcc.demo.provider.service.impl;

import com.liangh.bytetcc.demo.provider.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("accountServiceConfirm")
public class AccountServiceConfirm implements AccountService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public void increaseAmount(String acctId, double amount) {
		String sql = "update tb_account_two set amount = amount + ?, frozen = frozen - ? where acct_id = ?";
		jdbcTemplate.update(sql,amount,amount,acctId);
		log.info("xxxxxxxxxxxxxxxxxxxx---------confirm provider increaseAmount");
	}

}
