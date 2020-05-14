package com.luffy.rpc.thrift.examples;

import com.luffy.rpc.thrift.server.annotation.EnableThriftServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableThriftServer
public class ThriftServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThriftServerApplication.class, args);
    }

}
// java -jar target/calculator-examples-1.0.0.jar --spring.profiles.active=25000
// java -jar target/calculator-examples-1.0.0.jar --spring.profiles.active=25001
// java -jar target/calculator-examples-1.0.0.jar --spring.profiles.active=25002
// java -jar target/calculator-examples-1.0.0.jar --spring.profiles.active=8080