java study
###common
一致性hash
线程并发 CountDownLatch  CyclicBarrier SpinLock
###1.8新特性
CompletableFuture
接口默认实现
lambda
map
stream
time

###java虚拟机笔记
intern() 谨慎使用，  Intern()方法存在内存溢出的风险

线程，  直接内存，  在堆外

本地线程缓冲区分配
--XX:+/-UseTLAB


对象头 内容  都是指针，  指向java堆中的一块对象，  对线类型数据也是指针， 指向方法区的 类型数据

-Xss 栈的大小


1.8后， 永久代被 元空间取代

-XX:MaxMetaspaceSize  默认-1 不限制
-XX:MetaspaceSize  初始大小， 达到改制就开始GC 进行类卸载
-XX:MinMetaspaceFreeRatio: GC后最小元空间剩余的比例，  可以减少因元空间不足 GC的频率

直接内存
-XX:MaxDiretMemorySize , 默认与-Xmx一致


-XX:MaxGCPauseMillis 最大停顿时间  或 -XX:GCTimeRatio 吞吐量， 指定一个

典型场景
大硬件， 拆分为多个小的服务，
集群同步导致内存溢出
堆外内存溢出  直接内存
外部命令导致系统缓慢，  java 调用 shell，  会fork一个进程去执行， 慢
调用外部服务， 速度不匹配导致 等待线程和Socket过多
不恰当的数据结构
虚拟内存导致长时间停顿，
安全点导致的长时间停顿  在外部rpc等调用的时候， 采用 long 替换 int的循环   安全点以“是否具有让程序长时间执行的特性”为原则选定的

之前项目
平台  定制化

SPI/ServiceLoader 超级解耦; 解决同jvm内服务注册和服务发现（父classLoader 要调用子classLoader的类实现)
   eg.  src/main/resources/META-INF/services/jvm.serviceLoader.IMyServiceProvider

  <dependencies>
        <dependency>
            <groupId>com.google.auto.service</groupId>
            <artifactId>auto-service</artifactId>
            <version>1.0-rc4</version>
            <optional>true</optional>
        </dependency>
    </dependencies>
    
TODO 自定义的debug方式？
感觉像后门， 不过可以用管理员权限限制


解释器， 
编译器   
1. 即时编译 运行时优化
2. 提前编译  JDK9 Jaotc

java虚拟机内存模型
volatile只能保证可见性， 单处修改， 多处读 适合使用， 比如优雅的停止线程

操作系统实现用户线程的三种方式
1. 内核线程实现 1:1  java采用这种方式
2. 用户线程实现 1：N  如 golang 
3. 混合实现  N：M  上面两种都支持



协程 解决线程调用时间大于计算时间的场景.  可以和NIO一样， 从另外一个维度解决C10K问题

Lock性能优于synchronized 在1.6之前

锁优化  先自旋，等待几次失败后进入 重量级的互斥锁.  JVM对自旋等待支持会动态优化

### 自定义线程池
1. tomcat
先扩展核心线程，再进入队列
org.apache.tomcat.util.threads.ThreadPoolExecutor

Tomcat 线程池的逻辑：
```
如果当前运行的线程，少于corePoolSize，则创建一个新的线程来执行任务。
如果线程数大于 corePoolSize了，Tomcat 的线程不会直接把线程加入到无界的阻塞队列中，而是去判断，submittedCount（已经提交线程数）是否等于 maximumPoolSize。
如果等于，表示线程池已经满负荷运行，不能再创建线程了，直接把线程提交到队列，
如果不等于，则需要判断，是否有空闲线程可以消费。
如果有空闲线程则加入到阻塞队列中，等待空闲线程消费。
如果没有空闲线程，尝试创建新的线程。（这一步保证了使用无界队列，仍然可以利用线程的 maximumPoolSize）。
如果总线程数达到 maximumPoolSize，则继续尝试把线程加入 BlockingQueue 中。
如果 BlockingQueue 达到上限（假如设置了上限），被默认线程池启动拒绝策略，tomcat 线程池会 catch 住拒绝策略抛出的异常，再次把尝试任务加入中 BlockingQueue 中。
再次加入失败，启动拒绝策略。
```
2. snmp4j
线程数量是固定的， 如果没有可用的线程, wait
利用网卡的buffer进行缓存，而不是队列