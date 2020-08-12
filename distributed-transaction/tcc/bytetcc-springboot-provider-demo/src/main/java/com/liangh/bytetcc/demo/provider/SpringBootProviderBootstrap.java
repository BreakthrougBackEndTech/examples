package com.liangh.bytetcc.demo.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.io.IOException;

@Slf4j
@SpringBootApplication(exclude = MongoAutoConfiguration.class)  // 使用文件存储时, 不需要配置mongodb
public class SpringBootProviderBootstrap {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(SpringBootProviderBootstrap.class,args);
	}

}
