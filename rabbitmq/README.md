#### 处理流程
Producer -> 序列化-> 指定exchange 和 routingKey->发送到broker中
Consumer订阅 broker消息 -> 可能的反序列化->接受的业务方数据

#### 队列
可以被多个消费者消费， 消息不重复

#### Exchange类型
1. fanout  发送给所有绑定的队列
2. direct  发送给routingKey和bingingKey相同的队列
3. topic  
RoutingKey 为一个点号" "分隔的字符串(被点号"."分隔开的每一段独立的字符串称为一个单词)，如com.rabbitmq.client，java.util.concurrent 
BindingKey 和RoutingKey 一样也是点号"."分隔的字符串;
BindingKey 中可以存在两种特殊字符串"*"和"#"，用于做模糊匹配，其中"*"用于匹配一个单词，"#"用于匹配多规格单词(可以是零个)。
4. header  通过消息header里面的键值对转发， 性能很差

connection是实体的TCP链接，  channel是建立在connection上的虚拟连接
#### ACK
如果RabbitMQ 一直没有收到消费者的确认信号，并且消费此消息的消费者己经
断开连接，则RabbitMQ 会安排该消息重新进入队列，等待投递给下一个消费者，当然也有可
能还是原来的那个消费者。

#### 队列
TTL 0代表不超时 设置超时时间，在队列中超时， 就丢到死信交换器， 进入死信队列
延迟队列 可以采用死信队列实现
优先级队列

#### 持久化
交换机,队列,消息的持久化 
交换机和队列是在重启的时候 元数据不丢
BasicPropert i es 中的deliveryMode 属性)设置为2 即可实现消息的持久

#### 生产者确认
1. 事物
Rabb itMQ 客户端中与事务机制相关的方法有三个: channel.txSelect 、channel. xCommit 和channel.txRollback
性能低
2. 发送方确认机制
批量确认 
异步确认
channel.addConfirmListener(new ConfirmListener()(

#### 消费者确认
推模式 channel.basicQos(int prefetchCount) 设置消费最大个数， 滑动窗口。
channel.basicQos(10); // Per consumer limit
channel.basicQos(3, false); // Per consumer limit
channel.basicQos(5, true); // Per channel limit

不能保证消息顺序

#### 集群
磁盘节点 和内存节点 ， 至少有一个磁盘节点 
当所有磁盘节点挂了 ， 只能发送接收消息， 不能够更改配置

通过镜像 master 和 slave 来保证高可用,   发布消息向所有 master和 slave 发布， 其它的发送到master， master再广播
master挂，加入时间最长的slave提升为master
订阅只从master读，  就算从slave get， 也是slave转发请求到master， master回复slave， slave再回客户端

广域网集群 用Federation 或者Shovel

##### 负载均衡  平均连接到集群内的节点
1. 客户端内部  轮询 加权轮询  随机 加权随机  客户端IPHash 最小连接
2. HAProxy + Keepalived
3. LVS + Keepalived