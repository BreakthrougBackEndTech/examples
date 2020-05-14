package com.luffy.rpc.thrift.examples.rpc;

import com.luffy.rpc.thrift.examples.Calculator;
import com.luffy.rpc.thrift.server.annotation.ThriftService;

import java.math.BigDecimal;

@ThriftService(name = "rpcCalculatorService", version = 2.0)
public class RpcCalculatorService implements Calculator.Iface {

    @Override
    public int add(int arg1, int arg2) {
        BigDecimal arg1Decimal = new BigDecimal(arg1);
        BigDecimal arg2Decimal = new BigDecimal(arg2);
        return arg1Decimal.add(arg2Decimal).intValue();
    }

    @Override
    public int subtract(int arg1, int arg2) {
        BigDecimal arg1Decimal = new BigDecimal(arg1);
        BigDecimal arg2Decimal = new BigDecimal(arg2);
        return arg1Decimal.subtract(arg2Decimal).intValue();
    }

    @Override
    public int multiply(int arg1, int arg2) {
        BigDecimal arg1Decimal = new BigDecimal(arg1);
        BigDecimal arg2Decimal = new BigDecimal(arg2);
        return arg1Decimal.multiply(arg2Decimal).intValue();
    }

    @Override
    public int division(int arg1, int arg2) {
        BigDecimal arg1Decimal = new BigDecimal(arg1);
        BigDecimal arg2Decimal = new BigDecimal(arg2);
        return arg1Decimal.divide(arg2Decimal).intValue();
    }
}
