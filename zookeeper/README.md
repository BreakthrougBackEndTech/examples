###分布式算法
####2PC/3PC
#####2PC

1.提交事务请求

    a. 协调者发起事务询问
    b. 各个参与者记录redo undo日志
    c. 参与者反馈成功, 返回yes； 否则返回no
2.执行事务提交

    a. 提交事务  if all return yes
    b. 中断事务  if any one return no 或 timeout
    
if all return yes,  协调者提交事物
   else any one return no 或 timeout
![image](/zookeeper/img/2pc.jpg)
#####3PC

1. Can Commit

    a. 事务询问
    b. 参与者认为自身可以进行 return yes, 否则 return no
    
2. Pre commit

if all yes

    a. 协调者发送pre commit请求
    b. 参与者记录 redo undo日志，ack反馈给协调者， 等待commit或abort消息
if any one no or timeout

    a. 协调者发送abort
    b. 参与者受到abort 或是等待超时,  中断事务  

3. Do commit

if all yes

    a. 协调者发送commit请求
    b. 参与者受到 dor dommit请求， 提交本地事务，释放事务资源
    c. 参与者 向协调者发送Ack消息
    d. 协调者受到Ack，完成事务

if any one no or timeout

    a.协调者发送abort消息
    b.参与者受到 abort请求,执行本地undo回滚本地事务
    c.参与者回滚后 向协调者发送Ack消息
    d.协调者受到Ack，中断事务
    
在阶段三， 无论协调者故障 或 网路故障（do commit or abort消息丢失）
参与者默认执行本地事务提交

![image](/zookeeper/img/3pc.jpg)

2PC/3PC 都会出现数据不一致的情况
3PC阻塞范围更小， 性能好于2PC

####Paxos

在发生机器宕机或网络异常（包括消息的延迟、丢失、重复、乱序，还有网络分区）
快速且正确地在集群内部对某个数据的值达成一致，都不会破坏整个系统的一致性。

三个角色
    Proposer
    Acceptor
    Learners
![image](/zookeeper/img/paxos role.jpg)
提案（Proposal）。最终要达成一致的value就在提案里。

Paxos算法分为两个阶段。具体如下：

阶段一：

(a) Proposer选择一个提案编号N，然后向半数以上的Acceptor发送编号为N的Prepare请求。

(b) 如果一个Acceptor收到一个编号为N的Prepare请求，且N大于该Acceptor已经响应过的所有Prepare请求的编号，
那么它就会将它已经接受过的编号最大的提案（如果有的话）作为响应反馈给Proposer，同时该Acceptor承诺不再接受
任何编号小于N的提案。

阶段二：

(a) 如果Proposer收到半数以上Acceptor对其发出的编号为N的Prepare请求的响应，那么它就会发送一个针对**[N,V]提案
的Accept请求给半数以上的Acceptor。
注意：V就是收到的响应中编号最大的提案的value**，如果响应中不包含任何提案，那么V就由Proposer自己决定。

(b) 如果Acceptor收到一个针对编号为N的提案的Accept请求，只要该Acceptor没有对编号大于N的Prepare请求做出过响应，
它就接受该提案。
![image](/zookeeper/img/paxos algorithm flow.jpg)


通过选取主Proposer，就可以保证Paxos算法的活性。至此，我们得到一个既能保证安全性，又能保证活性的分布式一致性算法——Paxos算法
![image](/zookeeper/img/paxos liveness.jpg)
####Zab
leader follower obsever
所有事务请求必须由一个全局唯一的服务器来协调处理
ZXID是一个64位的数字   高32 Leader周期epoch的编号， 低32递增的int
崩溃后 ZXID最新的follower成为leader


ZAB协议主要用于构建一个高可用的分布式数据主备系统，而Paxos算法则用于构建一个分布式的一致性状态机系统。

####Raft
Leader（领袖） Follower（群众） Candidate（候选人）

Leader 选举过程
在极简的思维下，一个最小的 Raft 民主集群需要三个参与者（如下图：A、B、C），这样才可能投出多数票。
初始状态 ABC 都是 Follower，然后发起选举这时有三种可能情形发生。下图中前二种都能选出 Leader，
第三种则表明本轮投票无效（Split Votes），每方都投给了自己，结果没有任何一方获得多数票。
之后每个参与方随机休息一阵（Election Timeout）重新发起投票直到一方获得多数票。
这里的关键就是随机 timeout，最先从 timeout 中恢复发起投票的一方向还在 timeout 中的另外两方请求投票，
这时它们就只能投给对方了，很快达成一致。

1. 数据到达 Leader 节点前
2. 数据到达 Leader 节点，但未复制到 Follower 节点
    重新加入集群强制保持和 Leader 数据一致
    
3. 数据到达 Leader 节点，成功复制到 Follower 所有节点，但还未向 Leader 响应接收
这个阶段 Leader 挂掉，虽然数据在 Follower 节点处于未提交状态（Uncommitted）但保持一致，
重新选出 Leader 后可完成数据提交，此时 Client 由于不知到底提交成功没有，可重试提交。
针对这种情况 Raft 要求 RPC 请求实现幂等性，也就是要实现内部去重机制。

4. 数据到达 Leader 节点，成功复制到 Follower 部分节点，但还未向 Leader 响应接收
有最新数据的节点会被选为leader 其它的和3一样

