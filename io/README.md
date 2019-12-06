#### SNMP4J源码解读
SNMP UDP socket:
snmp.listen();  创建一个receive的socket

snmp.send(PDU);  生成唯一requestID，占用应用层线程 wait(timeout)  等待对方 PUD 响应， 本方socket接收，
根据requestID取出SyncResponseListener

snmp.send(PDU, AsyncResponseListener);  生成唯一requestID, 不占应用层线程去等待, 直接返回发送成功, 启动一个timer， 如果超时
没有收到响应， 回调应用层.  本方socket接收到PDU响应， 根据requestID取出AsyncResponseListener， 回调客户端

#### Client Server 通信的四种实现方式
BIO实现
NIO实现
AIO实现
Netty实现

#### TODO  BIO/NIO tomcat 底层 socket