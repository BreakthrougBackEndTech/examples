package com.liangh.bytetcc.demo.consumer.controller;

import com.liangh.bytetcc.demo.consumer.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Compensable(interfaceClass = TransferService.class, confirmableKey = "transferServiceConfirm",cancellableKey = "transferServiceCancel")
@RestController
public class ConsumerController {

	private int i = 0;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RestTemplate restTemplate;

	@ResponseBody
	@RequestMapping(value = "/transfer/{source}/{target}/{amount}")
	@Transactional
	public String transferAmount(@PathVariable("source") String source, @PathVariable("target") String target,
			@PathVariable("amount") double amount) {


		log.info("调用三方接口begin");
		ResponseEntity<String> stringResponseEntity = this.restTemplate.postForEntity(String.format("http://127.0.0.1:8081/increase/%s/%s", target, amount), null, String.class);
		log.info("调用三方接口end,response:{}",stringResponseEntity);

		this.jdbcTemplate.update("update tb_account_one set amount = amount - ?,frozen = frozen + ? where acct_id = ?", amount, amount,source);

		boolean condition = (i % 2) == 0;
		i++;
		if(condition){
			throw new RuntimeException("梁欢不允许资金外逃");
		}
		return "ok";
	}

}
