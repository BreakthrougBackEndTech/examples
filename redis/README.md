#### 基本类型
string, list, hash, set, zset;  
对象存放： 
1. fastjson -> string  整体存放，取的时候占带宽  
2. hash ，空间多， 根据实际情况需要考虑2种情况更优

#### 分布式锁
需要考虑超时问题,  Redisson 提供了封装好的用法.  
1. 可重入锁（Reentrant Lock）
2. 公平锁（Fair Lock）
3. 联锁（MultiLock）
4. 红锁（RedLock）
5. 读写锁（ReadWriteLock）
6. 信号量（Semaphore）
7. 可过期性信号量（PermitExpirableSemaphore）
8. 闭锁（CountDownLatch）

TODO  master 挂 -> slave 重新选主， 如何保证可靠性？

#### 不可靠的计数 并去重
HyperLogLog, 比set占用的空间小的多

#### 布隆过滤器
推荐去重，  redis 4.0 之前可以用第三包封装的库https://github.com/Baqend/Orestes-Bloomfilter

#### 限流
简单限流和漏斗限流,  redis 4.0 之后通过redis-cell 支持原子操作的限流

#### （x, y）距离算法GeoHash
将二维编码到一维比较， 地球 横竖切2刀分4块， 00， 01， 10， 11， 继续切下去

#### 查看keys分页
keys返回所有key
scan 可以实现分页查找， 游标值返回为0， 查找结束， 结果可能重复， 客服端需要去重
bigkeys 返回大小前N名的key
