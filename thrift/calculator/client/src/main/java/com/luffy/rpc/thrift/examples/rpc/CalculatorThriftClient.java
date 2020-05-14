package com.luffy.rpc.thrift.examples.rpc;

import com.luffy.rpc.thrift.examples.Calculator;
import com.luffy.rpc.thrift.client.annotation.ThriftClient;
import com.luffy.rpc.thrift.client.common.ThriftClientAware;

@ThriftClient(serviceId = "thrift-calculator-rpc-examples", refer = Calculator.class, version = 2.0)
public interface CalculatorThriftClient extends ThriftClientAware<Calculator.Client> {
}
