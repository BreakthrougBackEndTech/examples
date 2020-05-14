#spring-cloud-starter-thrift 参考 https://github.com/ostenant/spring-cloud-starter-thrift

### 生成thrift接口
下载并安装0.13.0的Thrift IDL编译器，下载地址http://thrift.apache.org/docs/install。通过编译器生成.java接口的类文件。

thrift-0.13.0.exe -gen java ./CalculatorService.thrift