5. 数据到达 Leader 节点，成功复制到 Follower 所有或多数节点，数据在 Leader 处于已提交状态，
但在 Follower 处于未提交状态

6. 数据到达 Leader 节点，成功复制到 Follower 所有或多数节点，数据在所有节点都处于已提交状态，但还未响应 Client
这个阶段 Leader 挂掉，Cluster 内部数据其实已经是一致的，Client 重复重试基于幂等策略对一致性无影响。

7. 网络分区导致的脑裂情况，出现双 Leader
网络分区将原先的 Leader 节点和 Follower 节点分隔开，Follower 收不到 Leader 的心跳将发起选举产生新的 Leader。
这时就产生了双 Leader，原先的 Leader 独自在一个区，向它提交数据不可能复制到多数节点所以永远提交不成功。
向新的 Leader 提交数据可以提交成功，网络恢复后旧的 Leader 发现集群中有更新任期（Term）的新 Leader 则自动降级为
 Follower 并从新 Leader 处同步数据达成集群数据一致。
 
 
####Gossip
最终一致 之前的BT 

####Consist Hash
有节点挂掉的时候 只影响1个或很少的节点，  避免hash算法导致的很多或所有节点迁移，缓存失效验证，雪崩

### zookeeper
Zookeeper是一个开源的分布式协调服务，其设计目标是将那些复杂的且容易出错的分布式一致性服务封装起来，
构成一个高效可靠的原语集，并以一些列简单的接口提供给用户使用。其是一个典型的分布式数据一致性的解决方案，
分布式应用程序可以基于它实现诸如数据发布/发布、负载均衡、命名服务、分布式协调/通知、集群管理、Master选举、
分布式锁和分布式队列等功能。其可以保证如下分布式一致性特性。
```
　　① 顺序一致性，从同一个客户端发起的事务请求，最终将会严格地按照其发起顺序被应用到Zookeeper中去。

　　② 原子性，所有事务请求的处理结果在整个集群中所有机器上的应用情况是一致的，即整个集群要么都成功应用了某个事务，
要么都没有应用。

　　③ 单一视图，无论客户端连接的是哪个Zookeeper服务器，其看到的服务端数据模型都是一致的。

　　④ 可靠性，一旦服务端成功地应用了一个事务，并完成对客户端的响应，那么该事务所引起的服务端状态变更将会一直被保留，
除非有另一个事务对其进行了变更。

　　⑤ 实时性，Zookeeper保证在一定的时间段内，客户端最终一定能够从服务端上读取到最新的数据状态。
```

基本概念
```
① 集群角色，最典型的集群就是Master/Slave模式（主备模式），此情况下把所有能够处理写操作的机器称为Master机器，
把所有通过异步复制方式获取最新数据，并提供读服务的机器为Slave机器。Zookeeper引入了Leader、Follower、Observer
三种角色，Zookeeper集群中的所有机器通过Leaser选举过程来选定一台被称为Leader的机器，Leader服务器为客户端提供写
服务，Follower和Observer提供读服务，但是Observer不参与Leader选举过程，不参与写操作的过半写成功策略，Observer
可以在不影响写性能的情况下提升集群的性能。

② 会话，指客户端会话，一个客户端连接是指客户端和服务端之间的一个TCP长连接，Zookeeper对外的服务端口默认为2181，
客户端启动的时候，首先会与服务器建立一个TCP连接，从第一次连接建立开始，客户端会话的生命周期也开始了，通过这个连接，
客户端能够心跳检测与服务器保持有效的会话，也能够向Zookeeper服务器发送请求并接受响应，同时还能够通过该连接接受来
自服务器的Watch事件通知。

③ 数据节点，第一类指构成集群的机器，称为机器节点，第二类是指数据模型中的数据单元，称为数据节点-Znode，
Zookeeper将所有数据存储在内存中，数据模型是一棵树，由斜杠/进行分割的路径，就是一个ZNode，如/foo/path1，
每个ZNode都会保存自己的数据内存，同时还会保存一些列属性信息。ZNode分为持久节点和临时节点两类，持久节点
是指一旦这个ZNode被创建了，除非主动进行ZNode的移除操作，否则这个ZNode将一直保存在Zookeeper上，而临时节点
的生命周期和客户端会话绑定，一旦客户端会话失效，那么这个客户端创建的所有临时节点都会被移除。另外，Zookeeper
还允许用户为每个节点添加一个特殊的属性：SEQUENTIAL。一旦节点被标记上这个属性，那么在这个节点被创建的时候，
Zookeeper会自动在其节点后面追加一个整形数字，其是由父节点维护的自增数字。

④ 版本，对于每个ZNode，Zookeeper都会为其维护一个叫作Stat的数据结构，Stat记录了这个ZNode的三个数据版本，
分别是version（当前ZNode的版本）、cversion（当前ZNode子节点的版本）、aversion（当前ZNode的ACL版本）。

⑤ Watcher，Zookeeper允许用户在指定节点上注册一些Watcher，并且在一些特定事件触发的时候，Zookeeper服务端
会将事件通知到感兴趣的客户端。

⑥ ACL，Zookeeper采用ACL（Access Control Lists）策略来进行权限控制，其定义了如下五种权限：

　　　　· CREATE：创建子节点的权限。

　　　　· READ：获取节点数据和子节点列表的权限。

　　　　· WRITE：更新节点数据的权限。

　　　　· DELETE：删除子节点的权限。

　　　　· ADMIN：设置节点ACL的权限
```