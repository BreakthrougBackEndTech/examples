#### 基本类型
#####string
hashMap， 小于1M， 成被扩容， 大于1M，每次增加1M; 最大512M

#####list
类似LinkedList  插入O(1), 查找O(n)
列表元素较少的情况下会使用一块连续的内存存储，这个结构是 ziplist，也即是压缩列表。
它将所有的元素紧挨着一起存储，分配的是一块连续的内存。当数据量比较多的时候才会改成 quicklist

#####hash
hashMap rehash的时候 新增一个new 和 old 的结构同时存在， 查询时查2个， 定时任务慢慢移到新的

#####set
HashSet


#####zset  有序列表
zset 内部的排序功能是通过「跳跃列表」数据结构来实现的


对象存放： 
1. fastjson -> string  整体存放，取的时候占带宽  
2. hash ，空间多， 根据实际情况需要考虑2种情况更优

数据结构
String：  

#### 分布式锁
#####悲观锁
需要考虑超时问题,  Redisson 提供了封装好的用法.  
1. 可重入锁（Reentrant Lock）
2. 公平锁（Fair Lock）
3. 联锁（MultiLock）
4. 红锁（RedLock）
5. 读写锁（ReadWriteLock）
6. 信号量（Semaphore）
7. 可过期性信号量（PermitExpirableSemaphore）
8. 闭锁（CountDownLatch）

master 挂 -> slave 重新选主， 如何保证可靠性？  
1. 红锁,  降低性能来提升可靠性
2. Redis3.0后， wait 指令可以让异步复制变身同步复制，确保系统的强一致性 (不严格)。 
#####乐观锁
利用redis提供的watch机制


#### 不可靠的计数 并去重
HyperLogLog, 比set占用的空间小的多

#### 布隆过滤器
用多个 hash 函数对 key 进行 hash
推荐去重，  redis 4.0 之前可以用第三包封装的库https://github.com/Baqend/Orestes-Bloomfilter

#### 限流
简单限流和漏斗限流,  redis 4.0 之后通过redis-cell 支持原子操作的限流

#### （x, y）距离算法GeoHash
将二维编码到一维比较， 地球 横竖切2刀分4块， 00， 01， 10， 11， 继续切下去

#### 查看keys分页
keys返回所有key
scan 可以实现分页查找， 游标值返回为0， 查找结束， 结果可能重复， 客服端需要去重
bigkeys 返回大小前N名的key

#### 持久化
快照 内存的序列化  fork子进程写磁盘, 父进程继续提供服务，采用 Copy on Write 占用空间紧凑，恢复快
AOF  内存修改指令的文本   占用空间大，回放久，  一般是写到内存中， 定期会刷磁盘， 可能存在宕机丢操作的
问题
redis 4.0后可以采用混合 快照+AOF

Redis比较适合当缓存， 不适合当数据库

#### 管道
合并多次客户端请求， 加快速度.   write 写完就返回， 继续write下一个.   read等待本地操作系统缓冲区有数据了 ， 返回客户端
逻辑和 SNMP async get 一样
redis-benchmark -t set -q

#### 事物
multi/exec/discard。multi 指示事务的开始，exec 指示事务的执行，discard 指示事务的丢弃
没提交前， 所有操作都在服务器端缓存队列
```
> multi
OK
> set books iamastring
QUEUED
> incr books
QUEUED
> set poorman iamdesperate
QUEUED
> exec
1) OK
2) (error) ERR value is not an integer or out of range
3) OK
> get books
"iamastring"
> get poorman
"iamdesperate
```

Redis 的事务根本不能算「原子性」，而仅仅是满足了事务的「隔离性」，隔离性中的串行化——当前执行的事务有着不被其它事务打断的权利

#### PubSub
PubSub 的生产者传递过来一个消息，Redis 会直接找到相应的消费者传递过去。如果一个消费者都没有，那么消息直接丢弃。
如果开始有三个消费者，一个消费者突然挂掉了，生产者会继续发送消息，另外两个消费者可以持续收到消息。
但是挂掉的消费者重新连上的时候，这断连期间生产者发送的消息，对于这个消费者来说就是彻底丢失了。
如果 Redis 停机重启，PubSub 的消息是不会持久化的，毕竟 Redis 宕机就相当于一个消费者都没有，
所有的消息直接被丢弃。

正是因为 PubSub 有这些缺点，它几乎找不到合适的应用场景

#### 主从同步
CAP 原理就是——网络分区发生时，一致性和可用性两难全
Redis 的主从数据是异步同步的，所以分布式的 Redis 系统并不满足「一致性」要求
增量操作是放在一个 环状的buffer， buffer满后会覆盖之前的操作
快照全量同步,  刷磁盘或redis 2.8.18之后的无盘复制

#### 集群
1. 哨兵
2. Codis  key hash %1024（可配置)  --> 对应具体的redis实例，  多个codis组成集群， 槽位映射关系保存在
zoomkeeper 或 etcd;  不支持事物， rename 操作很危险
3. RedisCluster  key  hash % 16384  当客户端向一个错误的节点发出了指令，该节点会发现指令的 key 所在的槽位并不归自己管理，
这时它会向客户端发送一个特殊的跳转指令携带目标操作的节点地址，告诉客户端去连这个节点去获取数据。

#### Info指令
```
# 获取所有信息
> info
# 获取内存相关信息
> info memory
# 获取复制相关信息
> info replication
# ops_per_sec: operations per second，也就是每秒操作数
> redis-cli info stats |grep ops
instantaneous_ops_per_sec:789
```

#### 淘汰策略 LRU
````
 noeviction 不会继续服务写请求 (DEL 请求可以继续服务)，读请求可以继续进行。这样可以保证不会丢失数据，但是会让线上的业务不能持续进行。这是默认的淘汰策略。
 volatile-lru 尝试淘汰设置了过期时间的 key，最少使用的 key 优先被淘汰。没有设置过期时间的 key 不会被淘汰，这样可以保证需要持久化的数据不会突然丢失。
 volatile-ttl 跟上面一样，除了淘汰的策略不是 LRU，而是 key 的剩余寿命 ttl 的值，ttl 越小越优先被淘汰。
 volatile-random 跟上面一样，不过淘汰的 key 是过期 key 集合中随机的 key。
 allkeys-lru 区别于 volatile-lru，这个策略要淘汰的 key 对象是全体的 key 集合，而不只是过期的 key 集合。这意味着没有设置过期时间的 key 也会被淘汰。
 allkeys-random 跟上面一样，不过淘汰的策略是随机的 key。
 volatile-xxx 策略只会针对带过期时间的 key 进行淘汰，allkeys-xxx 策略会对所有的 key 进行淘汰。
如果你只是拿 Redis 做缓存，那应该使用 allkeys-xxx，客户端写缓存时不必携带过期时间。如果你还想同时使用 Redis 的持久化功能，那就使用 volatile-xxx 策略，
这样可以保留没有设置过期时间的 key，它们是永久的 key 不会被 LRU 算法淘汰。
````

#### 异步线程
针对很慢的操作, 删除很大的hash, AOF sync等会开启异步线程

#### Redis 安全通信
spiped 就是这样的一款 SSL 代理软件，它是 Redis 官方推荐的代理软件